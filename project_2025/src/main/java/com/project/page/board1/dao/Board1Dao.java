package com.project.page.board1.dao;

import java.util.List;

import com.project.page.board1.model.Pager;
import com.project.page.board1.model.Post;

public interface Board1Dao {
	// 게시글 목록 조회
	List<Post> selectPostList(Pager pager);
	// 게시글 페이지
	int selectPostCount(Pager pager);
	// 게시글 입력
	void insertPost(Post post);
	// 게시글 수정
	int updatePost(Post post);
	// 게시글 postId 가져오기
	Post selectPostById(int postId);
	// 게시글 삭제
	void deletePost(int postId);
	// 게시글 조회수
	void increaseViewCount(int postId);
	
}  
   
