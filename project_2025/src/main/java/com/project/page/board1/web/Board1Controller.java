package com.project.page.board1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.page.board1.model.Board1;
import com.project.page.board1.service.Board1Service;

@Controller
@RequestMapping("/board1")//board1이라는 주소로 들어왔을때의 처리
public class Board1Controller {
	
	@Autowired
	Board1Service service;
	
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	String list(Model model) {
		List<Board1> list = service.list();
		
		model.addAttribute("list", list);
		
		model.addAttribute("msg" ,"list");
		
		return "board1/list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/add")
	String add() {
		return "board1/add";
	}
	
	@PostMapping("/add")
	String add(Board1 item) {
		
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/update/{board1Id}")
	String add(@PathVariable Long boardId,Model model) {
		Board1 item = service.item(boardId);
		
		model.addAttribute("item", item);
		
		return "board1/update";
	}
	
	@PostMapping("/update")
	String update(Board1 item) {
		
		service.update(item);
		
		return "redirect:../list";
	}
	@GetMapping("/delete/{board1Id}")
	String delete(@PathVariable Long boardId,Model model) {
	    service.delete(boardId);
		
		
		return "redirect:../list";
	}
	
	
}
