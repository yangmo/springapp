<%@ include file="/jsp/include.jsp" %>

<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <script type="text/javascript">
          var json = "\"" + ${json} + "\"";
          var original = ${json};
          window.onload = function () {
      	      var chart = new CanvasJS.Chart("chartContainer", parseJsonToCanvasJS(json));
      	      chart.render();
          }
      </script>
  </head>
<body>
    <%@ include file="TitleAndTabs.jsp" %>
<script type="text/javascript" src="/springapp/js/canvasjs-1.5.7/source/jquery.canvasjs.js"></script>

    <sf:form method = "get" modelAttribute ="paras" action="/springapp/stockFilter/get.html" >
      highVolumeCriteria:  <sf:input path = "highVolumeCriteria" id="highVolumeCriteria"  style="width: 90px;"/>
      <br/><br/>
      oversoldCriteria:  <sf:input path = "oversoldCriteria" id="oversoldCriteria"  style="width: 90px;"/>
      <br/><br/>
      recentMaxVolCriteria:  <sf:input path = "recentMaxVolCriteria" id="recentMaxVolCriteria"  style="width: 150px;"/>
            <br/><br/>
      similarKAverageCriteria: <sf:input path = "similarKAverageCriteria" id="similarKAverageCriteria"  style="width: 90px;"/>
        <input type="submit" value="Query"  />
    </sf:form>

    <br/>


    </body>
    <a href="http://xueqiu.com/S/SH600030" target="_blank">雪球</a>

    <p id="result"> ${json} </p>
    <script type="text/javascript">
        document.getElementById("result").innerHTML = decodeURIComponent("" + document.getElementById("result").innerHTML);
        document.getElementById("result").innerHTML = decodeURIComponent("" + document.getElementById("result").innerHTML.replace(new RegExp("\n",'gm'), "<br/>"));

    </script>
</html>