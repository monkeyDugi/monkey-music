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

### 로컬, 서버에 있는 ignore 파일
- main/resources/application-apikey.properties
```java
# 로컬 테스트용
api_youtube=본인의 youtube api key
```

<br/>

- test/resources/appication-apikey.properties
```java
# 테스트용
api_youtube=본인의 youtube api key
```

<br/>

- main/resources/application-oauth.properties
```java
# google oauth2 클라이언트 등록
spring.security.oauth2.client.registration.google.client-id=[본인 클라이언트 ID]
spring.security.oauth2.client.registration.google.client-secret=[본인 시크릿 ID]
spring.security.oauth2.client.registration.google.scope=profile,email

# naver oauth2 클라이언트 등록
# nvaer는 스프링 시큐리티에서 지원하지 않기 때문에 google 보다 설정이 많음(provider설정을 해주어야 함)
# registration
spring.security.oauth2.client.registration.naver.client-id=[본인 클라이언트 ID]
spring.security.oauth2.client.registration.naver.client-secret=[본인 시크릿 ID]
spring.security.oauth2.client.registration.naver.redirect_uri_template={baseUrl}/{action}/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.naver.authorization_grant_type=authorization_code
spring.security.oauth2.client.registration.naver.scope=name,email,profile_image
spring.security.oauth2.client.registration.naver.client-name=Naver
# provider
spring.security.oauth2.client.provider.naver.authorization_uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token_uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
## 기준이 되는 user_name의 이름을 네이버에서는 response로 해야 함
## 이유는 네이버의 회원 조회 시 반환되는 JSON 형태 때문(아래 참고)
#{
#    "resultcode": "00",
#    "message": "success",
#    "response": {
#        "email": "openapi@naver.com",
#        "nickname": "OpenAPI",
#        "profile_iamge": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
#        "age": "40-49",
#        "gender": "F",
#        "id": "32742776",
#        "name": "오픈API",
#        "brithday": "10-01"
#    }
#}
spring.security.oauth2.client.provider.naver.user_name_attribute=response
```

#

### 서버에만 있는 파일
- /home/ec2-user/app/monkey-music-service/application-real-db.properties
# dll-auto는운영서버이기 때문에 create를 하지 않는다.
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mariadb:[rds주소]:3306/[db명]
srping.datasource.username=[계정]
spring.datasource.password=[비번]
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
<br/>
