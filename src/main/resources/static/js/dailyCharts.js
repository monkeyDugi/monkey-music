let videoId;
let tag;
let currentVideoIndex;
//let songList = [
//    {
//        rank: 1,
//        song: '아무노래',
//        singer: '지코',
//        img: 'https://i.ytimg.com/vi/UuV2BmJ1p_I/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLB5nudwhD7aBZkTSH4IFhbWNei-7Q',
//        vidoeId: 'UuV2BmJ1p_I'
//    },
//    {
//        rank: 2,
//        song: 'Call You Mine',
//        singer: '권진아, 샘김',
//        img: 'https://i.ytimg.com/vi/tUPOFKU5ZuQ/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAwKi3fLguejBOiwE0rveHNPnz6iw',
//        vidoeId: 'tUPOFKU5ZuQ'
//    }
//]

let songList;

/**
 * Youtube API Load
 */
tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
const firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

/**
 * 필수
 * 플레이어 API에 대한 JavaScript 다운로드 완료 시 API가 이 함수 호출한다.
 * 페이지 로드 시 표시할 플레이어 개체를 만들어야 한다.
 */
let player;
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

 $.ajax({
     type : "GET"
 ,   url :  "/api/charts/daily"
 ,   dataType : "JSON"
 ,   contentType : "application/json"
 ,   success : function(obj) {
         songList = obj;
         songListSet();
     }
 });

let tr = document.getElementsByTagName('tr')
// 음악 목록 Set
function songListSet() {
	for(let i = 0; i < songList.length; i++) {
		tr[i+1].getElementsByTagName('td')[0].innerHTML = songList[i].rank;
        tr[i+1].getElementsByTagName('td')[1].firstElementChild.src = songList[i].image;
        tr[i+1].getElementsByTagName('td')[2].innerHTML = songList[i].title;
        tr[i+1].getElementsByTagName('td')[3].innerHTML = songList[i].singer;
        tr[i+1].getElementsByTagName('td')[4].firstElementChild.value = songList[i].videoId;
        tr[i+1].getElementsByTagName('td')[4].firstElementChild.dataset.index = i;
	}
}

const playBtns = document.getElementsByClassName('play-btn')
for(playBtn of playBtns) {
    playBtn.addEventListener('click', function() {
        onclickVideoIdSet($(this))
    })
}

// onClick 시 VideoId, 현재노래 index Set
function onclickVideoIdSet(obj) {
	if(obj != null && obj != '') {
		videoId = obj.val();
        currentVideoIndex = obj.data('index');
	} else {
        videoId = endedVideoIdSet();
	}

	videoLoad(videoId);
}

// 재상완료 후 다음곡 or 1번 곡 idSet
function endedVideoIdSet() {
	if(lastSongChk()) {
		return tr[currentVideoIndex + 1].getElementsByTagName('td')[4].firstElementChild.value;
	}

	currentVideoIndex = 0;
	return tr[1].getElementsByTagName('td')[4].firstElementChild.value;
}

// 마지막 곡인지 체크
function lastSongChk() {
	if(++currentVideoIndex ==  songList.length) {
		return false;
	}

	return true;
}

// 지정한 동영상 로드
function videoLoad(videoId) {
	player.loadVideoById(videoId, 0, 'small');
}