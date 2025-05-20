package com.project.page.board4.dao;

import java.util.ArrayList;
import java.util.List;

import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board4Dao {

	List<Post> list(Post item);

	void add(Post item);

	Board item(int boardId);

	Post detail(String postId);

	void update(Post post);

	void viewCntUp(String postId);

	void delete(String postId);

}
