package com.project.page.board2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board2.dao.Board2ReplyDao;
import com.project.page.board2.model.Reply;

@Service
public class Board2ReplyServiceImpl implements Board2ReplyService {

	@Autowired
	Board2ReplyDao dao;

	//댓글 조회
	@Override
	public List<Reply> selectReplyList(int postId) {
//		List<Reply> replies = dao.selectReplyList(postId);
//		for (Reply item : replies) {
//        	System.out.println("데이터:" + item.getDelYn());
//        	System.out.println("ddd:" + item.getReplyContent());
//        }
		return dao.selectReplyList(postId);
	}

	//댓글 등록
	@Override
	public void insertReply(Reply reply) {
		dao.insertReply(reply);
	}

	//댓글 삭제
	@Override
	public void deleteReply(int replyId) {
		dao.deleteReply(replyId);
	}

	//답글 등록
	@Override
	public void insertReplyAnswer(Reply reply) {
		dao.insertReplyAnswer(reply);
	}

}
