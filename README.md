# 멜론 일간 차트와 유튜브 API를 이용한 유튜브 영상 재생 서비스

### 사용 기술 및 버젼
- java : 1.8
- junit : 4.12
- springBoot : 2.1.7
- gradle : 4.10.2
- quarts : 2.3.1
- querydsl : 4.2.1
- 스프링 시큐리티 :
- 스프링 시큐리티 Oauth2 클라이언트
- [JPA(Spring Data JPA)]()

#

### 요구사항
- 음원 일간 차트
  - 매일 아침 5시 30분 멜론의 일간 차트를 업데이트 하여 보여준다.
  - 일간 차트는 10개
- 음원 검색 기능
  - 검색어에 대한 유튜브 리스트 10개를 보여준다.
- 마이리스트
  - 하트 클릭 시 마이 리스트에 저장
  - 다시 하트 클릭 시 제
  - 페이지당 5개로 페이징
- 공통
  - 음원은 유튜브 영상을 가져온다.
  - 구글, 네이버 소셜 로그인 
  - 비회원은 일간차트 기능만 사용 가능
 
#
 
### 프로젝트 환경 점검
- **SpringBoo 2.2.x**와 **Gradle 5.x**는 너무 많은 것이 변경 되어서 사용하지 않는다.  
  버전에 명시한 버전을 사용한다.
  
