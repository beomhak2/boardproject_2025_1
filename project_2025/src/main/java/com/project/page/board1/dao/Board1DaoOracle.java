package com.project.page.board1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.board1.model.Pager;
import com.project.page.model.Post;

@Repository 
public class Board1DaoOracle implements Board1Dao{

	@Autowired
	private SqlSession sql;
	
	/**
	 * 게시글 목록 조회
	 */
	@Override
	public List<Post> selectPostList(Pager pager) { 
		List<Post> list = sql.selectList("board1.selectPostList", pager);
		
		return list;
	}
	
	/**
	 * 게시글 페이지
	 */
	@Override
	public int selectPostCount(Pager pager) {
		return sql.selectOne("board1.selectPostCount", pager);
	}

	/**
	 * 게시글 입력
	 */
	@Override
	public void insertPost(Post post) {
		sql.insert("board1.insertPost", post);
	}

	/**
	 * 게시글 수정
	 */
	@Override
	public int updatePost(Post post) {
		return sql.update("board1.updatePost", post);
	}

	/**
	 * 게시글 아이디 찾기
	 */
	@Override
	public Post selectPostById(int postId) {
		return sql.selectOne("board1.selectPostById", postId);
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public void deletePost(int postId) {
		sql.delete("board1.deletePostY", postId);
	}

	/**
	 * 게시글 조회수
	 */
	@Override
	public void increaseViewCount(int postId) {
		sql.update("board1.increaseViewCount", postId);
	}
}
