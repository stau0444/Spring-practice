<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/shop/list.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<a href="insert_form.do">상품등록</a>
	<c:choose>
		<c:when test="${empty id }">
			<p>
				<a href="${pageContext.request.contextPath }/users/loginform.do">로그인</a>
				<a href="${pageContext.request.contextPath }/users/signup_form.do">회원가입</a>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<strong>${id }</strong> 님 로그인중...
			</p>
		</c:otherwise>
	</c:choose>
	<h1>상품 목록 입니다.</h1>
	<div class="row">
		<c:forEach var="tmp" items="${list }">
			<div class="col my-3">
				<div class="card">
					<img class="card-img-top" src="${pageContext.request.contextPath }/resources/images/wyattEarp.jpg"/>
					<div class="card-body">
						<h3 class="card-title">${tmp.name }</h3>
						<p class="card-text">
							가격 : <strong>${tmp.price }</strong>원 <br/>
							재고 : <strong>${tmp.remainCount }</strong>개
						</p>
						<c:choose>
							<c:when test="${tmp.remainCount eq 0 }">
								<p>품절</p>
							</c:when>
							<c:otherwise>
								<a href="buy.do?num=${tmp.num }" class="card-link">구입하기</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${tmp.rownum%3 eq 0}">
					<div class="w-100"></div>
				</c:when>
			</c:choose>
		</c:forEach>
			<c:choose>
				<c:when test="${MaxRowNum%3 eq 1}">
					<div class="col"></div>
					<div class="col"></div>
				</c:when>
				<c:when test="${MaxRowNum%3 eq 2}">
					<div class="col"></div>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
	</div>
	<a href="detail.do">상품디테일</a>
</div>
</body>
</html>