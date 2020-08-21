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
	
</body>
<script>
		if(${isValid}){
			alert("로그인되었습니다");
			location.href="${pageContext.request.contextPath }/home.do";
		}else{
			alert("아이디 혹은 비밀번호가 다릅니다 다시 시도해주세요 ");
			location.href="${pageContext.request.contextPath }/user/loginForm.do"
		}
	</script>
</html>