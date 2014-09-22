<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="js/comment.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Content page</title>
</head>
<body>
<b>Content Page</b>
<table>
	<tr>
		<th>게시판 번호 :</th>
		<td>${content.article_num}</td>
		<th>제목 :</th>
		<td>${content.title}</td>
	</tr>
	<tr>
		<th>글쓴이 :</th>
		<td>${content. id}</td>
		<th>작성일 :</th>
		<td>${content.write_date}</td>
	</tr>
	<tr>
		<th colspan="4">내용 : </th>
	</tr>
	<tr>
		<td colspan="4">${content.content}</td>
	</tr>
	<tr>
		<th>첨부파일 :</th>
		<td colspan="3"><a href="download.bbs?fname=${content.fname}">${content.fname}</a></td>
	</tr>
</table>
<table>
	<tr>
	<c:if test="${id !=null}">
	<th>
		<form action="/hansung_bbs_2014_jquery_jquery_jquery/replyForm.bbs" method="post" name="contentForm">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<input type="hidden" name="depth" value="${content.depth}">
			<input type="hidden" name="pos" value="${content.pos}">
			<input type="hidden" name="group_id" value="${content.group_id}">
			<input type="hidden" id="article_num" value="${content.article_num }" >
			<input type=submit value="답글 작성하기"/>
		</form>	
	</th>	
		<c:if test="${id == content.id}">
	<th>
		<input type=button onclick="location.href='updateForm.bbs?article_num=${content.article_num}&pageNum=${pageNum}'" value="수정하기"/>
	</th>
	<th>
		<input type=button onclick="location.href='deleteArticle.bbs?article_num=${content.article_num}&pageNum=${pageNum}'" value="삭제하기"/>
	</th>
		</c:if>
		<c:if test="${id != content.id}">
	<th>
		<input type=button value="수정하기" disabled="disabled"/>
	</th>
	<th>
		<input type=button value="삭제하기"  disabled="disabled"/>
	</th>
		</c:if>
	</c:if>
	<c:if test="${id == null}">
		<th>
			<input type=button value="답글 작성하기" disabled="disabled" />
		</th>
		<th>
			<input type=button value="수정하기" disabled="disabled"/>
		</th>
		<th>
			<input type=button value="삭제하기"  disabled="disabled"/>
		</th>
	</c:if>
		<th>
		<input type=button onclick="location.href='list.bbs?pageNum=${pageNum}'" value="목록보기">
		</th>
	</tr>
	<tr>
		<td colspan="4">
			<input type="button" value="comment 보기"  id="comment_read" >
		</td>
	</tr>
		<c:if test="${id !=null}">		
	<tr>
		<td colspan="3">
			<textarea name="comment_content" id="comment_content"></textarea>
		</td>
		<td>
			<input type="button" value="comment 쓰기" id="comment_write">
		</td>
	</tr>
		</c:if>
		<c:if test="${id ==null}">
	<tr>
		<td colspan="3">
			<textarea name="comment_content" id="comment_content" disabled="disabled"></textarea>
		</td>
		<td>
			<input type="button" value="comment 쓰기" disabled="disabled">
		</td>
	</tr>
		</c:if>
</table>
<div id="comments"> 
    <h3>요기에 새로운 내용이 들어옴</h3> 
 </div>
</body>
</html>