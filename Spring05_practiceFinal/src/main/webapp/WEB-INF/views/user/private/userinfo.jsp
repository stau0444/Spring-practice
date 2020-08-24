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
	<div class="container">
		<h2 class="mt-4">회원정보</h2>
		<span class="float-right mb-4">
			<a href="updateform.do">내정보 변경 |</a>
			<a href="update_pwdform.do">비밀번호 재설정 |</a>
			<a href="delete_form.do" class="text-danger"><strong>회원 탈퇴</strong></a>
		</span>
		<table class="table table-primary border">
			<tr>
				<th>아이디</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.email}</td>
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
	</div>
</body>
</html>