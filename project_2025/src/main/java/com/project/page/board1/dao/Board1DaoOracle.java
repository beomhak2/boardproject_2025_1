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
	public void add(Post post) {
		sql.insert("board1.insert", post);
	}


}
