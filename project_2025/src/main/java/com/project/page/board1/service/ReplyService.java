package com.project.page.board1.service;

import java.util.List;

import com.project.page.board1.model.Reply;

public interface ReplyService {

	List<Reply> list(int postId);

	void add(Reply reply);

	


	
}
