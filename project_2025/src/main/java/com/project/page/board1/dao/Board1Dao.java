package com.project.page.board1.dao;

import java.util.List;

import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board1Dao {

	List<Post> list();

	void add(Post item);

	Post item(int postId);

	void insert(String string, Post item);


}  
