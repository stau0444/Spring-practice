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
	if(${isValid}){
		alert("회원 탈퇴 되었습니다.");
		location.href="${pageContext.request.contextPath }/home.do";
	}else{
		alert("입력하신 비밀번호가 틀립니다.다시 시도해 주세요")
		location.href="userinfo.do"
	}
</script>
</html>