package com.project.page.board1.web;

import java.util.List;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/board1")//board1이라는 주소로 들어왔을때의 처리
public class Board1Controller {
	
	@Autowired
	Board1Service service;
	
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	public String list(Model model) {
		
		List<Post> list = service.list();
		model.addAttribute("list", list); 
		return "board1/list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/add")
	public String addGet() { 
		return "board1/add";
	}
	
	@PostMapping("/add")
	public String addPost(@ModelAttribute Post post, Model model) {
		
		service.writePost(post);
		return "redirect:/board1/list";
	}
	
}
