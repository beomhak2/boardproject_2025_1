package com.project.page.board4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board4.dao.Board4Dao;
import com.project.page.model.Board;
import com.project.page.model.Post;

@Service
public class Board4ServiceImpl implements Board4Service {

	@Autowired
	Board4Dao dao;
	
	@Override
	public List<Post> list(Post item) {
		return dao.list(item);
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

}
