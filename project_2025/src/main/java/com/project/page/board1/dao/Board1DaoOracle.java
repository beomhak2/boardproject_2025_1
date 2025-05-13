package com.project.page.board1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.page.board1.model.Board1;

@Repository
public class Board1DaoOracle implements Board1Dao{

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Board1> list() {
		List<Board1> list = sql.selectList("board1.list");
		
		return list;
	}

	@Override
	public void add(Board1 item) {
		
	}

	@Override
	public Board1 item(Long bookId) {
		return null;
	}

	@Override
	public void update(Board1 item) {
		
	}

	@Override
	public void delete(Long bookId) {
		
	}

}
