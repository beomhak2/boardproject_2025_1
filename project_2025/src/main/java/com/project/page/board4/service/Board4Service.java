package com.project.page.board4.service;

import java.util.List;

import com.project.page.board4.model.Pager;
import com.project.page.model.Board;
import com.project.page.board4.model.Post;
import com.project.page.model.Reply;

public interface Board4Service {

	List<Post> list(Pager pager);

	void add(Post item);

	Board item(int boardId);

	Post detail(String postId);

	void update(Post post);

	void viewCntUp(String postId);

	void delete(String postId);

	int replyInsert(Reply reply);

	List<Reply> getReplyList(Reply reply);

	void dummy();

	int replyDelete(Reply reply);



}
