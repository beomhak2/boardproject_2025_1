package com.project.page.board2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.page.board2.service.Board2Service;
import com.project.page.model.Board;

@Controller
@RequestMapping("/board2")//board2이라는 주소로 들어왔을때의 처리
public class Board2Controller {
	
	@Autowired
	Board2Service service;
	
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	String list(Model model) {
		List<Board> list = service.list();
		
		model.addAttribute("list", list);
		
		model.addAttribute("msg" ,"list");
		
		return "board2/list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/detail")
	String detail() {
		return "board2/detail";
	}
	
	@GetMapping("/add")
	String add() {
		return "board2/add";
	}
	
	@PostMapping("/add")
	String add(Board item) {
		
		service.add(item);
		
		return "redirect:list";
	}
	
}
