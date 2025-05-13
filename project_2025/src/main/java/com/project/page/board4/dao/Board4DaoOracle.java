package com.project.page.board4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.model.Board;

@Repository
public class Board4DaoOracle implements Board4Dao{

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Board> list() {
		List<Board> list = sql.selectList("board4.list");
		
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
