package com.project.page.board1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.board1.model.Board1;
import com.project.page.model.Board;

@Repository
public class Board1DaoOracle implements Board1Dao{

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Board> list() {
		List<Board> list = sql.selectList("board1.list");
		
		return list;
	}

	@Override
	public void add(Board item) {
		
	}

	@Override
	public Board item(int boardId) {
		return null;
	}
}
