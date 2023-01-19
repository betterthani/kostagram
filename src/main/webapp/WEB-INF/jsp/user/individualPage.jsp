<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center my-3">
	<div class="contents-box border rounded ">
		<!-- 프로필 상단 -->
		<div class="individualBox">
		
			<div class="d-flex align-items-center justify-content-around pt-2">
				<!-- 프로필 이미지 -->
				<div class="mini_box">
					<img alt="프로필" src="/static/img/timeline/profileImg.png" class="roundBox">
					<div>사용자 이름</div>
					<div id="introduceText">소개글</div>
				</div>
				
				<!-- 게시글 개수 -->
				<div class="mini_box">
					<div>게시글</div>
					<div>3</div>
				</div>
				
				<!-- 팔로워 -->
				<div class="mini_box">
					<div>팔로워</div>
					<div>3</div>
				</div>
				
				<!-- 팔로잉 -->
				<div class="mini_box">
					<div>팔로잉</div>
					<div>3</div>
				</div>
			</div>
		
		</div>

		<!-- 프로필 수정 버튼 -->
		<div class="profileEditBtnBox d-flex justify-content-center align-items-center">
			<a href="#" class="btn btn-dark w-50">프로필 수정</a>			
		</div>
		
		<!-- 이미지 박스  -->
		<div class="individualImgBox d-flex flex-wrap py-5">
			<img alt="기본이미지" src="/static/img/timeline/noimg.gif" class="imgBox">
		</div>

	</div>
</div>