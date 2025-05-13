package com.project.page.board1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board1.dao.Board1Dao;
import com.project.page.model.Board;

@Service
public class Board1ServiceImpl implements Board1Service {

	@Autowired
	Board1Dao dao;
	
	@Override
	public List<Board> list() {
		return dao.list();
	}

	@Override
	public void add(Board item) {
		dao.add(item);
	}

	@Override
	public Board item(int boardId) {
		return dao.item(boardId);
	}
}
