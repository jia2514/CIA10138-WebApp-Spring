<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<base th:href="@{/}">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>近兩個月所有空房查詢</title>

<link th:insert="~{/back-end/htmlfile/css.html}" />
    <style th:inline="text">
       * {
       padding: 0;
       margin: 0;
     }
     ol,
     ul,
     li {
       list-style: none;
     }
     :root {
       /* 定义变量 --变量名 通过var得到变量值 
   		(1)  定义到根元素  => 页面中都就可以使用
           (2)  定义到父元素  => 子元素可以使用*/
       --line-height: 40px;
     }
     .calendar {
       width: 300px;
       margin: 50px auto;
       background-color: aliceblue;
       padding: 10px;
     }
     .calendar-header {
       display: flex;
       justify-content: space-between;
       font-size: 14px;
       line-height: 40px;
     }
     .calendar-title {
       display: flex;
       justify-content: space-between;
       font-size: 14px;
       line-height: 40px;
     }
     .calendar .calendar-list {
       display: flex;
       flex-wrap: wrap;
       text-align: center;
       font-size: 14px;
       line-height: var(--line-height);
     }
     .calendar .calendar-list li {
       flex: 1;
       flex-basis: -webkit-calc(100% / 7);
       border: 1px solid transparent;
       box-sizing: border-box;
       color: black;
     }
     .calendar-list li:hover {
       border-color: dodgerblue;
     }

     .calendar .calendar-list .qday {
       background-color: pink;
     }

     .calendar .calendar-list .prevMonth,
     .calendar .calendar-list .nextMonth {
       color: #666;
     }
     .calendar .calendar-list .qMonth {
       color: black;
       font-weight: 500;
     }
     .my_empty_calendar{
     width:80%;
     height:50%
     }
     
     .fc .fc-toolbar.fc-header-toolbar {
    margin-bottom: 0.5em;
}
     .fc .fc-toolbar-title {
    font-size: 1.2em;
    margin: 0px;
}
.fc .fc-daygrid-body-natural .fc-daygrid-day-events {
    margin-bottom: 0;
}

.fc .fc-button {
   
    border-radius: 0.25em;
    
    font-size: 0.8em;
    font-weight: 400;
    line-height: 1.5;
    padding: 0.4em 0.65em;
    text-align: center;
    
}


.fc .fc-daygrid-day-top {
    display: inline-block;
   
}

a.fc-daygrid-day-number{
font-size: 1em;
}
.fc .fc-daygrid-body-unbalanced .fc-daygrid-day-events {
    min-height: 1.5em;
    position: relative;
}

.fc-h-event .fc-event-title {
   font-size: 1.2em;
}
    </style>


</head>
<body>
	<th:block th:insert="~{/back-end/htmlfile/content1}" />
	<div class="container-fluid pt-4 px-4">
		<div class="bg-light rounded">
			<div class="G3_content">
				<div align="center">
					<h2>所有空房查詢</h2>

            <div class="my_empty_calendar">
                <div id="calendar"></div>
            </div>

           <a th:href="@{/roomschedule/roomscheduleselect}">回住宿訂單首頁</a>
			</div>
		</div>
	</div>
	<th:block th:insert="~{/back-end/htmlfile/content2}" />
	<th:block th:insert="~{/back-end/htmlfile/script}" />


		<!-- 获取后台传递的数据并转换为 JSON 字符串 -->
<script th:inline="javascript">
    var listQuery = /*[[${roomScheduleCount}]]*/ [];
    console.log(listQuery);
</script>

<!-- 导入自定义 JavaScript 文件 -->
<script th:src="@{/backend/js/index.global.js}"></script>

<script th:inline="javascript">
    
    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var eventList = [];
        var listFirst = listQuery[0];
        var startDate = listFirst[1];
        var roomTypeId = 0;
        var emptyNum = null;

        listQuery.forEach(function(obj) {
            emptyNum = obj[3];
            var textcolor = null;
            var saleStatus = null;
            roomTypeId = obj[0];
            if (emptyNum == 0) {
                textcolor = '#C0C0C0';
                saleState = '已無空房';
            } else if (emptyNum == 1) {
                textcolor = 'red';
                saleState = '即將滿房';
            } else {
                textcolor = 'green';
                saleState = '熱銷中';
            }

            eventList.push({
                title: '剩餘:' + emptyNum + '間',
                start: obj[1],
                backgroundColor: 'transparent',
                borderColor: 'transparent',
                textColor: textcolor
            });
        });

        var calendar = new FullCalendar.Calendar(calendarEl, {
            headerToolbar: {
                left: '',
                center: 'title',
                right: 'prev,next today',
                classNames: 'calendar-title'
            },

            select: function(info) {
                alert('selected ' + info.startStr + ' to ' + info.endStr);
                var startDate = new Date(info.startStr);
                var endDate = new Date(info.endStr);
                var selectedData = listQuery.filter(function(obj) {
                    var objDate = new Date(obj[1]);
                    return objDate >= startDate && objDate <= endDate;
                });
                var minEmptyRooms = selectedData.reduce(function(min, obj) {
                    return Math.min(min, obj[3]);
                }, Infinity);
                var customPageURL = /*[[ @{${pageContext.request.contextPath}+'/backend/roomorder/addRoomOrder.jsp?roomTypeId1='+roomTypeId+'&checkindate='+info.startStr+'&checkoutdate='+info.endStr+'&minEmptyRooms1='+minEmptyRooms} ]]*/ '';
                window.location.href = customPageURL;
            },

            initialDate: startDate,
            navLinks: false,
            businessHours: false,
            editable: false,
            selectable: true,
            height: 'auto',
            aspectRatio: 2,
            events: eventList,
        });

        window.addEventListener('resize', function() {
            calendar.render();
        });

        calendar.render();
    });
</script>

</body>
</html>