<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
<script>

	//myApp 이라는 모듈 만들기 
	var myApp=angular.module("myApp", []);
	//formCtrl 이라는 컨트롤러 만들기 
	
	myApp.controller("formCtrl", function($scope, $http){
		//angularjs  가 초기화 될때 최초 한번만 호출된다.
		$scope.isValid=false;
		$scope.checkId=function(){
			$http({
				url:"checkid.do",
				method:"get",
				params:{id:$scope.id}
			})
			.success(function(data){
				$scope.isValid=data.isValid;
				console.log($scope.isValid)
				console.log(data.isValid+"하이");
			});
		};
		//비밀번호 입력란을 입력했을때 호출되는 함수 
		$scope.pwdChanged=function(){
			//비밀번호를 같게 입력했는지 여부를 모델로 관리한다. 
			$scope.myForm.pwd.$valid = $scope.pwd==$scope.pwd2;
			$scope.myForm.pwd.$invalid = $scope.pwd!=$scope.pwd2;
		};
	});
</script>
</head>
<body >
	<div class="container" ng-controller="formCtrl">
		<h1>회원 가입 폼 입니다.</h1>
		<form action="insert.do" method="post" id="myForm" name="myForm" novalidation>
			<div class="form-group">
				<label for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"
				ng-model="id"
				ng-required="true"
				ng-minlength="8"
				ng-maxlength="15"
				ng-class="{'is-invalid':isValid==false&&myForm.id.$dirty,'is-valid':isValid==true}"
				ng-change="checkId()"/>
				<small class="form-text text-muted">영문자 소문자로 시작하고 최소 8글자~15글자 이내로 입력 하세요.{{id}}</small>
				<div class="invalid-feedback">사용할수 없는 아이디 입니다.</div>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input class="form-control " type="password" name="pwd" id="pwd"
				ng-model="pwd"
				ng-required="true"
				ng-minlength="8"
				ng-maxlength="15"
				ng-pattern="/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/"
				ng-class="{'is-valid':(myForm.pwd.$valid&&pwd==pwd2) && myForm.pwd.$dirty,'is-invalid':(myForm.pwd.$invalid||pwd!=pwd2)&&myForm.pwd.$dirty}"
				/>
				<small class="form-text text-muted">최소 5글자~10글자 이내로 입력 하세요.</small>
				<div class="invalid-feedback">비밀번호를 확인 하세요.</div>
			</div>
			<div class="form-group">
				<label for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="pwd2" 
				ng-model="pwd2"
				ng-required="true"
				ng-minlength="8"
				ng-maxlength="15"
				ng-pattern="/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/"
				/>
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input class="form-control" type="text" name="email" id="email"
				ng-model="email"
				ng-required="true"
				ng-pattern="/^([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/"
				/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력해 주세요.</div>
			</div>
			<button class="btn btn-primary" type="submit"
			ng-disabled="myForm.$invalid">가입</button>
			<button class="btn btn-danger" type="reset">Reset</button>
		</form>
	</div>
</body>
</html>