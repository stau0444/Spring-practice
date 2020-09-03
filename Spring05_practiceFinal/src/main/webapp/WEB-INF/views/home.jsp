<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<script scr="${pageContext.request.contextPath }/resources/js/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
</head>
<body>	
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-success mb-3">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<c:choose>
				<c:when test="${id ne null}">
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath }/logout.do">로그아웃</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath }/private/userinfo.do">내정보
							</a></li>
						</ul>
						<strong>${id}</strong>님 로그인 되었습니다.
					</div>
				</c:when>
				<c:otherwise>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link"
								href="${pageContext.request.contextPath }/user/insertForm.do">회원
									가입</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath }/user/loginForm.do">로그인</a>
							</li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</nav>
		<a href="cafe/list.do">게시판으로 이동</a>
		<li><a href="shop/list.do">상품 목록 보기</a></li>
	</div>
</body>
</html>