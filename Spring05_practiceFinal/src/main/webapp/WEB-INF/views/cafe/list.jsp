<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
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
			<ul class="pagination pagination-sm">
				<!--prev 표시가 생기게 하는 코드 스타트페이지넘이 1이 아닐때 생긴다  -->
				<c:if test="${startPageNum ne 1 }">
					<li class="page-item"><a class="page-link" href="list.do?pageNum=${startPageNum-1 }&condition=${condition }&keyword=${encodedK }">Prev</a></li>
				</c:if>
				<!-- 페이지수를 표시하는 코드 -->
				<!-- 스타트페이지넘에서 엔드페이지넘까지 찍어낸다 -->
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<c:choose>
						<!--누른 페이지넘이 페이지넘과 같다면 activ속성을 추가하는 코드 -->
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
		<form action="list.do" method="get">
		<label for="condition">검색조건</label>
		<!-- 검색 조건을 고르는 select 선택된 것의 value가 서버로 넘어가고 응답받았을때 condition이 선택한것과 같게 만들어야하기 때문에 selected를 조건별로 넣어준다.-->
		<select name="condition" id="condition">
			<option value="title_content" <c:if test="${condition eq 'title_content' }">selected</c:if>>제목+내용</option>
			<option value="title" <c:if test="${condition eq 'title' }">selected</c:if>>제목</option>
			<option value="writer" <c:if test="${condition eq 'writer' }">selected</c:if>>작성자</option>
		</select>
		<input value="${keyword }" type="text" name="keyword" placeholder="검색어..."/>
		<button type="submit">검색</button>
	</form>	
	</div>
</body>
</html>