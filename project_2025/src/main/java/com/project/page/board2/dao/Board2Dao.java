package com.project.page.board2.dao;

import java.util.List;

import com.project.page.model.Board;

public interface Board2Dao {

	List<Board> list();

	void add(Board item);

	Board item(int boardId);

}
