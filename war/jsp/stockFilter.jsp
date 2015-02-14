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
<body style="position:relative;left:30px">
    <%@ include file="TitleAndTabs.jsp" %>
<script type="text/javascript" src="/springapp/js/canvasjs-1.5.7/source/jquery.canvasjs.js"></script>

    <sf:form method = "get" modelAttribute ="paras" action="/springapp/stockFilter/get.html" >
      成交量高于近X交易日平均值Y倍 (x, y): <br/><sf:input class="form-control"  path = "highVolumeCriteria" id="highVolumeCriteria"  style="width: 90px;"/>
      <br/>
      超跌低于X日均线Y (x, y): <br/> <sf:input class="form-control"  path = "oversoldCriteria" id="oversoldCriteria"  style="width: 90px;"/>
      <br/>
      近X日成交量最高值(x): <br/> <sf:input class="form-control"  path = "recentMaxVolCriteria" id="recentMaxVolCriteria"  style="width: 90px;"/>
            <br/><br/>
      均线接近:
      <br/><sf:input class="form-control"  path = "similarKAverageCriteria"
                                 id="similarKAverageCriteria"  style="width: 150px;"/>
      <br/>
        <input class="btn btn-default" type="submit" value="查询"  />
    </sf:form>

    <br/>


    </body>
    <a href="http://xueqiu.com/S/SH600030" target="_blank">雪球</a>

    <p id="result"> ${json} </p>
    <script type="text/javascript">
        document.getElementById("result").innerHTML = decodeURIComponent(document.getElementById("result").innerHTML);
        document.getElementById("result").innerHTML = decodeURIComponent(document.getElementById("result").innerHTML.replace(new RegExp("\n",'gm'), "<br/>"));

    </script>
</html>