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
	if(${isInsert}){
		alert("글이 등록 되었습니다.");
		location.href="${pageContext.request.contextPath }/cafe/list.do"
	}
</script>
</html>