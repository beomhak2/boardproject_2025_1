package com.project.page.board2.dao;

import java.util.List;

import com.project.page.board2.model.Reply;

public interface Board2ReplyDao {

	//��� ��ȸ
	List<Reply> selectReplyList(int postId);

}
