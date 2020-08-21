<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<h1>회원 가입 폼 입니다.</h1>
		<form action="insert.do" method="post" id="myForm">
			<div class="form-group">
				<label for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
				<small class="form-text text-muted">영문자 소문자로 시작하고 최소 5글자~10글자 이내로 입력 하세요.</small>
				<div class="invalid-feedback">사용할수 없는 아이디 입니다.</div>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text text-muted">최소 5글자~10글자 이내로 입력 하세요.</small>
				<div class="invalid-feedback">비밀번호를 확인 하세요.</div>
			</div>
			<div class="form-group">
				<label for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="pwd2"/>
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input class="form-control" type="text" name="email" id="email"/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력해 주세요.</div>
			</div>
			<button class="btn btn-primary" type="submit">가입</button>
			<button class="btn btn-danger" type="reset">Reset</button>
		</form>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 
	할것 = 아이디 정규표현 검사 ,email 정규표현검사 ,비밀번호 정규표현검사, 아이디 중복확인 ,비밀번호 일치하는지 확인
 -->
 <script>
 	var regId="/^[a-z0-9]{8,15}$/"
 	//아이디 영문 소문자,숫자 8~15글자
 	var regPwd="/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/"
 	//비밀번호 영문소문자 대문자 숫자 특수문자 1글자이상 포함 최소 8글자이상
 	var regEmail="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
 </script>

</html>