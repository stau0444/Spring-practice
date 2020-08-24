<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update user info</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
	<h2>회원정보 수정</h2>
		<form action="update.do" method="post">
			<table class="table table-primary border">
				<tr>
					<th>아이디</th>
						<td>
							<input type="hidden" name="id" value="${id}"/>
							<input type="text" value="${dto.id}" name="id" class="form-control " disabled/>
						</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" value="${dto.email}" name="email" class="form-control"/></td>
				</tr>
				<tr>
					<th>등록일</th>
					<td>${dto.regdate}</td>
				</tr>
				<tr>
					<th>주의!</th>
					<td>${id}님의 누적 경고번호: <strong> ${dto.warnnum} </strong> 입니다 <small class="text-danger">*누적 경고가 3회 이상일 경우 강제로 탈퇴 당할 수 있습니다.</small></td>
				</tr>
			</table>
			<div class="float-right">
				<button type="submit" class="btn btn-outline-success">수정</button>
				<button type="reset" class="btn btn-outline-danger">취소</button>
			</div>
		</form>
		
	</div>
</body>
</html>