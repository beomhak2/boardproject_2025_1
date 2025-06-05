package com.project.page.board2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.page.board2.model.Pager;
import com.project.page.board2.model.Post;
import com.project.page.board2.service.Board2Service;
import com.project.page.model.Board;

@Controller
@RequestMapping("/board2")//board2이라는 주소로 들어왔을때의 처리
public class Board2Controller {
	
	@Autowired
	Board2Service service;
	
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	String list(Model model, Pager pager) {
		List<Post> list = service.list(pager);
		
		model.addAttribute("list", list);
		
		return "board2/list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/add")
	String add() {
		return "board2/add";
	}
	
	@PostMapping("/add")
	String add(Post item) {
		
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/detail/{postId}")
	String detail(@PathVariable int postId, Model model) {
		Post item = service.item(postId);
		
		model.addAttribute("item", item);
		
		return "board2/detail";
	}
	
	@GetMapping("/detail/update/{postId}")
	String update(@PathVariable int postId, Model model) {
		Post item = service.item(postId);
		
		model.addAttribute("item", item);
		
		return "board2/update";
	}
	
	@PostMapping("/detail/update/{postId}")
	String update(@PathVariable int postId, Post item) {
		item.setPostId(postId);
		
		service.update(item);
		
		return "redirect:../../list";
	}
	
	@GetMapping("/detail/delete/{postId}")
	String delete(@PathVariable int postId) {
		service.delete(postId);
		
		return "redirect:../../list";
	}
}
