<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex flex-column align-items-center">
	<div class="box pl-3">
		<!-- 회원가입 텍스트 -->
		<h3 class="font-weight-bold mt-3">비밀번호 찾기</h3>

		<div class="findPasswordBox">
			<form action="/user/find_password" method="POST" id="signUpForm">

				<div class="p-3">
				
					<!-- id 박스 -->
					<div class="d-flex align-items-center mb-3">
						<div class="font-weight-bold miniBox">ID</div>
						<input type="text" id="loginId" name="loginId" class="form-control col-9" placeholder="ID를 입력해주세요">
					</div>
					
					<!-- 이메일 박스 -->
					<div class="d-flex align-items-center mb-3">
						<div class="font-weight-bold miniBox">이메일</div> 
						<input type="email" id="email" name="email" class="form-control col-9" placeholder="이메일을 입력해주세요">
					</div>
					
					<!-- 비밀번호 박스 -->
					<div class="d-flex align-items-center mb-2">
						<div class="font-weight-bold miniBox d-none newpassword">새비밀번호</div> 
						<input type="password" id="password" name="password" class="form-control col-9 d-none" placeholder="새로운 비밀번호를 입력해주세요">
					</div>

					<!-- 비밀번호 찾기 버튼 -->
					<div class="d-flex justify-content-center pt-3">
						<button type="button" class="btn btn-primary w-100" id="findPasswordBtn">비밀번호 찾기</button>
					</div>

					<!-- 비밀번호 변경 버튼 -->
					<div class="d-flex justify-content-center pt-3">
						<button type="submit" class="btn btn-success w-100 d-none" id="changePasswordBtn">비밀번호 변경</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#findPasswordBtn').on('click',function(){
			//validation
			let loginId = $('#loginId').val().trim();
			let email = $('#email').val().trim();
			if(loginId == ''){
				alert("아이디를 입력해주세요.");
				return;
			}
			
			if(email == ''){
				alert("이메일을 입력해주세요.");
				return;
			}
			
			//초기화
			$('#password').addClass('d-none'); // 새비밀번호 input
			$('.newpassword').addClass('d-none'); // 새비밀번호 div
			$('#changePasswordBtn').addClass('d-none'); // 비밀번호 변경 버튼
			
			// ajax 통신
			$.ajax({
				// request
				type:"POST"
				,url : "/user/find_password"
				,data:{"loginId":loginId, "email":email}
				
				//response
				,success:function(data){
					if(data.code == 1){
						if (data.result){
							// 회원정보가 있을때
							$('#findPasswordBtn').addClass('d-none');
							$('#password').removeClass('d-none');
							$('.newpassword').removeClass('d-none');
							$('#changePasswordBtn').removeClass('d-none');
						} else {
							// 회원정보가 없을때
							alert("입력하신 회원정보가 없습니다.");
						}
					} else {
						// 실패
						alert(data.errorMessage);
					}
				}
				,error:function(e){
					alert("에러" + e);
				}
			});//->ajax통신 끝
		});//->비밀번호 찾기버튼 끝
		
		// 비밀번호 변경
		$('#changePasswordBtn').on('click',function(){
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			let email = $('#email').val().trim();
			
			$.ajax({
				// request
				type:"POST"
				,url:"/user/passwordUpdate"
				,data:{"loginId":loginId,"password":password,"email":email}
			
				//response
				, success:function(data){
					if(data.code == 1){
						alert("비밀번호가 변경되었습니다.");
						location.href="/user/sign_in_view";
					} else if (data.code == 500){
						alert("비밀번호 변경 실패했습니다. 관리자에 문의해주세요.");
					} else {
						alert(data.result);
					}
				}
				, error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			}); //-> 비밀번호 변경 ajax끝
		});//-> 비밀번호 변경 버튼 끝
	});//->document end
</script>