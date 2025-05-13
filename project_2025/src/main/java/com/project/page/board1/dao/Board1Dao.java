package com.project.page.board1.dao;

import java.util.List;

import com.project.page.model.Board;

public interface Board1Dao {

	List<Board> list();

	void add(Board item);

	Board item(int boardId);

}
