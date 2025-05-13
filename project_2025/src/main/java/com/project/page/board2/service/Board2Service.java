package com.project.page.board2.service;

import java.util.List;

import com.project.page.board1.model.Board1;
import com.project.page.model.Board;

public interface Board2Service {

	List<Board> list();

	void add(Board item);

	Board item(int boardId);


}
