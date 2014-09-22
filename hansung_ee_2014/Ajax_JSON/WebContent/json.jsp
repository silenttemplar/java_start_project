<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function test(){
	var Obj={id:"human",pass:"1234"};
	var content=document.getElementById("content");
	content.innerHTML=Obj.id;
	var content1=document.getElementById("content1");
	content1.innerHTML=Obj.pass;
}
</script>
</head>
<body>
 요밑에 들어옴

  <div id="content"> 
     <h3>JSON 사용 id</h3> 
  </div>
  
  <div id="content1"> 
     <h3>JSON 사용 pass</h3> 
  </div>
 
<input type="button" value="test" onclick="test()">

</body>
</html>