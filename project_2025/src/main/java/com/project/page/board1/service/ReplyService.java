package com.project.page.board1.service;

import java.util.List;

import com.project.page.board1.model.Reply;
import com.project.page.board1.model.ReplyDTO;

public interface ReplyService {

	List<ReplyDTO> getCommentTreeByPostId(int postId) throws Exception;

	Reply writeReply(Reply reply) throws Exception;
	
}
