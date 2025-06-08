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
	
	//´ñ±Û Á¶È¸
	@Override
	public List<Reply> list(Reply reply) {
		return sql.selectList("board2.replyList", reply);
	}

	//´ñ±Û µî·Ï
//	@Override
//	public void insert(Reply reply) {
//		sql.insert("board2.replyInsert", reply);
//	}

}
