<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List page</title>
</head>
<body>
<table  width="700">
	<tr align="center">
		<c:if test="${id != null}">
			<%@include file="login_ok.jsp" %>
		</c:if>
		<c:if test="${id == null }">
			<%@include file="login.jsp" %>
		</c:if>
	</tr>
</table>
<b>게시글 목록 - (전체 글:${count})</b>
<table width="700">
	<tr>
		<td align="right">
			<a href="/hansung_bbs_2014_jquery/writeForm.bbs">글쓰기</a>
		</td>
	</tr>
</table>

<c:if test="${count == 0 }">
	<table width="700"	border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>
</c:if>

<c:if test="${count > 0 }">
	<table border="1" width="700" cellpadding="2" cellspacing="2" align="center">
			<tr height="30">
				<th align="center" width="50">번호</th>
				<th align="center" width="250">제목</th>
				<th align="center" width="100">작성자</th>
				<th align="center" width="150">작성일</th>
				<th align="center" width="50">조회수</th>
			</tr>
		<c:forEach var="article" items="${articleList}">
			<tr height="30">
				<td width="50" align="center">${article.article_num}</td>
				<td width="250">
					<c:if test="${article.depth > 0}">
						<img src="images/level.gif" width="${10 * article.depth}" height="16">
						<img src="images/re.gif">
					</c:if>
					<c:if test="${article.depth == 0}">
						<img src="images/level.gif" width="0" height="16">
					</c:if>
					<a href="/hansung_bbs_2014_jquery/content.bbs?article_num=${article.article_num}&pageNum=${pageNum}">
						${article.title}
					</a>
					<c:if test = "${article.hit >= 20}">
						<img src="images/hot.gif" border="0" height="16">
					</c:if>
				</td>
				<td width="100" align="center">${article.id}</td>
				<td width="150" align="center">${article.write_date}</td>
				<td width="50" align="center">${article.hit}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center" height="40">
			${pageCode}
			</td>
		</tr>
	</table>
</c:if>
</body>
</html>