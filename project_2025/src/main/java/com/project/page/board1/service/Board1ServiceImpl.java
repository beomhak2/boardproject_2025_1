package com.project.page.board1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board1.dao.Board1Dao;
import com.project.page.board1.model.Pager;
import com.project.page.model.Post;

@Service
public class Board1ServiceImpl implements Board1Service {

	@Autowired
	Board1Dao dao;
	
	/**
	 * 모든 게시글 조회 및 조회수 설정
	 */
	@Override
	public List<Post> getPostList(Pager pager) {
		return dao.selectPostList(pager);
	}
	
	/**
	 * 페이징 숫자 가져오기
	 */
	@Override
	public int getPostCount(Pager pager) {
		return dao.selectPostCount(pager);
	}
 
	/**
	 * 새 게시글 등록합니다.
	 */
	@Override
	public void writePost(Post post) { 
		dao.insertPost(post);
	}

	/**
	 * 게시글 수정합니다.
	 */
	@Override
	public int updatePost(Post post) {
		return dao.updatePost(post);
	}

	/**
	 * 게시글 postId 찾기
	 */
	@Override
	public Post getPostById(int postId) {
		return dao.selectPostById(postId);
	}

	/**
	 * 게시글 삭제(del_yn = 'Y')로 변경
	 */
	@Override
	public void deletePost(int postId) {
		dao.deletePost(postId);
	}

	/**
	 * 조회수 증가
	 */
	@Override
	public void increaseViewCount(int postId) {
		dao.increaseViewCount(postId);
	}
}
