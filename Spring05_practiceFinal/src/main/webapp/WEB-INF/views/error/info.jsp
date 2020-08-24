<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<alert>
	<p class="alert alert-danger">의도적으로 잘못된 경로를 입력했습니다 3회이상 입력시 강제로 회원탈퇴 됩니다  남은 횟수:${warnnum}</p>
</alert>
</body>
</html>