<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

	System.out.println(request.getParameter("name")); 
	response.setHeader("Content-Type", "text/json; charset=UTF-8");
	response.setContentType("text/plain;charset=UTF-8");
	PrintWriter	pw	= null;
	System.out.println("11");
	String retrunJson = "[{\"name\":\"홍길동\",\"age\":19,\"sex\":\"두번\"},{\"name\":\"둘리\",\"age\":12,\"sex\":\"?\"}]";
	
	try{		
		pw	= response.getWriter();
	
	}catch(IOException e) {e.printStackTrace();}
	
	pw.write(retrunJson.toString());
	pw.flush();
	pw.close();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSON Exercise Page</title>
</head>
<body>

</body>
</html>