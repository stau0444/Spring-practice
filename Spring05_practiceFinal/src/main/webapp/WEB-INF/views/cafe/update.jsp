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
	if(${isUpdate}){
		alert("글이 수정 되었습니다");
		location.href="${pageContext.request.contextPath}/cafe/private/detail.do?num="+${num};
	}
</script>
</html>