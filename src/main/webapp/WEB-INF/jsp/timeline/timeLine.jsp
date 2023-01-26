<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역 : 로그인 된 상태에서만 보여짐 --%>
		<c:if test="${not empty userId}">
			<div class="write-box border rounded m-3">
				<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
	
				<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
				<div class="d-flex justify-content-between">
					<div class="file-upload d-flex">
					
						<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
						<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
						
						<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
						<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
	
						<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
						<div id="fileName" class="ml-2"></div>
						
					</div>
					<button id="writeBtn" class="btn btn-info">게시</button>
				</div>
			</div>
		</c:if>
		<%--// 글쓰기 영역 끝 --%>

		<%-- 타임라인 영역 --%>
		<c:forEach var="card" items="${cardList}">
			<div class="timeline-box my-5">
				<%-- 카드1 --%>
				<div class="card border rounded mt-3">
					<%-- 글쓴이, 더보기(삭제) --%>
					<div class="p-2 d-flex justify-content-between">
						<span class="font-weight-bold"><a href="/post/individual_page_view?userId=${card.user.id}" class="individualBtn text-dark" data-user-id="${card.user.id }">${card.user.loginId}</a></span>
	
						<%-- 더보기(내가 쓴 글일 떄만 노출) --%>
						<c:if test="${card.user.id eq userId}">
						<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
							<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
						</a>
						</c:if>
					</div>
	
					<%-- 카드 이미지 --%>
					<div class="card-img">
					<c:choose>
						<c:when test="${empty card.post.imgPath}">
							<img src="/static/img/timeline/noimg.gif" class="w-100" alt="본문 이미지">
						</c:when>
						<c:otherwise>
							<img src="${card.post.imgPath}" class="w-100" alt="본문 이미지">
						</c:otherwise>
					</c:choose>
					</div>
	
					<%-- 좋아요 --%>
					<div class="card-like m-3">
						<a href="#" class="like-btn"  data-user-id="${userId}" data-post-id="${card.post.id}">
							<c:choose>
								<%--좋아요가 되어있을때 --%>
								<c:when test="${card.filledLike eq true}">
									<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18" height="18" alt="filled heart">
								</c:when>
								<%--좋아요가 해제되어있을때 --%>
								<c:otherwise>
									<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="empty heart">
								</c:otherwise>
							</c:choose>
								좋아요 ${card.likeCount}개
						</a>
						
						
					</div>
	
					<%-- 글 --%>
					<div class="card-post m-3">
						<span class="font-weight-bold">${card.user.loginId}</span>
						<span>${card.post.content}</span>
					</div>
	
					<%-- 댓글 --%>
					<div class="card-comment-desc border-bottom">
						<div class="ml-3 mb-1 font-weight-bold">댓글</div>
					</div>
	
					<%-- 댓글 목록 --%>
					<div class="card-comment-list m-2">
					
					<%-- 댓글 내용 --%>
					<c:forEach var="commentView" items="${card.commentList}">
						<div class="card-comment m-1">
								<span class="font-weight-bold">${commentView.user.loginId}:</span>
								<span>${commentView.comment.content}</span>
							
								<%-- 댓글 삭제 버튼 --%>
								<a href="#" class="commentDelBtn">
									<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
								</a>
						</div>
					</c:forEach>
						<%-- 댓글 쓰기 --%>
						<c:if test="${not empty userId}">
							<div class="comment-write d-flex border-top mt-2">
								<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"> 
								<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
							</div>
						</c:if>
					</div>
					<%--// 댓글 목록 끝 --%>
				</div>
				<%--// 카드1 끝 --%>
			</div>
		</c:forEach>
		<%--// 타임라인 영역 끝  --%>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<%--modal-sm : 작은 모달 창 --%>
	<%--modal-dialog-centered: 모달 창 수직으로 가운데 정렬 --%>
  <div class="modal-dialog modal-sm modal-dialog-centered">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
    		<a href="#" id="deletePostBtn">삭제하기</a>
    	</div>
    	<div class="py-3">
    		<%-- data-dismiss="modal" 모달 창 닫힘 --%>
    		<a href="#" data-dismiss="modal">취소하기</a>
    	</div>
    </div>
  </div>
</div>

