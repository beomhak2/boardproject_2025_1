package com.project.page.board2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board2.dao.Board2Dao;
import com.project.page.model.Board;
import com.project.page.model.Post;

@Service
public class Board2ServiceImpl implements Board2Service {

	@Autowired
	Board2Dao dao;
	
	@Override
	public List<Post> list() {
		return dao.list();
	}

	@Override
	public void add(Post item) {
		dao.add(item);
	}

	@Override
	public Post item(int postId) {
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
