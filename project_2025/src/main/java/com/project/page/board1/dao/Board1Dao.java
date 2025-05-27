package com.project.page.board1.dao;

import java.util.List;

import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board1Dao {

	List<Post> list();

	void insertPost(Post post);

	int updatePost(Post post);

	Post selectPostById(int postId);

	void deletePost(int postId);

	void increaseViewCount(int postId);

	
}  
   