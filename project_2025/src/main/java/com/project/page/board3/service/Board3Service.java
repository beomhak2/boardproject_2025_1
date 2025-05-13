package com.project.page.board3.service;

import java.util.List;

import com.project.page.model.Board;

public interface Board3Service {

	List<Board> list();

	void add(Board item);

	Board item(int boardId);


}
