function showComment(){		  
	if (request.readyState == 4) {
		   if (request.status == 200) { 
			   var json = request.responseText; 	  
		       var json_parse= JSON.parse(json);
		       if(json_parse.length==0){
		    	   alert("comment가없습니다");
		       }
		       var content="<table border='1'>";
				for(var i=0;i<json_parse.length;i++){
					content += "<tr> <td>"+json_parse[i].comment_num+"</td>";
					content += "<td>"+json_parse[i].id+"</td>";
					content += "<td>"+json_parse[i].comment_content+"</td>";
					content += "<td>"+json_parse[i].comment_write_date+"</td>";
					content += "<td>"+json_parse[i].article_num+"</td></tr>";
				}
				content+="</table>";		       
			document.getElementById("comments").innerHTML = content;
			}
	}	  
}
function createRequest() {
	try {
			request = new XMLHttpRequest();
	} catch (tryMS) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (otherMS) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failed) {
				request = null;
			}
		}
	}
	return request;
}