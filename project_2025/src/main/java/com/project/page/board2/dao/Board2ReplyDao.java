package com.project.page.board2.dao;

import java.util.List;

import com.project.page.board2.model.Reply;

public interface Board2ReplyDao {

	//댓글 조회
	List<Reply> selectReplyList(int postId);

	//댓글 등록
	void insertReply(Reply reply);

	//댓글 삭제
	void deleteReply(int replyId);

	//답글 등록
	void insertReplyAnswer(Reply reply);
	
}
