<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<body>
    <script type="text/javascript" src="/springapp/js/canvasjs-1.5.7/source/jquery.canvasjs.js"></script>
    <script type="text/javascript" src="/springapp/js/jquery1.9.min.js"></script>

    <jsp:include page="TitleAndTabs.jsp" />
    <h4 style="color:red">Doesn't support concurrent running.</h4>

    <h2>Parameters</h2>

    <script type="text/javascript">

    function getState(){
        $.get('/PerfTest/getState.html',function(data){
            if (window.console) console.log(data);

            $('#divState').html( data[1] );

            if (data[0] == 'NotStarted' || data[0] == 'End' || data[0] == 'EndWithError'){
                $('#btnStart').show();
                $('#spanTesting').hide();
                if (timerId){
                    clearInterval(timerId);
                }
            } else {
                $('#btnStart').hide();
                $('#spanTesting').show();
            }

        }, 'json');
    }

    var timerId;
    function enableGetState(){
        timerId = setInterval(function(){
            getState();
        }, 3000);
    }

    function start(){

        var data = {
            dateStr:document.getElementById("dateStr").value
        };

        if (window.console) console.log('start...', data);

        $.get('/springapp/dailyUpdate/start.html', data , function(result){
            if (window.console) console.log(result);
            document.getElementById("dateStr").value=result;
        }).fail(function(){
                if (window.console) console.log('error, when startTest');
            });
        }
    </script>

        <!-- add these for page loading debug  -->
        <input type="hidden" name="state" value="${state} " />
        <input type="hidden" name="fakeAccountCount" value="${fakeAccountCount} " />
        <input type="hidden" name="esofsRequestThreadNum" value="${esofsRequestThreadNum} " />
        <input type="hidden" name="esofsRequestCallNum" value="${esofsRequestCallNum} " />
        <input type="hidden" name="jiffyThreadNum" value="${jiffyThreadNum} " />
        <input type="hidden" name="jiffyWriteNum" value="${jiffyWriteNum} " />
        <input type="hidden" name="jiffyWriteIntervalInMS" value="${jiffyWriteIntervalInMS} " />
        <input type="hidden" name="isNewESOFS" value="${isNewESOFS} " />
        <input type="hidden" name="isFMA" value="${isFMA} " />
        <input type="hidden" name="testEnv" value="${testEnv} " />

       <input type="text" id="dateStr" value="${dateStr}" style="position:absolute;left:450;width:143px;">
       <input type="button" id="btnStart" value="Start New Test" onclick="start();"/><br>


    <div id='divState' style='white-space:pre;'>

    </div>

</body>
</html>