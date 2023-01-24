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
		
		<!-- 작성글 박스 -->
		<textarea class="form-control" id="content" placeholder="내용을 입력하세요." rows="3">${post.content}</textarea>
		<!-- 파일 버튼 -->
		<div class="d-flex justify-content-end my-1">
			<input type="file" id="file" accept=".jpg, .png, .jpeg, .gif" class="files">
		</div>
		
		<!-- 버튼 -->
		<div class="d-flex justify-content-between my-3">
			<a href="/user/individual_page_view" class="btn btn-info">뒤로가기</a>
			<div class="d-flex justify-content-end ">
				<button type="button" class="btn btn-primary ml-2" id="editBtn">수정</button>
				<button type="button" class="btn btn-dark ml-2" id="delBtn">삭제</button>
			</div>
		</div>
		
	</div>
</div>
<script>
	$(document).ready(function(){
		// 프리뷰
		/* $('#file').on('change',function(){
			//alert(111);
			setImageFromFile(this, '#previewImg');
		});//->프리뷰 끝
		function setImageFromFile(input, expression) {
		    if (input.files && input.files[0]) {
		    var reader = new FileReader();
		    reader.onload = function (e) {
		    $(expression).attr('src', e.target.result);
		  }
		  reader.readAsDataURL(input.files[0]);
		  }
		} */
		
		// 수정버튼
		$('#editBtn').on('click',function(){
			//alert(111);
			
		});//->수정버튼 클릭 끝
	});//->document 끝

</script>