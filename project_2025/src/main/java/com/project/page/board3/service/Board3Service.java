package com.project.page.board3.service;

import java.util.List;

import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board3Service {

	List<Post> list(Post item);

	void add(Post item);

	void update(Post item);

	Post item(int postId);

	void delete(int postId);

	void viewCnt(int postId);


}
