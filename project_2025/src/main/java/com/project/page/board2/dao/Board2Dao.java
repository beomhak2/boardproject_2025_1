package com.project.page.board2.dao;

import java.util.List;

import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board2Dao {

	List<Post> list();

	void add(Post item);

	Post item(int postId);

	void update(Post item);

	void delete(int postId);

}
