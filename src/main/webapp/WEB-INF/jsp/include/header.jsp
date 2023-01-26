<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex align-items-center justify-content-between w-100">
	<!-- 로고 영역 -->
	<div>
		<h1><a href="/timeline/timeline_view" id="headerLogo" class="text-dark">kostargram</a></h1>
	</div>
	
	<!-- 로그인 정보 : 로그인시 보일수 있게 -->

	<c:if test="${not empty userId}">
		<div class="mr-3">
			<span><a href="/post/individual_page_view?userId=${userId}" class="text-dark font-weight-bold">${userName}</a>님 안녕하세요.</span> 
			<a href="/user/sign_out" class="ml-2 font-weight-bold">로그아웃</a>
		</div>
	</c:if>
	
	<c:if test="${empty userId}">
		<div class="mr-3">
			<a href="/user/sign_in_view" class="font-weight-bold">로그인</a>
		</div>
	</c:if>
	
</div>