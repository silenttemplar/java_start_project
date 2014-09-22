<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
String human=null;
String aaa=request.getParameter("ImageID");
if(aaa.equals("itemGuitar")){
	human="<p><font color='red'>1이여</font>";
	}else if(aaa.equals("itemShades")){
		human="<p>2구만";
	}else if(aaa.equals("itemCowbell")){
		human="<p>3이다";
	}else{
		human="<p>4다";
	};
%>
<%=human %></body>
</html>


