<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/shop/list.jsp</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
	<script scr="${pageContext.request.contextPath }/resources/js/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
<style>
	#condition{
		font-size:5px;
	}
	.card{
		width: auto;
		height: 475px;
	}
	.card-img-top{
		width: auto;
		height: 300px;
	}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-success mb-3">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<c:choose>
			<c:when test="${id ne null}">
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath }/logout.do">로그아웃</a></li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath }/private/userinfo.do">내정보
						</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="insert_form.do">상품등록</a>
						</li>
					</ul>
					<strong>${id}</strong>님 로그인 되었습니다.
				</div>
			</c:when>
			<c:otherwise>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active"><a class="nav-link"
							href="${pageContext.request.contextPath }/user/insertForm.do">회원
								가입</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath }/user/loginForm.do">로그인</a>
						</li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</nav>
	<div class="container">
		<h1>상품 목록 입니다.</h1>
		<div class="row">
			<c:forEach var="tmp" items="${list }">
				<div class="col-md  my-3 ">
					<div class="card">
						<c:choose>
							<c:when test="${tmp.image eq null }">
								<img class="card-img-top" src="${pageContext.request.contextPath }/resources/images/blank_image.png">
							</c:when>
							<c:otherwise>
								<img class="card-img-top" src="${pageContext.request.contextPath }${tmp.image}" />
							</c:otherwise>
						</c:choose>
						<div class="card-body">
							<h3 class="card-title">${tmp.name }</h3>
							<p class="card-text">
								가격 : <strong>${tmp.price }</strong>원 <br /> 재고 : <strong>${tmp.remainCount }</strong>개
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
				<c:when test="${listSize%3 eq 1}">
					<div class="col"></div>
					<div class="col"></div>
				</c:when>
				<c:when test="${listSize%3 eq 2}">
					<div class="col"></div>
				</c:when>
				<c:when test="${listSize eq 1}">
					<div class="col"></div>
					<div class="col"></div>
				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>
		</div>
		<div class="page-display d-flex justify-content-center">
			<ul class="pagination pagination-sm">
				<!--prev 표시가 생기게 하는 코드 스타트페이지넘이 1이 아닐때 생긴다  -->
				<c:if test="${startPageNum ne 1 }">
					<li class="page-item"><a class="page-link"
						href="list.do?pageNum=${startPageNum-1 }&condition=${condition}&keyword=${encodedK}">Prev</a></li>
				</c:if>
				<!-- 페이지수를 표시하는 코드 -->
				<!-- 스타트페이지넘에서 엔드페이지넘까지 찍어낸다 -->
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i eq pageNum }">
							<li class="page-item active"><a class="page-link"
								href="list.do?pageNum=${i }&condition=${condition}&keyword=${encodedK}">${i }</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href="list.do?pageNum=${i }&condition=${condition}&keyword=${encodedK}">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!--next를 표시하는 코드 next를 누르면 페이지넘이 1씩 증가하고 endPageNum이 총페이지수보다 적을때만 next가 표시된다 -->
				<c:if test="${endPageNum lt totalPageCount }">
					<li class="page-item"><a class="page-link"
						href="list.do?pageNum=${endPageNum+1 }&condition=${condition}&keyword=${encodedK}">Next</a></li>
				</c:if>
			</ul>
		</div>
		<form action="list.do" method="get" class="float-right">
			<!-- 검색 조건을 고르는 select 선택된 것의 value가 서버로 넘어가고 응답받았을때 condition이 선택한것과 같게 만들어야하기 때문에 selected를 조건별로 넣어준다.-->
			<div class="form-inline my-2  row">
				<select name="condition" id="condition " class="select col mr-1" >
					<option value="name" >상품명</option>
				</select>
					 <input class="form-control mr-2 col-6" value="${keyword }" type="text" name="keyword" placeholder="검색어..." />
				<button class="btn btn-outline-success my-2 col" type="submit">검색</button>
			</div>
		</form>
	</div>
</body>
</html>