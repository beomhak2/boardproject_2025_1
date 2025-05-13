package com.project.page.board1.dao;

import java.util.List;

import com.project.page.board1.model.Board1;

public interface Board1Dao {

	List<Board1> list();

	void add(Board1 item);

	Board1 item(Long bookId);

	void update(Board1 item);

	void delete(Long bookId);

}
