<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center w-100 my-3">
	<div class="edit-box">
		<!-- 이미지 박스 -->
		<div class="d-flex justify-content-center">
			<c:choose>
				<c:when test="${empty user.profileImgPath}">
					<img alt="프로필 사진" src="/static/img/timeline/profileImg.png" class="profileBox">
				</c:when>
				<c:otherwise>
					<img alt="프로필 사진" src="${user.profileImgPath}" class="profileBox">
				</c:otherwise>
			</c:choose>
		</div>
		
		<!-- 파일 선택 버튼 -->
		<div class="d-flex justify-content-end">
			<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
			<button class="btn-sm btn-primary">사진 선택</button>
			<div id="fileName" class="ml-2"></div>
		</div>
		
		<!-- input박스 -->
		<div class="d-flex justify-content-center">
			<div class="w-75 my-3">
				<input type="text" class="form-control" placeholder="변경할 이름을 입력해주세요." value="${user.name}">
				<input type="text" class="form-control mt-3" placeholder="상태메세지를 입력해주세요." value="${user.statusMessage}">
			</div>
		</div>
		
		<!-- 버튼 -->
		<div class="buttonBox my-2">
				<button class="btn btn-warning w-75" id="profileEditBtn">프로필 수정</button>
				<button class="btn btn-secondary w-75 my-2" id="exitUserBtn">회원탈퇴</button>
		</div>
		
	</div>
</div>