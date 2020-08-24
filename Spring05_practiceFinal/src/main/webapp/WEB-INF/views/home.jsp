<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<c:choose>
		<c:when test="${id ne null}">
		<strong>${id}</strong>님 로그인 되었습니다.
			<div>
				<a href="private/userinfo.do">내정보 |</a>
				<a href="logout.do">로그아웃</a>
			</div>
		</c:when>
		<c:otherwise>
			<a href="user/insertForm.do">회원 가입</a>
			<a href="user/loginForm.do">로그인</a>
		</c:otherwise>
	</c:choose>
	<a href="cafe/list.do">게시판으로 이동</a>
</body>
</html>