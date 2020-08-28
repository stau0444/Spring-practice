<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
	<c:choose>
		<c:when test="${id ne null}">
		<strong>${id}</strong>님 로그인 되었습니다.
			<div>
				<a href="${pageContext.request.contextPath }/user/private/userinfo.do">내정보 |</a>
				<a href="${pageContext.request.contextPath }/user/logout.do">로그아웃</a>
			</div>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath }/user/insertForm.do">회원 가입</a>
			<a href="${pageContext.request.contextPath }/user/loginForm.do">로그인</a>
		</c:otherwise>
	</c:choose>
	<a href="${pageContext.request.contextPath }/home.do">홈으로</a>
	<h1>새글 작성 양식 입니다.</h1>
	<form action="update.do" method="post">
		<input type="hidden" name="sort" value="1" />
		<input type="hidden" name="num" value="${dto.num}" />
		<input type="hidden" name="writer" value="${id}" />
		<div class="form-group">
			<label for="writer">작성자</label>
			<input class="form-control" type="text" value="${id }" disabled/>
		</div>
		<div class="form-group">
			<label for="title">제목</label>
			<input class="form-control" type="text" name="title" value="${dto.title}"/>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" name="content" >${dto.content}</textarea>
		</div>
		<button class="btn btn-outline-primary" type="submit" onclick="submitContents(this);">수정</button>
		<button class="btn btn-outline-warning" type="reset">취소</button>
	</form>
</div>
</body>
</html>