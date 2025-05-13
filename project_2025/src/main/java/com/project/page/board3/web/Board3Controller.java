package com.project.page.board3.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.page.board3.service.Board3Service;
import com.project.page.model.Board;

@Controller
@RequestMapping("/board3")//board3이라는 주소로 들어왔을때의 처리
public class Board3Controller {
	
	@Autowired
	Board3Service service;
	
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	String list(Model model) {
		List<Board> list = service.list();
		
		model.addAttribute("list", list);
		
		model.addAttribute("msg" ,"list");
		
		return "board3/list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/detail")
	String detail() {
		return "board3/detail";
	}
	
	@GetMapping("/add")
	String add() {
		return "board3/add";
	}
	
	@PostMapping("/add")
	String add(Board item) {
		
		service.add(item);
		
		return "redirect:list";
	}
	
}
