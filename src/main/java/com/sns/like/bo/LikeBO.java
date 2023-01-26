package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	
	// 좋아요 했는지 조회
	public boolean existLike(Integer userId, int postId) { // 비로그인일 경우의 수도 넣기
		if(userId == null) {// 비로그인
			return false; // 채워지지 않는 하트 보내기
		}
		//return likeDAO.existLike(userId, postId); // 로그인에 대해 처리
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0 ? true: false;
	}
	
	// 좋아요 카운트
	public int getLikeCountByPostId(int postId) {
		//return likeDAO.selectLikeCountByPostId(postId);
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	// 좋아요 클릭
	public void likeToggle(int postId, int userId) {
		// 좋아요 있는지 확인
		if (likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			// 있으면 제거
			likeDAO.deleteLikeByUserIdPostId(userId, postId);
		} else {
			// 없으면 추가
			likeDAO.insertLikeByUserIdPostId(userId, postId);
		}
	}
	
	// 글 삭제(회원탈퇴시)
	public void deleteByUserId(int userId) {
		likeDAO.deleteByUserId(userId);
	}
	
	// 글 삭제(타임라인)
	public void deleteByPostIdUserId(int postId, int userId) {
		likeDAO.deleteByPostIdUserId(postId, userId);
	}
	
	
	

}
