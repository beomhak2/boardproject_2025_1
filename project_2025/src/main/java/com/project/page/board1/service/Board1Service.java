package com.project.page.board1.service;

import java.util.List;

import org.springframework.ui.Model;

import com.project.page.model.Board;
import com.project.page.model.Post;

public interface Board1Service {

	List<Post> list();
  
	void writePost(Post post);
 
}
