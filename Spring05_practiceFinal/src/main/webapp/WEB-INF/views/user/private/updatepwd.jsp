<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
<script>
	if(${isSuccess}){
		alert("비밀번호를 수정하였습니다");
		location.href="userinfo.do"
	}else{
		alert("현재 비밀번호가 일치하지 않습니다 다시 시도 해주세요");
		location.href="update_pwdform.do";
	}
</script>
</html>