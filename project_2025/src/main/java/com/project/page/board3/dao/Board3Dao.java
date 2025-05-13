package com.project.page.board3.dao;

import java.util.List;

import com.project.page.model.Board;

public interface Board3Dao {

	List<Board> list();

	void add(Board item);

	Board item(int boardId);

}
