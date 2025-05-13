package com.project.page.board2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board2.dao.Board2Dao;
import com.project.page.model.Board;

@Service
public class Board2ServiceImpl implements Board2Service {

	@Autowired
	Board2Dao dao;
	
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
