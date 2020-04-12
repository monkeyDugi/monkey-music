let totalPages
let currentPage
let isLastPage
let isFirstPage

let videoId
let tag
let currentVideoIndex
let songList

let queryParam

/****************************************************************************************************************************************
 * Youtube API Load
 */
tag = document.createElement('script')
tag.src = "https://www.youtube.com/iframe_api"
const firstScriptTag = document.getElementsByTagName('script')[0]
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag)

/**
 * 필수
 * 플레이어 API에 대한 JavaScript 다운로드 완료 시 API가 이 함수 호출한다.
 * 페이지 로드 시 표시할 플레이어 개체를 만들어야 한다.
 */
let player
function onYouTubeIframeAPIReady() {
	player = new YT.Player('player', {
		height: '300',
		width: '800',
		videoId: '',
		events: {
			'onReady': onPlayerReady,               // 플레이어 로드가 완료되고 API 호출을 받을 준비가 될 때마다 실행
			'onStateChange': onPlayerStateChange    // 플레이어의 상태가 변경될 때마다 실행
		}
	})
}

function onPlayerReady(event) {
	// 재생
	// 커스텀 펑션으로 뺀다면 player.playVideo()로 사용.
	event.target.playVideo()
}

function onPlayerStateChange(event) {
	if(event.data == YT.PlayerState.ENDED) {
		onclickVideoIdSet()
	}
}

// onClick 시 VideoId, 현재노래 index Set
function onclickVideoIdSet(obj) {
	if(obj != null && obj != '') {
		videoId = obj.val()
        currentVideoIndex = obj.data('index')
	} else {
        videoId = endedVideoIdSet()
	}

	videoLoad(videoId)
}

// 재상완료 후 다음곡 or 1번 곡 idSet
function endedVideoIdSet() {
	if(lastSongChk()) {
		return tr[currentVideoIndex + 1].getElementsByTagName('td')[3].firstElementChild.value
	}

	currentVideoIndex = 0
	return tr[1].getElementsByTagName('td')[3].firstElementChild.value
}

// 마지막 곡인지 체크
function lastSongChk() {
	if(++currentVideoIndex ==  songList.content.length) {
		return false
	}

	return true
}

// 지정한 동영상 로드
function videoLoad(videoId) {
	player.loadVideoById(videoId, 0, 'small')
}

$(document).on('click', '.play-btn', function() {
    onclickVideoIdSet($(this))
})

/****************************************************************************************************************************************
 * 검색 기능
 */
let search = document.getElementById('search')
let word
search.addEventListener('keydown', function(event) {
    if(event.keyCode === 13) {
        word = search.value
        window.location.href = '/charts/search/' + word
    }
})

/****************************************************************************************************************************************
 * 마이뮤직 전체를 가져오는 api
 */
function myMusicList(isPageMove) {
    if(!isPageMove) queryParam = '?page=0'

    $.ajax({
        type : 'GET',
        url :  '/api/charts/good' + queryParam,
        dataType : "JSON",
        success : function(obj) {
            songList = obj
            rowTagAdd()
            songListSet()
            pagingDataSet()
            paging()
        },
        error : function(error) {
            alert(JSON.stringify(error))
        }
    })
}

myMusicList(false)

function pagingDataSet() {
    totalPages = songList.totalPages
    currentPage = songList.number
    isLastPage = songList.last
    isFirstPage = songList.first
}

/****************************************************************************************************************************************
 * 마이뮤직 리스트 태그 생성
 */
function rowTagAdd() {
    document.getElementById('tbody').innerHTML = ''

    for(let i = 0; i < songList.content.length; i++) {
        objRow = document.getElementById('tbody').insertRow()
        let objCellImage = objRow.insertCell(0)
        objCellImage.innerHTML = "<td class='img-td'><img src=''></td>"

        let objCellTb = objRow.insertCell(1)
        objCellTb.innerHTML = "<td></td>"

        let objCellGood = objRow.insertCell(2)
        objCellGood.innerHTML = "<td class='good-image-td'><img src='' data-good='' data-index='' class='good-btn' style='cursor:Pointer'></td>"

        let objCellPlay = objRow.insertCell(3)
        objCellPlay.innerHTML = "<td class='play-td'><button type='button' class='play-btn' data-index=''><img src=/image/play.png></button></td>"
    }
}

/****************************************************************************************************************************************
 * 마이뮤직 데이터 셋팅
 */
let tr = document.getElementsByTagName('tr')
// 음악 목록 Set
function songListSet() {
	for(let i = 0; i < songList.content.length; i++) {
        tr[i+1].getElementsByTagName('td')[0].firstElementChild.src = songList.content[i].image
        tr[i+1].getElementsByTagName('td')[1].innerHTML = songList.content[i].title
        tr[i+1].getElementsByTagName('td')[2].firstElementChild.src = '/image/heart_b.png'
        tr[i+1].getElementsByTagName('td')[2].firstElementChild.dataset.index = i
        tr[i+1].getElementsByTagName('td')[3].firstElementChild.value = songList.content[i].videoId
        tr[i+1].getElementsByTagName('td')[3].firstElementChild.dataset.index = i
	}
}

/****************************************************************************************************************************************
 * 마이뮤직에서 제거하는 이벤트
 */
// const tb = document.getElementById('tbody')
// 동적 태그 컨트롤을 위해 제이쿼리 사용, vanilaJs로 모르겠음..
$(document).on('click', '.good-btn', function() {
    let index = $(this).data('index')
    let goodVideoId = songList.content[index].videoId

    $(this).parent().parent().remove()

    $.ajax({
        type : 'DELETE',
        url :  '/api/charts/good/' + goodVideoId,
        dataType : 'JSON',
        success : function() {
            window.location.href = '/charts/good'
        }
    })
})

/****************************************************************************************************************************************
 * paging
 * pagination.js 사용함
 */
function paging() {
    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        prev: '이전',
        next: '다음',
        onPageClick: function (event, page) {
           queryParam = '?page=' + (page - 1)
           myMusicList(true)
        }
    })
}