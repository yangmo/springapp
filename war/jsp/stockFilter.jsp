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
<body >
    <%@ include file="TitleAndTabs.jsp" %>
    <script type="text/javascript" src="/springapp/js/canvasjs-1.5.7/source/jquery.canvasjs.js"></script>

    <sf:form method = "get" modelAttribute ="paras" action="/springapp/stockFilter/get.html"
        style="position:relative;left:20px">
      成交量高于近X交易日平均值Y倍 (x, y): <br/><sf:input class="form-control"  path = "highVolumeCriteria" id="highVolumeCriteria"  style="width: 90px;"/>
      <br/>
      超跌低于X日均线Y (x, y): <br/> <sf:input class="form-control"  path = "oversoldCriteria" id="oversoldCriteria"  style="width: 90px;"/>
      <br/>
      近X日成交量最高值(x): <br/> <sf:input class="form-control"  path = "recentMaxVolCriteria" id="recentMaxVolCriteria"  style="width: 90px;"/>
            <br/>
      均线接近:
      <br/><sf:input class="form-control"  path = "similarKAverageCriteria"
                                 id="similarKAverageCriteria"  style="width: 150px;"/>
      <br/>
        <input class="btn btn-default" type="submit" value="查询"  />
    </sf:form>

    <br/>

    <table class="table table-striped" style="width:800px;">
        <tr>
            <th>序号</th>
            <th>代码</th>
            <th>名称</th>
            <th>详情</th>
            <th>链接</th>
        </tr>
        <c:forEach items="${filterResults}" var="filterResult" varStatus="loop">
            <tr>
                <td> ${filterResult.serialNo} </td>
                <td> ${filterResult.stockId} </td>
                <td id="stockName${filterResult.serialNo}"> ${filterResult.stockName} </td>
                <td> ${filterResult.detail} </td>
                <td> <a href=${filterResult.link} target="_blank"> 雪球 </a> </td>
                <script>
                    document.getElementById("stockName${filterResult.serialNo}").innerHTML =
                        decodeURIComponent(document.getElementById("stockName${filterResult.serialNo}").innerHTML);
                </script>
            </tr>
        </c:forEach>
    </table>
    </body>
</html>