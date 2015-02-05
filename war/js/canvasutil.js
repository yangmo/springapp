/**
 * 
 */

function parseJsonToCanvasJS(json){
    json.title.text = decodeURIComponent(json.title.text);

	for(var i = 0; i < json.data.length; i++){
		json.data[i].dataPoints = parseDataPoints(json.data[i].dataPoints);
	}
	
	return json;
}

function parseDataPoints(dpList){
	var dp1 = new Array();
	
	for(var i = 0; i < dpList.length; i++){
		var data = dpList[i].x;
		console.log(data);
	    dp1.push({x:new Date(data), y:dpList[i].y})
	}
	console.log(dp1);
	return dp1;
}