package com.project.page.board1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.model.Board;
import com.project.page.model.Post;

@Repository 
public class Board1DaoOracle implements Board1Dao{

	@Autowired
	private SqlSession sql;
	
	@Override
	public List<Post> list() {
		return sql.selectList("board1.selectPostList");
	}

	@Override
	public void insertPost(Post post) {
		sql.insert("board1.insertPost", post);
	}

	@Override
	public int updatePost(Post post) {
		return sql.update("board1.updatePost", post);
	}

	@Override
	public Post selectPostById(int postId) {
		return sql.selectOne("board1.selectPostById", postId);
	}

	@Override
	public void deletePost(int postId) {
		sql.delete("board1.deletePost", postId);
	}

	@Override
	public void increaseViewCount(int postId) {
		sql.update("board1.increaseViewCount", postId);
		
		
	}
}