- Gradle 버전 확인
![이미지 2](https://user-images.githubusercontent.com/53487385/75084429-60a87f00-5563-11ea-9bd6-5a7822c0474a.png)  
위와 같이 5.x 버전을 사용 중이면 4.10.2 로 다운 그레이드 한다.  
  ```shell script
  $ gradlew wrapper --gradle-version 4.10.2
  ```

#

### SpringBoot 버전 체크  
- 아래와 같아야 함, 2.2.x 버전이면 안됨
**build.gradle**
```shell script
buildscript {
    ext {
        springBootVersion = '2.1.7.RELEASE' // 2.1.7, 2.1.8, 2.1.9 다 괜찮습니다.
    }
    repositories {
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}
```

#  
  
# 목차
### IFrame API
### quarts 스케줄러
### form과 input 태그로 고생한 일
### 스프링 시큐리티와 스프링 스큐리티 Oauth2 클라이언트
- 구글 소셜 로그인
## 세션정보 DB에 저장

#

### IFrame API
- 유튜브에서 제공하는 유튜브 영상을 로드하고, 컨트롤 하기 위한 API
```javascript
<div id="player"></div>

<script type="text/javascript">
const tag;
tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
const firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

/**
 * 필수
 * 플레이어 API에 대한 JavaScript 다운로드 완료 시 API가 이 함수 호출한다.
 * 페이지 로드 시 표시할 플레이어 개체를 만들어야 한다.
 */
const player;
function onYouTubeIframeAPIReady() {
	player = new YT.Player('player', {
		height: '300',
		width: '800',
		videoId: '',
		events: {
			'onReady': onPlayerReady,               // 플레이어 로드가 완료되고 API 호출을 받을 준비가 될 때마다 실행
			'onStateChange': onPlayerStateChange    // 플레이어의 상태가 변경될 때마다 실행
		}
	});
}

function onPlayerReady(event) {
	// 재생
	// 커스텀 펑션으로 뺀다면 player.playVideo()로 사용.
	event.target.playVideo();
}

function onPlayerStateChange(event) {
	if(event.data == YT.PlayerState.ENDED) {
		onclickVideoIdSet();
	}
}
</script>
```
[공문](https://developers.google.com/youtube/iframe_api_reference?hl=ko)

#

### quarts 스케줄러
- 일정 시간마다 기능을 수행할 수 있는 스케줄러
- 사용이유 : 매일 오후 5시 30분에 멜론 일간 차트를 가져와야 하기 때문
- 에러 : InstantiationException, NullPointerException
  - InstantiationException는 @RequireConstructor로 생성자 주입을 할 때 발생  
    NullPointerException는 @Autowired, new로 생성 할 때 발생
  - 원인
    - Job인터페이스를 구현한 Job 구현 클래스를 가동 시키는 스케줄러 클래스 빈이  
      주입 받으려는 Service 클래스에 접근 할 수 없다. Job을 구현 받은 클래스에서 외부 빈은 주입 받을 수 없는건가??
      아마도 다른 빈들 보다 Job의 빈이 먼저 등록 되어, 다른 빈들 불러오지 못하는 것 같음.  
      **아직 정확히 모르겠다.**
  - 해결
    - **ApplicationContextAware** 인터페이스를 구현 받아 **setApplicationContext**로 ApplicationContext에  
      접근할 수 있다.  
      접근하여, ApplicationContext를 set하고 원하는 빈을 get하여 리턴하여 강제 주입 해주면 된다.
  - ApplicationContextAware
    - 빈이 생성 될 때 **setApplicationContext**가 실행 되어 AppicationContext를 가져올 수 있다.
  - ApplicationContext
    - 빈이 생성되면 IoC컨테이너(ApplicationContext)에 저장이 되는데 여기서 직접 빈을 가져오기 위해 사용했다.    

# 

### form과 input 태그로 고생한 일
- ajax로 api를 사용 하다가 고생을 해서 기록한다
- 로직 설명
  1. input의 값을 ajax를 통해 PathVariable로 컨트롤러에 전송  
  ![form](https://user-images.githubusercontent.com/53487385/76988168-a94b3080-6987-11ea-85d4-d0adf2b213b4.PNG)  
  
  2. 엔터키 탕! -> api로 input 값 전송  
  ![js](https://user-images.githubusercontent.com/53487385/76988173-ab14f400-6987-11ea-8a84-fc8e1f40db55.PNG)  
  
  3. input 값 PathVariable로 받아 처리  
  ![apiController](https://user-images.githubusercontent.com/53487385/76988174-ab14f400-6987-11ea-8367-026d511da7de.PNG)  
  
  4. view Controller  
  ![viewController](https://user-images.githubusercontent.com/53487385/76988175-abad8a80-6987-11ea-8693-73edb1b5592b.PNG)  

- 문제점  
  - 엔터키 탕! -> ajax를 처리하다 말고 view url인 "/charts/search/**?**"가 로드 되어 ajax로 처리하던 것이 모두 초기화 되어 버림  
    즉, api 사용 불가, 더욱 이상했던 점은 **다른 키 입력 시 정상작동 함**
- 원인
  - **from**태그는 디폴트로 action이 **자기자신**을 가지고 있고, **form**태그 안에 존재하는 **input**태그는 엔터 입력 시 submit을 하여,    
    **queryString**을 action에 던지는 디폴트를 가지고 있다. 즉, 해당 화면에서 **input**태그에 name, value 속성이 없었으므로
    "/charts/search/**?**"을 로드 해버린 것 이다. name=singer, value=IU 이런 식으로 **input** 태그에 넣어 준다면, 
    "/charts/search/**?singer=IU**" 이런 식으로 로드 되며, 결과는 동일했다. 
- 해결
  - ajax 통신 중 "/charts/search/**?**"가 로드 되는 것이므로, event.preventDefault()로 기존에 걸려있던 submit을 중단 시킨다.
- 정리
  - **form**와 **form**태그 안에 **input**태그는 따로 이벤트를 지정하지 않으면 엔터키 탕! -> queryString을 현재 페이지에 보내어 로드하게 된다.  
  - 의도치 않는 submit이 일어나게 된다. 이는 submit이란 **action**으로 **form**안에 있는 내용을 전송하는 것    
  - <h3>위에 설명한 글은 input태그 type=text일 경우 임.</h3>
  - input태그 type=checkbox일 경우 event.preventDefault()를 실행하면 check, uncheck가 안되는 것.
    - **form**의 action은 아무런 의미가 없음.
- [참고1](https://webisfree.com/2017-08-07/input-입력폼-엔터키-누를-경우-submit-막기-prevent)
- [참고2](https://www.tjvantoll.com/2013/01/01/enter-should-submit-forms-stop-messing-with-that/)
- [참고3](https://developer.mozilla.org/ko/docs/Web/API/Event/preventDefault) 

#

### 스프링 시큐리티와 스프링 스큐리티 Oauth2 클라이언트
- 소셜 로그인 사용 이유 중 몇 가지만 추려 보자면 아래와 같다
  - 로그인 시 보안
  - 비밀번호 찾기/변경
  - 회원가입 시 인증
  - 회원정보 변경 

- 스프링 부트 1.5의 Oauth2가 아니라, 스프링 부트 2.0을 쓸 것 인데 이유는 아래와 같다.
  - 기존이 1.5가 물론 안정적일 수는 있지만 스프링 팀에서 더 이상 버그 수정 이외에 신규 기능은 나오지 않고,  
    2.0에만 신규 기능이 나올 것 이라고 했기 때문이다.  
 
#### -> 구글 소셜 로그인
- 도메인뒤에 붙게 되며 로그인 요청 시 해당 URI를 리디렉션 한다.
![구글 리디렉션 URL](https://user-images.githubusercontent.com/53487385/78036422-40ab7d00-73a5-11ea-8f4d-b58c2d50e191.png)  

- 리디렉션 후 리턴은 도메인이다. 이것을 확인하지 않아서 시간을 날렸다.. 계속 다른 페이지로 리턴을 받으려고 했었다..
![구글OAuth2 리디렉션 후 요청](https://user-images.githubusercontent.com/53487385/78036427-41dcaa00-73a5-11ea-8aa5-fe0f9c974e7a.png)

#

### 세션정보 DB에 저장
- 설정이 간단하지만, 로그인 요청 마다 DB에 요청이 발생하여 성능상 이슈가 발생할 수 있지만,  
  사용자가 적기 때문에 사용!
- JPA에 의하여 **SPRING_SESSION, SPRING_SESSION-ATTRIBUTES** 테이블이 생성 된다.  