package com.project.page.board3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board3.dao.Board3Dao;
import com.project.page.model.Board;
import com.project.page.model.Post;

@Service
public class Board3ServiceImpl implements Board3Service {

	@Autowired
	Board3Dao dao;

	@Override
	public List<Post> list(Post item) {
		return dao.list(item);
	}

	@Override
	public void add(Post item) {
		dao.add(item);
	}

	@Override
	public void update(Post item) {
		dao.update(item);
	}

	@Override
	public Post item(int postId) {
		return dao.item(postId);
	}

	@Override
	public void delete(int postId) {
		dao.delete(postId);
	}

	@Override
	public void viewCnt(int postId) {
		dao.viewCnt(postId);
	}

}
