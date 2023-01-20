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
		</div>
		
		<!-- 작성글 박스 -->
		<textarea class="form-control" id="content" placeholder="내용을 입력하세요." rows="3">${post.content}</textarea>
		
		<!-- 버튼 -->
		<div class="d-flex justify-content-between my-3">
			<a href="/user/individual_page_view" class="btn btn-info">뒤로가기</a>
			<div class="d-flex justify-content-end ">
				<button type="button" class="btn btn-primary ml-2">수정</button>
				<button type="button" class="btn btn-dark ml-2">삭제</button>
			</div>
		</div>
		
	</div>
</div>