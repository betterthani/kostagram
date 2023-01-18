<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex flex-column align-items-center">
	<div class="box pl-3">
		<!-- 회원가입 텍스트 -->
		<h3 class="font-weight-bold mt-3">회원가입</h3>

		<div class="joinBox">
			<form action="/user/sign_up" method="POST" id="signUpForm">

				<div class="p-3">
				
					<!-- id 박스 -->
					<small>ID</small>
					<div class="d-flex mb-1">
						<input type="text" id="loginId" name="loginId"
							class="form-control col-9 mr-3" placeholder="ID를 입력해주세요">
						<button type="button" id="isDuplicatedBtn"
							class="btn btn-sm btn-outline-success">중복확인</button>
						<br>
					</div>
					
					<%-- 아이디 체크 결과 --%>
					<%-- d-none 클래스: display none (보이지 않게) --%>
					<div id="idCheckLength" class="small text-danger d-none">ID를
						4자 이상 입력해주세요.</div>
					<div id="idCheckDuplicated" class="small text-danger d-none">이미
						사용중인 ID입니다.</div>
					<div id="idCheckOk" class="small text-success d-none">사용 가능한
						ID 입니다.</div>

					<!-- password 박스 -->
					<small>password</small> <input type="password" id="password"
						name="password" class="form-control col-7 mr-3 mb-1"
						placeholder="비밀번호를 입력해주세요">

					<!-- 재확인용 password 박스 -->
					<small>confirm password</small> 
					<div>
						<input type="password" id="recheckPassword" name="recheckPassword" class="form-control col-7 mr-3 mb-1" placeholder="비밀번호를 재입력해주세요">
					</div>
					<div id="passwordCheckDuplicated" class="small text-danger d-none">비밀번호가 일치하지 않습니다.</div>
					
					<!-- 이름 박스 -->
					<small>이름</small> <input type="text" id="name" name="name"
						class="form-control col-9 mr-3 mb-1" placeholder="이름을 입력해주세요">


					<!-- 이메일 박스 -->
					<small>이메일</small> <input type="text" id="email" name="email"
						class="form-control col-9 mr-3 mb-1" placeholder="이메일을 입력해주세요">

					<!-- 회원가입 버튼 -->
					<div class="d-flex justify-content-center pt-3">
						<button type="submit" class="btn btn-primary w-100" id="signUpBtn">가입하기</button>
					</div>
				</div>

			</form>
		</div>

	</div>
</div>
<script>
	$(document).ready(function() {
		
		// id 길이(중복 확인)
		$('#loginId').on('input', function() {
			//초기화
			$('#idCheckLength').addClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			$('#idCheckOk').addClass('d-none');

			let loginId = $('input[name=loginId]').val().trim();
			if (loginId.length < 4) {
				loginId = loginId.slice(0, 4);
				$(this).val(loginId);
				$('#idCheckLength').removeClass('d-none');
				return;
			}

			// 중복확인
			$('#isDuplicatedBtn').on('click', function() {
				
				let loginId = $('input[name=loginId]').val().trim();
				
				$.ajax({
					//request
					url : "/user/is_duplicated_id",
					data : {
						"loginId" : loginId
					}

					//response
					,
					success : function(data) {
						if (data.code == 1 && loginId.length >= 4) {
							// 성공
							if (data.result) {
								// 중복
								$('#idCheckDuplicated').removeClass('d-none');
							} else {
								// 사용 가능
								$('#idCheckOk').removeClass('d-none');
							}
						} 

					},
					error : function(e) {
						alert("중복확인에 실패했습니다.");
					}
				});//->중복확인 ajax
			});//->중복확인 끝
		});//->id길이 제한끝
		
		$('#recheckPassword').on('input',function(){
			let password = $('#password').val().trim();
			let recheckPassword = $('#recheckPassword').val().trim();
			$('#passwordCheckDuplicated').addClass('d-none');
			
			if(recheckPassword ==''){
				$('#passwordCheckDuplicated').addClass('d-none');
				return;
			} else if(password != recheckPassword){
				$('#passwordCheckDuplicated').removeClass('d-none');
				return;
			}
		});//-> 비밀번호 리체크박스 끝
		
		$('#signUpBtn').on('click',function(){
			let loginId = $('input[name=loginId]').val().trim();
			let password = $('#password').val().trim();
			let recheckPassword = $('#recheckPassword').val().trim();
			let name = $('#name').val().trim();
			let email = $('#email').val().trim();
			
			if(loginId == ''){
				alert("아이디를 입력해주세요.");
				return false;
			}
			if(password == '' || recheckPassword ==''){
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			if(password != recheckPassword){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			if (name == ''){
				alert("이름을 입력해주세요.");
				return false;
			}
			if(email == ''){
				alert("이메일을 입력해주세요.");
				return false;
			}
			if( $('#idCheckOk').hasClass('d-none')){
				alert("아이디 중복확인을 다시 해주세요.");
				return false;
			}
			
			// ajax통신
			let url = $(this).attr('action');
			let params = $(this).serialize();
			consol.log(params);
			
			$.post(url, params)
			.done(function(data){
				if(data.code == 1){
					alert(loginId + "님 환영합니다! 로그인을 해주세요!");
					//location.href = "/user/sign_in_view";
				} else {
					alert(data.errorMessage);
				}
			});//->ajax통신 끝
		}); //-> 회원가입 버튼 누를시 끝
	});//->document 끝
</script>