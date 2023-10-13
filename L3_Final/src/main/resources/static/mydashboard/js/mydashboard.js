$(document).ready(function () {

	//mydashboard calendar
	function prevMonth(date) {
		var target = new Date(date);
		target.setDate(1);
		target.setMonth(target.getMonth() - 1);

		return getYmd(target);
	}

	function nextMonth(date) {
		var target = new Date(date);
		target.setDate(1);
		target.setMonth(target.getMonth() + 1);

		return getYmd(target);
	}

	function getYmd(target) {
		// IE에서 날짜 문자열에 0이 없으면 인식 못함
		var month = ('0' + (target.getMonth() + 1)).slice(-2);
		return [target.getFullYear(), month, '01'].join('-');
	}

	function fullDays(date) {
		var target = new Date(date);
		var year = target.getFullYear();
		var month = target.getMonth();

		var firstWeekDay = new Date(year, month, 1).getDay();
		var thisDays = new Date(year, month + 1, 0).getDate();

		// 월 표시 달력이 가지는 셀 갯수는 3가지 가운데 하나이다.
		// var cell = [28, 35, 42].filter(n => n >= (firstWeekDay + thisDays)).shift();
		var cell = [28, 35, 42].filter(function (n) {
			return n >= (firstWeekDay + thisDays);
		}).shift();

		// 셀 초기화, IE에서 Array.fill()을 지원하지 않아서 변경
		// var days = new Array(cell).fill({date: '', dayNum: '', today: false});
		var days = []
		for (var i = 0; i < cell; i++) {
			days[i] = {
				date: '',
				dayNum: '',
				today: false
			};
		}

		var now = new Date();
		var today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
		var inDate;
		for (var index = firstWeekDay, i = 1; i <= thisDays; index++, i++) {
			inDate = new Date(year, month, i);
			days[index] = {
				date: i,
				dayNum: inDate.getDay(),
				today: (inDate.getTime() === today.getTime())
			};
		}

		return days;
	}

	function drawMonth(date) {
		$('#month-this').text(date.substring(0, 7).replace('-', '.'));
		$('#month-prev').data('ym', prevMonth(date));
		$('#month-next').data('ym', nextMonth(date));

		$('#tbl-month').empty();

		var td = '<td class="__REST__ __TODAY__"><a __HREF__><span>__DATE__<span></a></td>';
		var href = '/depart/schedule?date=' + date.substring(0, 8);
		var hasEvent;
		var tdClass;
		var week = null;
		var days = fullDays(date);
		for (var i = 0, length = days.length; i < length; i += 7) {
			// 전체 셀을 주단위로 잘라서 사용
			week = days.slice(i, i + 7);

			var $tr = $('<tr></tr>');
			week.forEach(function (obj, index) {

				tdClass = (index === 0) ? 'sun' : '';
				tdClass = (index === 6) ? 'sat' : tdClass;

				$tr.append(td.replace('__REST__', tdClass)
					.replace('__TODAY__', (obj['today']) ? 'today' : '')
					.replace('__HREF__', (hasEvent) ? 'href="' + href + ('0' + obj['date']).slice(-2) + '"' : '')
					.replace('__DATE__', obj['date']));
					
				//오늘 날짜에 date-select 클래스 추가	
				$('.today a').addClass('date-select');
			});
			$('#tbl-month').append($tr);
		}
	}

	$(function () {
		var date = (new Date()).toISOString().substring(0, 10);
		drawMonth(date);

		$('.month-move').on('click', function (e) {
			e.preventDefault();

			drawMonth($(this).data('ym'));
		});
	});

	$(function () {
    // 클릭 이벤트 핸들러 추가
    $('#tbl-month').on('click', 'td a', function (e) {
        e.preventDefault();

        // 모든 td 요소에서 on 클래스 제거
        $('#tbl-month td a').removeClass('date-select');

        // 클릭한 <a> 태그의 부모 <td> 요소에 on 클래스 추가
        $(this).addClass('date-select');

        var SelectedDate = $(this).text();

        // 해당 날짜의 년, 월, 일을 추출
        var yearMonth = $('#month-this').text(); // 현재 표시된 년월
        var year = yearMonth.split('.')[0]; // 년도
        var month = yearMonth.split('.')[1]; // 월
        var day = SelectedDate; // 클릭한 날짜

        // 요일 표시
        var daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
        var selectedDateObj = new Date(year, month - 1, day); // Date 객체로 변환
        var today = daysOfWeek[selectedDateObj.getDay()]; // getDay() 메서드로 요일값 가져옴

        // 콘솔에 년, 월, 일을 표시
        console.log('오늘 날짜와 요일:', year + '/' + month + '/' + day + ' (' + today + ')');

        // 나중에 캘린더 db 불러올 때 사용할 ajax
        $.ajax({
            type: 'GET', // 또는 'GET' 등 HTTP 메서드 선택
            url: '../mainboard/getschedule', // 서버 엔드포인트 URL 설정
            data: JSON.stringify(SelectedDate), // 선택된 데이터를 JSON 형식으로 전달
            contentType: 'application/json', // 데이터 타입 설정
            success: function (response) {
                console.log('서버 응답:', response);
                if (response.length > 0) {
                    $('.schedules').empty();
                    response.forEach(function (item) {
                        let str = '<li>'
                        str += '<img src="../resources/mydashboard/img/calendar.svg" class="schedule-calendar-img">'
                        str += '<div class="schedule-title-date">'
                        str += '<span class="schedule-title">' + item.schedule_subject + '</span>'
                        str += '<span class="schedule-date">' + SelectedDate + '(' + today + ')' + '</span>'
                        str += '</div></li>'

                        $('.schedules').append(str);
                    }); //forEach end
                } else {
                    let noScheduleMsg = '<h2>등록된 일정이 없습니다.</h2>'
                    $('.schedules').append(noScheduleMsg);
                    console.log('내 일정이 없습니다.');
                }
            },
            error: function (error) {
                console.error('오류:', error);
            }
        });
    });
});







const memoContent = document.getElementById('memo-content');
const csrfToken = 'csrf토큰 값';
let memoTxt = memoContent.textContent.trim();
let textExist = "";

if (memoTxt === "") {
    memoContent.innerHTML = "내용을 입력하세요.";
    memoContent.style.color = 'lightgrey';
}

// 메모 저장 버튼 클릭 시
memoContent.addEventListener('click', function () {
    if (memoContent.textContent.trim() === "내용을 입력하세요.") {
        memoContent.innerHTML = ""; // 초기화
        memoContent.style.color = '#697a8d';
    }
});

function saveMemo() {
    const newText = memoContent.innerHTML.trim(); // 클릭 이후의 내용 가져오기 (앞뒤 공백 제거)
    if (newText !== "") {
        textExist = newText; // 클릭 이후의 내용을 textExist에 저장
    }

    // 이스케이핑: HTML 엔터티로 변환
    $.ajax({
        url: '../mainboard/memoSave',
        type: 'POST',
        data: {
            memoTxt: newText
        },
        success: function (data) {
            memoContent.innerHTML = ""; // 초기화
            memoContent.innerHTML = textExist; // 클릭 이후의 내용으로 복원
            memoContent.style.color = '#697a8d';
            console.log('메모 저장 성공');
        },
        error: function (error) {
            console.error('메모 저장 실패:', error);
        }
    });
}

// 메모 영역을 떠날 때 메모 저장
memoContent.addEventListener('blur', saveMemo);

// 페이지 로드 시 메모 내용 초기화
window.addEventListener('load', function () {
    saveMemo();
});














	function animatePercentage() {
		const totalWorkCount = parseInt($(".total-work span").text());

		$(".situation-list").not(".total-work").each(function () {
			const workCount = parseInt($(this).find("span").text());
			const percent = Math.round((workCount / totalWorkCount * 100));

			$(this).find(".js-chart-percent").prop('Counter', 0).animate({
				Counter: workCount
			}, {
				duration: 1000,
				easing: 'swing',
				step: function (now) {
					$(this).text(Math.ceil(now) + "%");
				}
			});
		});
	}


	//work-summary load
	$.ajax({
		url: '../mainboard/CountPerStatus',
		type: 'GET',
		success: function (issuecount) {
			if (issuecount.length > 0) {
				// 데이터가 비어있지 않으면 작업 수행

				// issuecount 데이터를 사용하여 숫자 업데이트
				$(".total-work span").text(issuecount[0].totalcount);
				$(".ToDo-work span").text(issuecount[0].todocount);
				$(".Progress-work span").text(issuecount[0].progresscount);
				$(".Resolved-work span").text(issuecount[0].resolvedcount);
				$(".Done-work span").text(issuecount[0].donecount);

				// animatePercentage() 함수 호출
				animatePercentage();
			} else {
				// 데이터가 비어 있는 경우의 처리
				console.log("데이터가 비어 있습니다.");
			}
		},
		error: function (error) {
			// 데이터 가져오기에 실패했을 때 이 코드를 실행
			console.error('데이터를 가져오는 중에 오류가 발생했습니다:', error);
		}
	});

	const counterElement = $("#counter");
	const targetNumber = 1199;
	let duration;

	if (targetNumber < 100) {
		duration = 1000;
	} else if (targetNumber >= 100) {
		duration = 1500;
	}

	let currentNumber = 0;
	let startTime = null;

	function updateCounter(timestamp) {
		if (!startTime) {
			startTime = timestamp;
		}

		const progress = timestamp - startTime;
		const speedFactor = 1 - Math.exp(-progress / duration * 5); // 속도를 조절

		currentNumber = Math.round(targetNumber * speedFactor);
		counterElement.text(currentNumber);

		if (currentNumber < targetNumber) {
			requestAnimationFrame(updateCounter);
		}
	}

	requestAnimationFrame(updateCounter);




	//my-work-list
	$(".work-type li:first-child").addClass("active");

	// 항목을 클릭할 때 이벤트를 처리합니다.
	$(".work-type li").click(function () {
		$(".work-type li").removeClass("active");

		$(this).addClass("active");

		var selectedWorkType = $(this).data('value');

		$.ajax({
			url: "../mainboard/mywork",
			type: "POST",
			data: {
				status: selectedWorkType
			},
			success: function (mywork) {4
				$('.board').empty();
				if (mywork.length > 0) {
					mywork.forEach(function (item) {
						let str = '<li><div class="type-title">'
						if (item.issue_type === '버그') {
							str += '<img src="../resources/issue/img/bug.svg"> <span>버그</span>'
						} else if (item.issue_type === '작업') {
							str += '<img src="../resources/issue/img/task.svg"> <span>작업</span>'
						} else {
							str += '<img src="../resources/issue/img/epic.svg"> <span>에픽</span>'
						}
						str += '<a href="../issue/issuedetail?num=' + item.issue_id + '">'
						str += '<span class="post-title">' + item.issue_subject + '</span></div></a>'
						str += '<span class="post-date">' + item.issue_created + '</span></li>'

						$('.board').append(str);
					});
				} else {
					console.log('작업이 없습니다.');
					$('.board').append('<h3>작업이 없습니다.</h3>')
				}
			},
			error: function (error) {
				console.error("Error: " + error);
			}
		});



	});


}); //ready end


