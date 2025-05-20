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
	SqlSession sql;
	
	@Override
	public List<Post> list() {
		List<Post> list = sql.selectList("board1.list");
		
		return list;
	}

	@Override
	public void add(Post item) {
		sql.insert("board1.add", item);
	}

	@Override
	public Post item(int postId) {
		return sql.selectOne("board1.item", postId);
	}
}
