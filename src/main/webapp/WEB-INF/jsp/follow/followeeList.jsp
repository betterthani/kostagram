<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center my-5">
	<div class="text-center w-50">
		<h3>팔로잉</h3>
		<table class="table">
			<tbody>
				<c:forEach var="followeeUser" items="${FolloweeUserInfo}">
					<tr>
						<!-- 프로필 사진 -->
						<c:choose>
							<c:when test="${empty followeeUser.user.profileImgPath}">
								<td>
									<img alt="프로필 사진 없을때" src="/static/img/timeline/profileImg.png" width="20" height="20">
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<img alt="프로필 사진" src="${followeeUser.user.profileImgPath}" width="20" height="20">
								</td>
							</c:otherwise>
						</c:choose>
						
						<!-- 유저 이름 -->
						<td>
							<a href="/post/individual_page_view?userId=${followeeUser.user.id}" class="text-secondary font-weight-bold">${followeeUser.user.name}</a>
						</td>
						
						<!-- 팔로잉 버튼 누를시 팔로우 취소됨 -->
						<td>
							<button type="button" class="btn btn-secondary followBtn" data-user-id="${followeeUser.user.id}" data-session-id="${userId}">팔로잉</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script>
	$(document).ready(function(){
		$('.followBtn').on('click',function(){
			let userId = $(this).data('user-id');
			let sessionId = $(this).data('session-id');
			//alert(sessionId);
			
			$.ajax({
				url:"/follow/"+userId
				,data:{"userId":userId, "sessionId":sessionId}
				,success:function(data){
					if(data.code == 1){
						document.location.reload();
					} else {
						alert("실패, 관리자에 문의하세요.");
					}
					
				}
				,error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});
	});
</script>