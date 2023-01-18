<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="login-box">
		<h1 class="mb-4 mt-5">로그인</h1>
		
		<%-- 키보드 Enter키로 로그인이 될 수 있도록 form 태그를 만들어준다.(submit 타입의 버튼이 동작됨) --%>
		<form id="loginForm" action="/user/sign_in" method="post">
			<div class="input-group mb-3">
				<%-- input-group-prepend: input box 앞에 ID 부분을 회색으로 붙인다. --%>
				<div class="input-group-prepend">
					<span class="input-group-text"><i class="fa fa-user"></i></span>
				</div>
				<input type="text" class="form-control" id="loginId" name="loginId">
			</div>
	
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text"><i class="fa fa-lock"></i></span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			
			
			<%-- 로그인 버튼 --%>
			<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
			<input type="submit" class="btn btn-block btn-primary" value="로그인">
			
			<%-- 비밀번호 찾기 --%>
			<div class="d-flex justify-content-center pt-3">
				<a href="/user/find_password_view">비밀번호를 잊으셨나요?</a>
			</div>
			
			<%-- 회원가입 링크 --%>
			<div class="d-flex justify-content-center pt-2">
				<div>계정이 없으신가요?</div>		
				<a href="/user/sign_up_view" class="pl-3">회원가입</a>
			</div>
			
		</form>
	</div>
</div>
<script>
	$(document).ready(function(){
		// 로그인 버튼
		$('#loginForm').on('submit',function(e){
			e.preventDefault();
			
			// validation
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val();
			
			if(loginId == ''){
				alert("아이디를 입력해주세요.");
				return false;
			}
			
			if(password ==''){
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
			// ajax 통신
			let url = $(this).attr('action');
			//console.log(url);
			let params = $(this).serialize();
			//console.log(params);
			
			$.post(url,params) //request
			.done(function(data){ // response
				if(data.code == 1){
					document.location.href = "/timeline/timeline_view";
				}else {
					alert(data.errorMessage);
				}
			}); // -> 로그인 ajax통신 끝
		});//-> form태그 끝
	});//->document끝
</script>