<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center my-5">
	<div class="text-center w-50">
		<h3>팔로워</h3>
		<table class="table">

			<tbody>
				<c:forEach var="followerUser" items="${FollowerUserInfo}">
					<tr>
					
						<!-- 프로필 사진 -->
						<c:choose>
							<c:when test="${empty followerUser.user.profileImgPath}">
								<td>
									<img alt="프로필 사진 없을때" src="/static/img/timeline/profileImg.png" width="20" height="20">
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<img alt="프로필 사진" src="${followerUser.user.profileImgPath}" width="20" height="20">
								</td>
							</c:otherwise>
						</c:choose>
						
						<td>
							<a href="/post/individual_page_view?userId=${followerUser.user.id}" class="text-dark font-weight-bold">${followerUser.user.name}</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>