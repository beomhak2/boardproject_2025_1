package com.project.page.board4.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.page.board4.service.Board4Service;
import com.project.page.model.Post;

@Controller
@RequestMapping("/board4")//board3이라는 주소로 들어왔을때의 처리
public class Board4Controller {
	
	@Autowired
	Board4Service service;
	
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	String list(Post post,Model model) {
		List<Post> list = service.list(post);
		
		model.addAttribute("list", list);
		
		model.addAttribute("msg" ,"list");
		
		return "board4/list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/detail")
	String detail(@RequestParam String postId,Model model) {
		
		Post postDetail =  service.detail(postId);
		
		service.viewCntUp(postId);
		
		model.addAttribute("vo", postDetail);
		
		return "board4/detail";
	}
	
	@GetMapping("/add")
	String addGet() {
		return "board4/add";
	}
	
	@PostMapping("/add")
	String addPost(Post post) {
		
		service.add(post);
		
		return "redirect:list";
	}
	
	@GetMapping("/update")
	String updateGet(@RequestParam String postId,Model model) {
		
		Post postDetail =  service.detail(postId);
		
		model.addAttribute("vo", postDetail);
		
		return "board4/update";
	}
	
	@PostMapping("/update")
	String updatePost(Post post) {
		service.update(post);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete")
	String delete(@RequestParam String postId,Model model) {
		
		service.delete(postId);
		
		return "redirect:list";
	}
	
	
}
