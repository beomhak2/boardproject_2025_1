package com.project.page.board1.service;

import java.util.List;

import com.project.page.board1.model.Pager;
import com.project.page.model.Post;

public interface Board1Service {
	// 게시판 목록
	List<Post> getPostList(Pager pager);
	// 게시판 페이지
	int getPostCount(Pager pager);
	// 게시판 작성
	void writePost(Post post);
	// 게시판 아이디
	Post getPostById(int postId);
	// 게시판 업데이트
	int updatePost(Post post);
	// 게시판 삭제
	void deletePost(int postId);
	// 죄회수 증가
	void increaseViewCount(int postId);

	
}
