package com.project.page.board3.dao;

import java.util.List;

import com.project.page.board3.pager.Pager;
import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board3Dao {

	Post item(int postId);


	void add(Post item);

	void update(Post item);

	void delete(int postId);

	void viewCnt(int postId);

	int total(Pager pager);

	List<Post> list(Pager pager);


}
