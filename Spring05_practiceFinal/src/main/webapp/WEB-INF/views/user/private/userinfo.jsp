<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
		<h2 class="mt-4">회원정보</h2>
		<span class="float-right mb-4">
			<a href="updateform.do">내정보 변경 |</a>
			<a href="update_pwdform.do">비밀번호 재설정 |</a>
			<a href="delete_form.do" class="text-danger"><strong>회원 탈퇴</strong></a>
		</span>
		<table class="table table-primary border">
			<tr>
				<th>아이디</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.email}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${dto.regdate}</td>
			</tr>
			<tr>
				<th>주의!</th>
				<td>${id}님의 누적 경고번호: <strong> ${dto.warnnum} </strong> 입니다 <small class="text-danger">*누적 경고가 3회 이상일 경우 강제로 탈퇴 당할 수 있습니다.</small></td>
			</tr>
		</table>
	</div>
</body>
</html>