package com.project.page.board4.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board4.dao.Board4Dao;
import com.project.page.board4.model.Pager;
import com.project.page.model.Board;
import com.project.page.board4.model.Post;
import com.project.page.model.Reply;

@Service
public class Board4ServiceImpl implements Board4Service {

	@Autowired
	Board4Dao dao;
	
	@Override
	public List<Post> list(Pager pager) {
		int total = dao.total(pager);
		
		pager.setTotal(total);
		
		
		return dao.list(pager);
	}

	@Override
	public void add(Post item) {
		dao.add(item);
	}

	@Override
	public Board item(int boardId) {
		return dao.item(boardId);
	}

	@Override
	public Post detail(String postId) {
		return dao.detail(postId);
	}

	@Override
	public void update(Post post) {
		dao.update(post);
	}

	@Override
	public void viewCntUp(String postId) {
		dao.viewCntUp(postId);
	}

	@Override
	public void delete(String postId) {
		dao.delete(postId);
	}

	@Override
	public int replyInsert(Reply reply) {
		return dao.replyInsert(reply);
	}

	@Override
	public List<Reply> getReplyList(Reply reply) {
		return dao.getReplyList(reply);
	}

	@Override
	public void dummy() {
		List<Integer> dummyList = new ArrayList<Integer>(); 
		for (int i = 0; i < 100; i++) {
			dummyList.add(i);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dummySeqList", dummyList);
		
		
		dao.dummy(map);
	}

	@Override
	public int replyDelete(Reply reply) {
		return dao.replyDelete(reply);
	}

}
