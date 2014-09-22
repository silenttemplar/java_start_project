<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
<%--
		ArrayList<LoginVO> loginList = (ArrayList<LoginVO>)request.getAttribute("loginList");
		for(LoginVO obj : loginList){
			
	아이디: <%=obj.getId() %><Br>
	
	패스워드: <%=obj.getPass() %><Br>
€
		}
--%>
<c:forEach var="loginvo" items="${loginList}">
아이디는 ${loginvo.id } <br>
패스워드는 ${loginvo.pass } <br>
</c:forEach>
</body>
</html>