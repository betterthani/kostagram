<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center my-3">
	<div class="contents-box">
		<!-- 이미지 박스 -->
		<div class="d-flex justify-content-center">
			<c:if test="${not empty post.imgPath}">
				<img alt="이미지" src="${post.imgPath}" class="postDetailBox w-100">
			</c:if>
			<c:if test="${empty post.imgPath}">
				<img src="/static/img/timeline/noimg.gif" class="postDetailBox w-100" alt="본문 이미지">
			</c:if>
				<!-- <img src="" class="postDetailBox w-100" alt="본문 이미지" id="previewImg"> -->
		</div>
		
		<!-- 작성글 박스(작성자가 아닌 게시물의 경우 그냥 글만 보여지기) -->
		<c:choose>
			<c:when test="${post.userId eq userId}">
				<textarea class="form-control" id="content" placeholder="내용을 입력하세요." rows="3">${post.content}</textarea>
			</c:when>
			<c:otherwise>
				<div>${post.content}</div>
			</c:otherwise>
		</c:choose>
		<!-- 파일 버튼 -->
		<div class="d-flex justify-content-end my-1">
			<!-- 접속자와 post의 userId동일할때만 보이기 -->
			<c:if test="${post.userId eq userId}">
				<input type="file" id="file" accept=".jpg, .png, .jpeg, .gif" class="files">
			</c:if>
		</div>
		
		<!-- 버튼 -->
		<div class="d-flex justify-content-between my-3">
			<!-- 뒤로가기 버튼 -->
			<a href="/post/individual_page_view?userId=${post.userId}" class="btn btn-info">뒤로가기</a>
			
			<div class="d-flex justify-content-end ">
			
			<!-- 접속자와 post의 userId동일할때만 보이기 -->
			<c:if test="${post.userId eq userId}">
			
				<!-- 수정버튼 -->
				<button type="button" class="btn btn-primary ml-2" id="editBtn" data-user-id="${userId}" data-post-id="${post.id}">수정</button>
				
				<!-- 삭제버튼 -->
				<button type="button" class="btn btn-dark ml-2" id="delBtn" data-user-id="${userId}" data-post-id="${post.id}">삭제</button>
				
			</c:if>
			
			</div>
		</div>
		
	</div>
</div>
<script>
	$(document).ready(function(){
		// 이미지 선택시 사진 보여지기
		 $('#file').on('change',function(){
			 setImageFromFile(this, '.postDetailBox');
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
		
		// 수정버튼
		$('#editBtn').on('click',function(){
			let content = $('#content').val();
			let file = $('#file').val();
			let userId = $(this).data('user-id');
			let postId = $(this).data('post-id');
			
			if(file != ''){
				let ext = file.split(".").pop().toLowerCase();
				if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){
					alert("지원하는 확장자가 아닙니다.");
					$('#file').val("");
					return;
				}
			}
			
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);
			formData.append("userId", userId);
			formData.append("postId", postId);
			
			$.ajax({
				
				//request
				type:"PUT"
				,url:"/post/update"
				,data:formData
				,enctype:"multipart/form-data"
				,processData:false
				,contentType:false
				
				//respose
				,success:function(data){
					if(data.code == 1){
						// 성공
						alert("수정 완료되었습니다.");
						document.location.reload(true);
					} else {
						// 실패
						alert("수정 실패.");
					}
				}
				, error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});//->수정버튼 클릭 끝
		
		// 삭제버튼
		$('#delBtn').on('click',function(){
			let userId = $(this).data('user-id');
			let postId = $(this).data('post-id');
			
			$.ajax({
				type:"DELETE"
				,url:"/post/delete"
				,data:{"postId" : postId}
			
				,success:function(data){
					if(data.code == 1){
						alert("삭제에 성공했습니다.");
						location.href="/post/individual_page_view?userId="+userId;
					}
				}
				,error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});//->삭제버튼 끝
	});//->document 끝

</script>