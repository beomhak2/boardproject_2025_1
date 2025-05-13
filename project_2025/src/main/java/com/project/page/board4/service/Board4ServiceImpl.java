package com.project.page.board4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board4.dao.Board4Dao;
import com.project.page.model.Board;

@Service
public class Board4ServiceImpl implements Board4Service {

	@Autowired
	Board4Dao dao;
	
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
