package com.project.page.board2.service;

import java.util.List;

import com.project.page.board2.model.Reply;

public interface Board2ReplyService {

	//댓글 조회
	List<Reply> selectReplyList(int postId);

}
