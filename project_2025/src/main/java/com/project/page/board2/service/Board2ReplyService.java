package com.project.page.board2.service;

import java.util.List;

import com.project.page.board2.model.Reply;

public interface Board2ReplyService {

	//��� ��ȸ
	List<Reply> selectReplyList(int postId);

}
