package com.project.page.board1.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.board1.model.Reply;

@Repository
public class ReplyDaoOracle implements ReplyDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public void insertReply(Reply reply) {
		sql.insert("board1.replyInsert", reply);
	}

	@Override
	public List<Reply> selectReply(int postId) {
		return sql.selectList("board1.replySelect", postId);
	}

	@Override
	public int getNextReplyId() {
		return sql.selectOne("board1.getNextReplyId");
	}

	@Override
	public Reply selectReplyById(int replyId) {
		return sql.selectOne("board1.selectReplyById", replyId);
	}

	@Override
	public void updateReplyOrderAfter(int replyGroup, int replyOrder) {
		Map<String, Object> param = new HashMap<>();
		param.put("replyGroup", replyGroup);
		param.put("replyOrder", replyOrder);
		sql.update("board1.updateReplyOrderAfter", param);
	}

}
