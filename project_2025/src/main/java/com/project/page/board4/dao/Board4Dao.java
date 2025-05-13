package com.project.page.board4.dao;

import java.util.List;

import com.project.page.model.Board;

public interface Board4Dao {

	List<Board> list();

	void add(Board item);

	Board item(int boardId);

}
