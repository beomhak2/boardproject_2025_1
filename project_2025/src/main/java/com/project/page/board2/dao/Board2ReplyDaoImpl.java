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
	
}