<script>
	$(document).ready(function(){
		//파일 업로드 이미지 클릭 => 숨겨져있는 file을 동작시킴
		$('#fileUploadBtn').on('click',function(e){
			e.preventDefault(); // a태그의 올라가는 현상 방지.(다른 버튼 필요없음)
			$('#file').click(); // input file을 클릭한 것과 같은 효과
		}); //-> 파일업로드 버튼 끝
		
		// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드 된 파일 이름 노출
		$('#file').on('change',function(e){
			// alert("파일 선택");
			let fileName = e.target.files[0].name; // e.target = this
			//alert(fileName); // pictureicon.png
			
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if(ext != 'jpg' && ext != 'jpeg' && ext != 'gif' && ext != 'png') {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val(''); // 파일 태그에 실제 파일 제거
				$('#fileName').text(''); // 파일 이름 비우기
				return;
			}
			// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
			$('#fileName').text(fileName); // 태그 사이에 값을 넣는거 = text
		});//-> 이미지 선택 끝
		
		// 게시버튼 누를때
		$('#writeBtn').on("click",function(){
			//alert(1111);
			let content = $('#writeTextArea').val();
			
			if(content == ''){
				alert("내용을 입력해주세요.");
				return;
			}
			
			let file = $('#file').val();
			//alert(file); //C:\fakepath\pictureicon.png
			
			if(file != ''){
				let ext = file.split(".").pop().toLowerCase();
				if(ext != 'jpg' && ext != 'jpeg' && ext != 'gif' && ext != 'png'){
					alert("이미지 파일만 게시할 수 있습니다.");
					$('#file').val('');
					$('#fileName').text('');
					return;
				}
			}
			
			// ajax 통신
			
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);
			
			$.ajax({
				// request 
				type:"POST"
				, url :"/post/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData : false
				, contentType : false
				// response
				, success:function(data){
					if(data.code == 1){
						alert("게시물이 저장되었습니다.");
						location.href ="/timeline/timeline_view";
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e){
					alert("저장에 실패했습니다.");
				}
			}); //-> 게시버튼 ajax끝			
		});//->게시버튼 끝
		
		// 댓글 쓰기
		$('.comment-btn').on('click',function(){
			//alert(1111);
			let postId = $(this).data('post-id');
			// 지금 클릭된 게시버튼의 형제인 input 태그를 가져온다. (siblings)
			let comment = $(this).siblings('input').val().trim(); // 클릭한 아이와 형제의 input태그값(왜냐면 현재 지금 n개의 값이 있기때문에 id, class의 값으로 지정 불가)
			
			if(comment == '') {
				alert("댓글을 입력해주세요.");
				return;
			}
			
			$.ajax({
				type:"POST"
				,url:"/comment/create"
				,data:{"postId":postId, "content":comment}
				,success:function(data){
					if(data.code == 100){
						document.location.reload(true);
					} else if(data.code == 500) {
						alert(data.result);
						location.href ="/user/sign_in_view";
					} else {
						alert(data.errorMessage);
					}
				}
				,error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});//-> 댓글 ajax통신
			
		});//->댓글게시 버튼 끝
		
		// 좋아요 버튼 누르기
		$('.like-btn').on('click',function(e){
			e.preventDefault();
			//alert(1111);
			let postId = $(this).data('post-id');
			//alert(postId);
			let userId = $(this).data('user-id');
			//alert(userId);
			if (userId == '') {
				alert("로그인을 해주세요");
				return;
			}
			
			$.ajax({
				// requset
				url:"/like/" + postId
				
				// response
				,success:function(data){
					if(data.code == 500){
						alert(data.errorMessage);
						location.href ="/user/sign_in_view";
					} else {
						document.location.reload(true);
					} 
				}
				,error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});//->좋아요 버튼 끝
		
		// 더보기 버튼(...) 클릭 (글 삭제)
		$('.more-btn').on('click',function(e){
			e.preventDefault();
			
			let postId = $(this).data('post-id'); // getting
			//alert(postId);
			
			$('#modal').data('post-id', postId); // setting 모달 태그에 data-post-id를 심어 넣어줌
		});//-> 더보기 버튼 끝
		
		// 모달 안에있는 삭제하기 버튼 클릭
		$('#modal #deletePostBtn').on('click',function(e){
			e.preventDefault();
			
			let postId = $('#modal').data('post-id');
			//alert(postId);
			
			// ajax 글 삭제
			$.ajax({
				type:"DELETE"
				,url:"/post/delete"
				,data:{"postId" : postId}
			
				,success:function(data){
					if(data.code == 1){
						alert("삭제에 성공했습니다.");
						location.href="/timeline/timeline_view"
					}
				}
				,error:function(jqXHR, textStatus, errorThrown){
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});//-> 모달 삭제버튼 끝
		
	});//-> 도큐먼트 끝
</script>
