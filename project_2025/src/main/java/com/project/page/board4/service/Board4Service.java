package com.project.page.board4.service;

import java.util.List;

import com.project.page.model.Board;

public interface Board4Service {

	List<Board> list();

	void add(Board item);

	Board item(int boardId);


}
