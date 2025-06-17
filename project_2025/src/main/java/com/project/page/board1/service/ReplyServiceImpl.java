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
		if(reply.getParentReplyId() == null) {	// 최상위 댓글
			Integer groupId = replydao.getNextGroupId();
			if(groupId == null) {
				groupId = 1;
			}
			reply.setReplyGroup(groupId.intValue());
			reply.setReplyOrder(0);
		} else {
			Reply parent = replydao.selectReplyById(reply.getParentReplyId());
			
			replydao.updateReplyOrder(parent.getReplyGroup(), parent.getReplyOrder());
			
			reply.setReplyGroup(parent.getReplyGroup());
			reply.setReplyOrder(parent.getReplyOrder() + 1);
		}
		
		// 댓글 삽입
		replydao.insertReply(reply);
	}

	@Override
	public Integer getNextGroupId() {
		return replydao.getNextGroupId();
	}

	@Override
	public int getMaxReplyOrderInGroupAfter(Integer replyGroup, Integer replyOrder) {
		Integer maxOrder = replydao.getMaxReplyOrderInGroupAfter(replyGroup, replyOrder);
		return maxOrder != null ? maxOrder : replyOrder;
	}

	@Override
	public Reply getReplyById(Integer replyId) {
		return replydao.selectReplyById(replyId);
	}

}
