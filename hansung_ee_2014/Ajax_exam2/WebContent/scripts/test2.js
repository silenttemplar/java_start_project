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

function test2() {	  
	  request = createRequest();
	  if (request == null)
	    alert("Unable to create request");
	  else {	    
	    var url= "result2.jsp";	    
	    request.onreadystatechange = showTable;	   
	    request.open("GET", url, true);
	    request.send(null);	   
	  }
	}

	function showTable() {
	  if (request.readyState == 4) {
	    if (request.status == 200) {	    	
	    	var xml_content = request.responseXML;
	    	var root_Node=xml_content.getElementsByTagName("Member");
	    	var child_Node_id=xml_content.getElementsByTagName("ID");
	    	var child_Node_pass=xml_content.getElementsByTagName("PASS");
	    	var result_content="<table>";    	
	    	
	    	for(var i=0;i<root_Node.length;i++){	    		
	    		result_content +="<tr>";
	    		id=child_Node_id[i].childNodes[0].nodeValue;	    		
	    		result_content +="<td>"+id+"</td>";
	    		pass=child_Node_pass[i].childNodes[0].nodeValue;
	    		result_content +="<td>"+pass+"</td>";
	    		result_content +="</tr>";	    		
	    	}   	    	
	    	result_content +="</table>";	    	
	    	document.getElementById("content").innerHTML = result_content;	    
	    }
	  }  
	}