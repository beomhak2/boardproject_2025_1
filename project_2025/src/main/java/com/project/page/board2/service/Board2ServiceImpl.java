package com.project.page.board2.service;

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
	public void dummy() {
		for(int postId = 18; postId < 100; postId++) {
			Post item = new Post();
			
			item.setPostId(postId);
			item.setTitle("제목 " + postId);
			item.setPostContent("내용 " + postId);
			item.setRegDt(null);
			item.setBoardId(2);
			item.setUserId("abc123");
			
			dao.add(item);
		}
	}
	
	@Override
	public void init() {
		for(int postId = 18; postId < 100; postId++) {
			dao.delete(postId);
		}
	}
	
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

}
