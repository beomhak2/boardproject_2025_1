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
	
	//´ñ±Û Á¶È¸
	@Override
	public List<Reply> list(Reply reply) {
		return dao.list(reply);
	}

	//´ñ±Û µî·Ï
//	@Override
//	public void insert(Reply reply) {
//		dao.insert(reply);
//	}

}
