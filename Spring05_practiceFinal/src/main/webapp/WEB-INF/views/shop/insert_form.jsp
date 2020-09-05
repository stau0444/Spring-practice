<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	#productImage{
		width: 360px;
		height: 450px;
	}
</style>
</head>
<body>
	<h2>상품등록</h2>
	<h3>상품이미지</h3>
	<a href="javascript:" id="imageLink">
		<c:choose>
			<c:when test="${empty dto.image}">
				<svg id="productImage"  width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
				</svg>
			</c:when>
			<c:otherwise>
				<img id="productImage" src="${pageContext.request.contextPath }${dto.image }"/>
			</c:otherwise>
		</c:choose>
	</a>
	
	<form action="insert.do">
		<input type="hidden" name="image" id="product" value="${dto.image}"/>
		<label for="product">상품명</label>
		<input type="text" name="name" />
		<label for="price">가격</label>
		<input type="text" name="price" />
		<label for="remainCount">재고량</label>
		<input type="text"  name="remainCount"/>
		<button type="submit">등록</button>
	</form>
	<form action="image_upload.do" method="post" enctype="multipart/form-data" id="imageForm">
		<input type="file" name="image"  accept=".jpg, .jpeg, .png, .JPG, .JPEG" id="image"/>
	</form>
</body>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script>
$("#imageLink").on("click", function(){
	$("#image").click();
});

$("#image").on("change", function(){
	$("#imageForm").submit();
});

$("#imageForm").ajaxForm(function(data){
	console.log("성공")
	$("#productImage").remove();
	$("<img/>")
	.attr("id", "productImage")
	.attr("src", "${pageContext.request.contextPath }"+data.imageSrc)
	.appendTo("#imageLink");
	
	//회원정보 수정폼 전송될때 같이 전송 되도록한다.
	$("#product").val(data.imageSrc);// input type="hidden" 의 value값
});
</script>
</html>