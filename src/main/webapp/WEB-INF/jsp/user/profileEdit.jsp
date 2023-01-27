<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center w-100 my-3">
	<div class="edit-box">
		<!-- 이미지 박스 -->
		<div class="d-flex justify-content-center">
			<c:choose>
				<c:when test="${empty user.profileImgPath}">
					<img alt="프로필 사진 없을때" src="/static/img/timeline/profileImg.png" class="profileBox">
				</c:when>
				<c:otherwise>
					<img alt="프로필 사진" src="${user.profileImgPath}" class="profileBox">
				</c:otherwise>
			</c:choose>
		</div>
		
		<!-- 파일 선택 버튼 -->
		<div class="d-flex justify-content-end">
			<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
			<button class="btn-sm btn-primary pictureBtn">사진 선택</button>
			<div id="fileName" class="ml-2"></div>
		</div>
		
		<!-- input박스 -->
		<div class="d-flex justify-content-center">
			<div class="w-75 my-3">
				<input type="text" class="form-control name" placeholder="변경할 이름을 입력해주세요." value="${user.name}">
				<input type="text" class="form-control mt-3 statusMessage" placeholder="상태메세지를 입력해주세요." value="${user.statusMessage}">
				<input type="password" class="form-control mt-3 password" placeholder="비밀번호를 입력해주세요.">
			</div>
		</div>
		
		<!-- 버튼 -->
		<div class="buttonBox my-2">
				<button type="button" class="btn btn-warning w-75" id="profileEditBtn">프로필 수정</button>
				<button type="button"  class="btn btn-secondary w-75 my-2" id="exitUserBtn">회원탈퇴</button>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		// 사진 선택
		$('.pictureBtn').on('click',function(){
			$('#file').click();
		});
		
		// 이미지 선택시 사진 보여지기
		 $('#file').on('change',function(){
			 setImageFromFile(this, '.profileBox');
		 });
		 function setImageFromFile(input, expression) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
			    reader.onload = function (e) {
			    $(expression).attr('src', e.target.result);
				}
			reader.readAsDataURL(input.files[0]);
			}
		}

		// 프로필 수정버튼
		$('#profileEditBtn').on('click',function(){
			let name = $('.name').val().trim();
			let statusMessage = $('.statusMessage').val().trim();
			//alert("name : " + name + "\n statusMessage : "+ statusMessage + "\npassword:"+password);
			let userId = ${userId};
			let password = $('.password').val();
			
			let file = $('#file').val();
			if(name == ''){
				alert("사용할 이름을 입력해주세요.");
				return;
			}
			
			if(file != ''){
				let ext = file.split(".").pop().toLowerCase();
				if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){
					alert("지원하는 확장자가 아닙니다.");
					$('#file').val("");
					return;
				}
			}
			
			let formData = new FormData();
			formData.append("name", name);
			formData.append("statusMessage", statusMessage);
			formData.append("userId", userId);
			formData.append("password", password);
			formData.append("file", $('#file')[0].files[0]);
			
			$.ajax({
				// request
				type:"POST"
				, url:"/user/profileEdit"
				, data:formData
				, enctype:"multipart/form-data"
					, processData:false
					, contentType:false
					
				// response
				,success:function(data){
					if(data.code == 1){
						// 성공
						alert("수정 완료되었습니다.");
						location.href="/post/individual_page_view?userId="+userId;
					} else {
						// 실패
						alert("수정 실패.");
					}
				}
				, error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			})//-> 프로필수정 ajax끝
			
		});// 프로필 수정버튼 끝
		
		// 회원탈퇴 버튼
		$('#exitUserBtn').on('click',function(){
			//alert(111);
			let name = $('.name').val().trim();
			let statusMessage = $('.statusMessage').val();
			let password = $('.password').val();
			let userId = ${userId};
			
			if(name == ''){
				alert("이름을 입력해주세요.");
				return;
			}
			
			if(password == ''){
				alert("비밀번호를 입력해주세요.");
				return;
			}
			
			$.ajax({
				type:"DELETE"
				,url:"/user/withdrawal"
				,data:{"name":name, "password":password}
			
				,success:function(data){
					if(data.code == 1){
						alert("회원 탈퇴가 완료되었습니다.");
						location.href="/user/sign_out";
						location.href="/timeline/timeline_view";
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
			
		});//->탈퇴버튼 끝
	});//->document끝
</script>