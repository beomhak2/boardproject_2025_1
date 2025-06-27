package com.project.page.board2.service;

import java.util.List;

import com.project.page.board2.model.Pager;
import com.project.page.board2.model.Post;

public interface Board2Service {

	List<Post> list(Pager pager);

	void add(Post item);

	Post item(int postId);

	void update(Post item);

	void delete(int postId);

	int getReplyCount(int postId);
}
