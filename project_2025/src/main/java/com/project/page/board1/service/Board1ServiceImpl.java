package com.project.page.board1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.page.board1.dao.Board1Dao;
import com.project.page.model.Board;
import com.project.page.model.Post;

@Service
public class Board1ServiceImpl implements Board1Service {

	@Autowired
	Board1Dao dao;
	
	@Override
	public List<Post> list() {
		return dao.list();
	}
 
	@Override
	public void writePost(Post post) { 
		dao.insertPost(post);
	}

	@Override
	public int updatePost(Post post) {
		return dao.updatePost(post);
	}

	@Override
	public Post getPostById(int postId) {
		return dao.selectPostById(postId);
	}

	@Override
	public void deletePost(int postId) {
		dao.deletePost(postId);
	}

	@Override
	public void increaseViewCount(int postId) {
		dao.increaseViewCount(postId);
	}
}