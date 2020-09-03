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
	<h2>상품등록</h2>
	<form action="insert.do">
		<label for="product">상품명</label>
		<input type="text" name="name" />
		<label for="price">가격</label>
		<input type="text" name="price" />
		<label for="remainCount">재고량</label>
		<input type="text"  name="remainCount"/>
		<button type="submit">등록</button>
	</form>
</body>
</html>