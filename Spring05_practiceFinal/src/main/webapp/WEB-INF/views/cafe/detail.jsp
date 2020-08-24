<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<h1>글 상세 페이지</h1>
			<table class="table table-primary">
				<tr>
					<th>글번호</th>
					<td>${dto.num }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${dto.writer }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${dto.title }</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td>${dto.regdate }</td>
				</tr>
			</table>
			<div class="contents">${dto.content }</div>
			<c:if test="${id eq dto.writer }">
				<a href="update_form.do?num=${dto.num}" class="btn btn-outline-success">글 수정</a>
				<a href="delete.do?num=${dto.num }" class="btn btn-outline-danger">글 삭제</a>
			</c:if>
	</div>
</body>
</html>