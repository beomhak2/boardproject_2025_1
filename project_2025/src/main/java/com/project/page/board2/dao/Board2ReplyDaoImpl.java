package com.project.page.board2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.board2.model.Reply;

@Repository
public class Board2ReplyDaoImpl implements Board2ReplyDao {

	@Autowired
	SqlSession sql;

	//댓글 조회
	@Override
	public List<Reply> selectReplyList(int postId) {
		return sql.selectList("board2.selectReplyList", postId);
	}

	//댓글 등록
	@Override
	public void insertReply(Reply reply) {
		if (reply == null || reply.getPostId() == 0 || reply.getReplyContent() == null) {
	        throw new IllegalArgumentException("댓글 데이터 누락");
	    }
		sql.insert("board2.insertReply", reply);
	}

	//댓글 삭제
	@Override
	public void deleteReply(int replyId) {
		sql.delete("board2.deleteReply", replyId);
	}
	
	//답글 등록
	@Override
	public void insertReplyAnswer(Reply reply) {
		sql.insert("board2.insertReplyAnswer", reply);
	}
	
}
