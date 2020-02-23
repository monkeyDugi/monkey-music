# 멜론 일간 차트와 유튜브 API를 이용한 유튜브 영상 재생 서비스

### 사용 기술 및 버젼
- java : 1.8
- junit : 4.12
- springBoot : 2.1.7
- gradle : 4.10.2
- [JPA(Spring Data JPA)]()
  - 내가 현업에서 경험한 java는 DB에 접근하기 위한 수단 정도로 느껴진다. 모든 로직은 쿼리에서 수행하려고 하고,  
    관계형 데이터 베이스는 SQL로만 가능 하기 때문인 것 같다. 이게 잘 못 되었다고 생각하지는 않는다. 다만 java를 이용해서  
    데이터 베이스를 조작 해보고 싶다. 
    뭐가 맞는지는 모르겠지만, java를 쿼리에 넘길 파라미터를 지정하고, 쿼리에서 받은 값을 화면에 던져주는 용도로 사용 중이다.    
    물론 로직도 존재한다. 하지만 쿼리가 메인이라고 느껴진다. 내가 아직 현업에서 java를 제대로 만질 레벨이 아니라서 그런가?  
    그래서 이런 생각이 드는건가? 했지만 몇 가지 프로젝트를 해본 경험으로 윗 분들도 java를 고민하고 사용하지 않는다.  
    이미 자사 프레임워크로 구현이 되어있고, 이미 되어있는 것을 사용하기 때문인 것 같다. 당연히 되어있는 것을 참고해서 하는게 맞지만  
    java에 대해서 고민을 현업에서 한 적이 없다. 쿼리에 대한 고민만 했다. 다른 지인들 이야기를 들어보면 신입들도 java에 대해서  
    고민을 많이 한다고 하는데 나는 경험해보지 못했다. 그래서 이번에 토이 프로젝트에 객체관계매핑인 [ORM]()을 사용하기 위해 JPA를 선택했다.

#

### 요구사항
- 음원 일간 차트
  - 매일 아침 10 시 멜론의 일간 차트를 업데이트 하여 보여준다.
  - 일간 차트는 20개
- 음원 검색 기능
  - 검색어에 대한 유튜브 리스트 20개를 보여준다.
- 마이뮤직
  - 하트 클릭 시 마이 뮤직 리스트에 저장
- 공통
  - 음원은 유튜브 영상을 가져온다.
  - 반복재생, 다음 곡 재생, 이전 곡 재생 가능
  - 구글, 네이버 소셜 로그인 
  - 비회원은 검색 기능 사용 불가
 
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
  
### 목차
#### - [IFrame API]()
#### - [Enetity에서 Setter]()
#### - [생성자와 Builder 패턴]()
#### - [기타]()
- [ORM과 JPA란]()

#

### - IFrame API
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

### - Enetity에서 Setter
- Entity에서 Setter는 사용하지 않는다. 언제 어디서 사용되는 것인지 불분명 해지기 때문이다.
- Entity 변경이 필요하면 메서드를 만들어 사용하자
```java
// 잘못된 예
public class Order {
    public void setStatus(boolean status) {
        this.status = status;
    }   
}

public void 주문서비스의_취소이벤트() {
    order.setStatus(false);
}

// 옳은 예
public class Order {
    public void cancelOrder() {
        this.status = false;
    }   
}

public void 주문서비스의_취소이벤트() {
    order.cancelOrder();
}
```

#

### - 생성자와 Builder 패턴
- 생성자는 setter와 마찬가지로 목적을 파악하기 어렵기 때문에 Builder 패턴으로 명확하게 표현한다.
```java
// 잘못된 예
// 생성자 호출 시 new Exapmle(b, a)로 해도 맞는지 틀린지 돌려보기 전 까지는 알 수가 없다.
public Example(Sring a, STring b) {
    this.a = a;
    this.b = b;
}

// Builder 패턴 : 명확함
Example.builder())
       .a(a)
       .b(b)
       .build();

```

# 
# 기타
### - ORM과 JPA이란
- ORM : Object-relational mapping(객체 관계 매핑)
  - 객체는 객체대로 설계하고, 관계형 데이터베이스는 관계형 데이터베이스대로 설계
  - ORM 프레임워크가 중간에서 매핑
  - 기존에 MyBatis에서 테이블 모델링에 집중하고, 쿼리를 이용하여 데이터베이스 관계를 조작 했지만,   
    ORM은 객체간 관계를 이용한다.
- JPA : Java Persistence API(자바 영속성 API)
  - java ORM 기술 표준으로 인터페이스 모음이다. 
  - Hibernate라는 프레임 워크를 주로 사용
- Spring Data JPA
  - JPA의 구현체를 조금 더 쉽게 사용하기 위해 등장
  - 구현체 교체가 용이함
    - Hibernate 이외에 다른 구현체로 교체를 할 때 Spring Data JPA 내부에서 구현체 매핑을 지원 해주기 때문에  
      교체가 용이하다고 한다.
  - 저장소 교체가 용이함
    - 관계형 데이터베이스 외에 다른 저장소로 쉽게 교체가 쉽다.
      - Spring Data의 하위 프로젝트들은 기본적인 CRUD 인터페이스가 같기 때문이라고 한다.
      - 즉, Spring Data JPA, Spring Data MongoDB 이런 식으로 의존성만 교체하면 save(), findAll() 등의 인터페이스가 같다. 

