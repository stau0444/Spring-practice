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
	<a href="${pageContext.request.contextPath }/home.do">홈으로</a>
		<c:if test="${not empty keyword}">
			<p class="alert alert-info">
				<strong>${keyword}</strong>라는 키워드로 검색된 결과의 자세히 보기입니다
			</p>
		</c:if>
		<c:if test="${dto.prevNum ne 0}">
			<a href="detail.do?num=${dto.prevNum}&condition=${condition}&keyword=${encodedK}" class="btn btn-outline-info btn-sm">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  					<path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
				</svg>
				이전글
			</a>
		</c:if>
		<c:if test="${dto.nextNum ne 0}">
			<a href="detail.do?num=${dto.nextNum}&condition=${condition}&keyword=${encodedK}" class="btn btn-outline-info btn-sm">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-down" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  					<path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
				</svg>
				다음글
			</a>
		</c:if>
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
			<!-- 원글의 댓글 작성 form -->
			<form class="comment-insert-form" action="comment_insert.do">
				<!-- 원글의 번호가 댓글의 참조번호가 된다.-->
				<input type="hidden" name="ref_group" value="${dto.num}" />
				<!-- 원글의 작성자가 댓글의 대상자가 된다. -->
				<input type="hidden" name="target_id" value="${dto.writer }" />
				<!-- 댓글의 내용 -->
				<textarea name="content"></textarea>
				<button type="submit">등록</button>
			</form>
	</div>
</body>
</html>