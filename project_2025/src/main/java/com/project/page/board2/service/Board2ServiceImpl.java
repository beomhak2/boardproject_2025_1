package com.project.page.board2.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board2.dao.Board2Dao;
import com.project.page.board2.model.Pager;
import com.project.page.board2.model.Post;

@Service
public class Board2ServiceImpl implements Board2Service {

	@Autowired
	Board2Dao dao;

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
	public Post item(int postId) {
		//조회수 증가
		dao.updateViewCnt(postId);
		
		return dao.item(postId);
	}

	@Override
	public void update(Post item) {
		dao.update(item);
	}

	@Override
	public void delete(int postId) {
		dao.delete(postId);
	}
	
	public int getReplyCount(int postId) {
		return dao.replyCount(postId);
	}

}
