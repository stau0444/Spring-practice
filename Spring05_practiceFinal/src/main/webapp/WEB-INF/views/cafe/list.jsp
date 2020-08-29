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
	/* Reset Select */
select {
  -webkit-appearance: none;
  -moz-appearance: none;
  -ms-appearance: none;
  appearance: none;
  outline: 0;
  box-shadow: none;
  border: 0 !important;
  background: #2c3e50;
  background-image: none;
}
/* Remove IE arrow */
select::-ms-expand {
  display: none;
}
/* Custom Select */
.select {
  position: relative;
  display: flex;
  width: 100%;
  height: 35px;
  line-height: 1;
  background: tomato;
  overflow: hidden;
  border-radius: .25em;
  margin-right:10px;
}
select {
  flex: 1;
  padding: 0 .5em;
  color: #fff;
  cursor: pointer;
}
/* Arrow */
.select::after {
  content: '\25BC';
  position: absolute;
  top: 0;
  right: 0;
  padding: 0 1em;
  background: #34495e;
  cursor: pointer;
  pointer-events: none;
  -webkit-transition: .25s all ease;
  -o-transition: .25s all ease;
  transition: .25s all ease;
}
/* Transition */
.select:hover::after {
  color: #f39c12;
}
</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-primary bg-light mb-3">
		  <a class="navbar-brand" href="#">Navbar</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
			<c:choose>  
			<c:when test="${id ne null}">
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <ul class="navbar-nav mr-auto">
			      <li class="nav-item active">
			      	<a class="nav-link" href="${pageContext.request.contextPath }/home.do">홈으로</a>
			      </li>
			      <li class="nav-item">
			       	<a class="nav-link" href="${pageContext.request.contextPath }/logout.do">로그아웃</a>
			      </li>
			      <li class="nav-item">
			       	<a class="nav-link" href="${pageContext.request.contextPath }/private/userinfo.do">내정보 </a>
			      </li>
			    </ul>
			    <strong>${id}</strong>님 로그인 되었습니다.
			  </div>
			  </c:when>
			  <c:otherwise>
			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <ul class="navbar-nav mr-auto">
			      <li class="nav-item active">
			      	<a class="nav-link" href="${pageContext.request.contextPath }/home.do">홈으로</a>
			      </li>
			      <li class="nav-item active">
			      	<a class="nav-link"  href="${pageContext.request.contextPath }/user/insertForm.do">회원 가입</a>
			      </li>
			      <li class="nav-item">
			       	<a class="nav-link" href="${pageContext.request.contextPath }/user/loginForm.do">로그인</a>
			      </li>
			    </ul>
			  </div>
			  </c:otherwise>
			</c:choose>
		</nav>
	<h2>게시판</h2>
	<a href="private/insert_form.do" class="float-right">글 작성</a>
		<table class="table table-light">
			<thead>
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list}">
					<tr>
						<td>${tmp.num}</td>
						<td>${tmp.writer }</td>
						<td><a href="private/detail.do?num=${tmp.num}">${tmp.title }</a></td>
						<td>${tmp.viewCount }</td>
						<td>${tmp.regdate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page-display">
			<ul class="pagination pagination-sm d-flex justify-content-center">
				<!--prev 표시가 생기게 하는 코드 스타트페이지넘이 1이 아닐때 생긴다  -->
				<c:if test="${startPageNum ne 1 }">
					<li class="page-item"><a class="page-link" href="list.do?pageNum=${startPageNum-1 }&condition=${condition }&keyword=${encodedK }">Prev</a></li>
				</c:if>
				<!-- 페이지수를 표시하는 코드 -->
				<!-- 스타트페이지넘에서 엔드페이지넘까지 찍어낸다 -->
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<c:when test="${i eq pageNum }">
							<li class="page-item active"><a class="page-link" href="list.do?pageNum=${i }&condition=${condition }&keyword=${encodedK }">${i }</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link" href="list.do?pageNum=${i }&condition=${condition }&keyword=${encodedK }">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!--next를 표시하는 코드 next를 누르면 페이지넘이 1씩 증가하고 endPageNum이 총페이지수보다 적을때만 next가 표시된다 -->
				<c:if test="${endPageNum lt totalPageCount }">
					<li class="page-item"><a class="page-link" href="list.do?pageNum=${endPageNum+1 }&condition=${condition }&keyword=${encodedK }">Next</a></li>
				</c:if>
			</ul>	
		</div>
		<!-- 검색키워드를 전송하는 폼 -->
		<form action="list.do" method="get" class="float-right">
			<!-- 검색 조건을 고르는 select 선택된 것의 value가 서버로 넘어가고 응답받았을때 condition이 선택한것과 같게 만들어야하기 때문에 selected를 조건별로 넣어준다.-->
			<div class="form-inline my-2 my-lg-0">
				<label for="condition"></label>
				<select name="condition" id="condition" class="select">
					<option value="title_content" <c:if test="${condition eq 'title_content' }">selected</c:if>>제목+내용</option>
					<option value="title" <c:if test="${condition eq 'title' }">selected</c:if>>제목</option>
					<option value="writer" <c:if test="${condition eq 'writer' }">selected</c:if>>작성자</option>
				</select>
			  <input class="form-control mr-sm-2" value="${keyword }" type="text" name="keyword" placeholder="검색어..."/>
		      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
		    </div>
		</form>	
	</div>
</body>
</html>