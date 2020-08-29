<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	li{
		list-style: none;
	}
	.contents{
		width:auto;
		height:500px;
		border: 1px solid green;
	}
	.footer{
		width:100%;
		height:200px;
		border-top:1px solid grey;
		margin-top:50px;
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
		<c:if test="${not empty keyword}">
			<p class="alert alert-info">
				<strong>${keyword}</strong>라는 키워드로 검색된 결과의 자세히 보기입니다
			</p>
		</c:if>
		<c:if test="${dto.prevNum ne 0}">
			<a href="detail.do?num=${dto.prevNum}&condition=${condition}&keyword=${encodedK}" class="btn btn-outline-info btn-sm">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  					<path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
				</svg>
				이전글
			</a>
		</c:if>
		<c:if test="${dto.nextNum ne 0}">
			<a href="detail.do?num=${dto.nextNum}&condition=${condition}&keyword=${encodedK}" class="btn btn-outline-info btn-sm">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-down" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  					<path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
				</svg>
				다음글
			</a>
		</c:if>
		<h1>글 상세 페이지</h1>
			<table class="table table-primary">
				<tr>
					<th>글번호</th>
					<td>${dto.num }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${dto.writer }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${dto.title }</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td>${dto.regdate }</td>
				</tr>
			</table>
			<div class="contents">${dto.content }</div>
			<c:if test="${id eq dto.writer }">
				<a href="update_form.do?num=${dto.num}" class="btn btn-outline-success">글 수정</a>
				<a href="delete.do?num=${dto.num }" class="btn btn-outline-danger">글 삭제</a>
			</c:if>
			<div> <strong>댓글</strong></div>
			<ul class="pl-0">
				<c:forEach var="tmp" items="${commentList}">
					<li class="mt-2 ">
						<div class="alert alert-success" role="alert">
						  <p class="alert-heading">작성자:${tmp.writer } 작성일:${tmp.regdate }</p>
						  <p>${tmp.content }</p>
						  <hr>
						  <c:if test="${id eq tmp.writer}">
						 	 <p class="mb-0" >
							 	 <a href="">답글</a> 
							 	 <a href="">수정</a>
							 	 <a href="" class="text-danger">삭제</a>
						 	 </p>
						  </c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div class="page-display d-flex justify-content-center">
				<ul class="pagination pagination-sm">
					<!--prev 표시가 생기게 하는 코드 스타트페이지넘이 1이 아닐때 생긴다  -->
					<c:if test="${startPageNum ne 1 }">
						<li class="page-item"><a class="page-link" href="detail.do?num=${num }&pageNum=${startPageNum-1 }">Prev</a></li>
					</c:if>
					<!-- 페이지수를 표시하는 코드 -->
					<!-- 스타트페이지넘에서 엔드페이지넘까지 찍어낸다 -->
					<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
						<c:choose>
							<c:when test="${i eq pageNum }">
								<li class="page-item active"><a class="page-link" href="detail.do?num=${num }&pageNum=${i }">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="detail.do?num=${num }&pageNum=${i }">${i }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!--next를 표시하는 코드 next를 누르면 페이지넘이 1씩 증가하고 endPageNum이 총페이지수보다 적을때만 next가 표시된다 -->
					<c:if test="${endPageNum lt totalPageCount }">
						<li class="page-item"><a class="page-link" href="detail.do?num=${num }&pageNum=${endPageNum+1 }">Next</a></li>
					</c:if>
				</ul>	
			</div>
			<!-- 원글의 댓글 작성 form -->
			<form class="comment-insert-form" action="comment_insert.do">
				<!-- 원글의 번호가 댓글의 참조번호가 된다.-->
				<input type="hidden" name="ref_group" value="${dto.num}" />
				<!-- 원글의 작성자가 댓글의 대상자가 된다. -->
				<input type="hidden" name="target_id" value="${dto.writer }" />
				<!-- 댓글의 내용 -->
				<div class="input-group">
					  <div class="input-group-prepend">
					    <span class="input-group-text">댓글달기</span>
					  </div>
					<textarea class="form-control" name="content"></textarea>
				<button class="btn btn-outline-success" type="submit">등록</button>
				</div>
			</form>
	</div>
	<div class="footer"></div>
</body>
</html>