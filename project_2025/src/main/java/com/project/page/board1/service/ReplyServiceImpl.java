package com.project.page.board1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board1.dao.ReplyDao;
import com.project.page.board1.model.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired private ReplyDao replydao;
	
	@Override
	public List<Reply> list(int postId) {
		return replydao.selectReply(postId);
	}
	
	@Override
	public void add(Reply reply) {
		if(reply.getReplyOrder() == 0) {
			long groupId = replydao.getNextGroupId();
			reply.setReplyGroup(groupId);
			reply.setReplyClass(0);
		}else {
			reply.setReplyClass(1);
		}
		replydao.insertReply(reply);
	}

}
