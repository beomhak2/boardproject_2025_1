package com.project.page.board1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.page.board1.service.Board1Service;
import com.project.page.model.Board;
import com.project.page.model.Post; 

@Controller
@RequestMapping("/board1")//board1占쎌뵠占쎌뵬占쎈뮉 雅뚯눘�꺖嚥∽옙 占쎈굶占쎈선占쎌넅占쎌뱽占쎈르占쎌벥 筌ｌ꼶�봺
public class Board1Controller {
	
	@Autowired
	Board1Service service;
	
	@GetMapping("/list")	//占쎈선占쎈폏占쎈립 雅뚯눘�꺖嚥∽옙 占쎈굶占쎈선占쎌넅占쎌뱽占쎈르 �눧�똻毓울옙�뱽 筌ｌ꼶�봺 占쎈막 野껉퍔�뵥揶쏉옙?
	public String list(@RequestParam(required = false) String type,
						@RequestParam(required = false) String keyword,
						Model model) {
		List<Post> list = service.list();
		model.addAttribute("list", list); 
		
		return "board1/list";	// 占쎈퓠 占쎌뿳占쎈뮉 list占쎌뵬占쎈뮉 筌뤿굞臾띰옙�벥 jsp嚥∽옙 獄쏆꼹�넎
	}
	
	@GetMapping("/add")
	public String addGet() { 
		return "board1/add";
	}
	
	@PostMapping("/add")
	public String addPost(Post post) {
		service.add(post);
		return "redirect:/board1/list";
	}

}
