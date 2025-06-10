package com.project.page.board1.dao;

import java.util.List;

import com.project.page.board1.model.Reply;

public interface ReplyDao {

	List<Reply> selectReply(int postId);
	
	void insertReply(Reply reply);

	long getNextGroupId();

}
