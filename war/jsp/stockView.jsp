<%@ include file="/jsp/include.jsp" %>

<html>
  <head>
  <script type="text/javascript" src="/springapp/js/canvasjs-1.5.7/canvasjs.min.js"></script>
  <script type="text/javascript" src="/springapp/js/canvasjs-1.5.7/source/jquery.canvasjs.js"></script>
  <script type="text/javascript" src="/springapp/js/canvasutil.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <script type="text/javascript">
  var json = ${json};
  var original = ${json};
  window.onload = function () {
	 
	var chart = new CanvasJS.Chart("chartContainer", parseJsonToCanvasJS(json));
	chart.render();
  }
  </script>
</head>
<body>
  <%@ include file="TitleAndTabs.jsp" %>

  <div id="chartContainer" style="height: 300px; width: 60%;">
  </div>
  <br/><br/>
  <sf:form method = "get" modelAttribute ="paras" action="/springapp/stockView/get.html" >
      Start:  <sf:input path = "start" id="start"  style="width: 90px;"/>
      <br/><br/>
      End:  <sf:input path = "end" id="end"  style="width: 90px;"/>
      <br/><br/>
      Average:  <sf:input path = "kAverages" id="kAverages"  style="width: 150px;"/>
            <br/><br/>
      StockId: <sf:input path = "stockId" id="stockId"  style="width: 90px;"/>
        <input type="submit" value="Query"  />
    </sf:form>
 
  </body>
</html>