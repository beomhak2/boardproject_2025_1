package com.project.page.board3.dao;

import java.util.List;

import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board3Dao {

	Post item(int postId);

	List<Post> list(Post item);

	void add(Post item);

	void update(Post item);

	void delete(int postId);


}
