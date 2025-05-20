package com.project.page.board4.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.model.Board;
import com.project.page.model.Post;

@Repository
public class Board4DaoOracle implements Board4Dao{

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Post> list(Post item) {
		List<Post> list = sql.selectList("board4.list", item);
		
		return list;
	}

	@Override
	public void add(Post item) {
		sql.insert("board4.insert", item);
	}

	@Override
	public Board item(int boardId) {
		return null;
	}

	@Override
	public Post detail(String postId) {
		return sql.selectOne("board4.detail",postId);
	}

	@Override
	public void update(Post post) {
		sql.update("board4.update", post);
	}

	@Override
	public void viewCntUp(String postId) {
		sql.update("board4.viewCntUp", postId);
	}

	@Override
	public void delete(String postId) {
		sql.update("board4.delete", postId);
	}
}
