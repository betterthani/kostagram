package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// 로그인이 되지 않은 사람도 카드 목록 보이게 설정.
	// view용 객체 가공
	public List<CardView> generateCardList(){
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록 가져오기 (post)
		List<Post> postList = postBO.getPostList();
		
		// postList 반복문 => CardView => cardViewList에 넣는다.
		for(Post post : postList) {
			CardView card = new CardView();
			// 글
			card.setPost(post);
			
			// 글쓴이
			User user = userBO.getUserByUserId(post.getUserId());
			card.setUser(user);
			
			// 글 하나에 해당하는 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());// 글번호
			card.setCommentList(commentList);
			
			// 내가 좋아요를 눌렀는지 filledLike
			boolean filledLike = likeBO.getLikeByUserIdPostId(post.getUserId(), post.getId());
			card.setFilledLike(filledLike);
			
			// 좋아요 개수
			int likeCount = likeBO.getLikeByPostId(post.getId());
			card.setLikeCount(likeCount);
			
			// 카드 리스트에 채우기 !!!!!!!!!!!
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}
