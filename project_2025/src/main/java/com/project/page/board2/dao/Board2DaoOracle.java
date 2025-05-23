package com.project.page.board2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.model.Board;
import com.project.page.model.Post;

@Repository
public class Board2DaoOracle implements Board2Dao{

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Post> list() {
		List<Post> list = sql.selectList("board2.list");
		
		return list;
	}

	@Override
	public void add(Post item) {
		sql.insert("board2.add", item);
	}

	@Override
	public Post item(int postId) {
		return sql.selectOne("board2.item", postId);
	}

	@Override
	public void update(Post item) {
		sql.update("board2.update", item);
	}

	@Override
	public void delete(int postId) {
		sql.delete("board2.delete", postId);
	}

}
