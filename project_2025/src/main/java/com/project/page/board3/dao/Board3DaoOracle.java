package com.project.page.board3.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.model.Board;
import com.project.page.model.Post;

@Repository
public class Board3DaoOracle implements Board3Dao{

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Post> list(Post item) {
		List<Post> list = sql.selectList("board3.list", item);
		
		return list;
	}

	@Override
	public void add(Post item) {
		sql.insert("board3.add",item);
	}

	
	@Override
	public Post item(int postId) {
		return sql.selectOne("board3.item",postId);
	}

	@Override
	public void update(Post item) {
		sql.update("board3.update",item);
	}

	@Override
	public void delete(int postId) {
		sql.delete("board3.delete",postId);
	}

	@Override
	public void viewCnt(int postId) {
		sql.update("board3.viewCnt", postId);
	}
}
