package com.project.page.board1.dao;

import java.util.List;

import com.project.page.board1.model.Reply;

public interface ReplyDao {

	List<Reply> selectReply(int postId);
	
	void insertReply(Reply reply);

	Integer getNextGroupId();

	Reply selectReplyById(int replyId);

	void updateReplyOrder(Integer replyGroup, Integer replyOrder);

	int getMaxReplyOrderInGroupAfter(Integer replyGroup, Integer replyOrder);

}
