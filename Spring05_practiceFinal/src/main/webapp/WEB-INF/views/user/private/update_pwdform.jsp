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
	<h2>비밀번호 변경</h2>
		<form action="updatepwd.do" method="post" id="myForm">

			<div class="form-group">
				<label for="pwd">현재 비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text text-muted">영문자 소문자로 시작하고 최소 5글자~10글자 이내로 입력 하세요.</small>
				<div class="invalid-feedback">사용할수 없는 아이디 입니다.</div>
			</div>
			<div class="form-group">
				<label for="pwd">새로운 비밀번호</label>
				<input class="form-control" type="password" name="newpwd" id="newpwd"/>
				<small class="form-text text-muted">최소 5글자~10글자 이내로 입력 하세요.</small>
				<div class="invalid-feedback">비밀번호를 확인 하세요.</div>
			</div>
			<div class="form-group">
				<label for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="checkNewPwd"/>
			</div>
			
			<button class="btn btn-primary" type="submit">변경</button>
			<button class="btn btn-danger" type="reset">Reset</button>
		</form>
	</div>
</body>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<script>
$("#myForm").on("submit",function(){
	var newPwd=$("#newpwd").val();
	var checkNewPwd=$("#checkNewPwd").val();
	if(newPwd!=checkNewPwd){
		alert("새로운 비밀번호가 일치하지 않습니다.");
		return false;
	}else{
		$("#myForm").sumit();
	}
})
</script>

</html>
