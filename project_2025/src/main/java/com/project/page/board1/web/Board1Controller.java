package com.project.page.board1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.page.board1.service.Board1Service;
import com.project.page.model.Board;
import com.project.page.model.Post; 

@Controller
@RequestMapping("/board1")//board1�씠�씪�뒗 二쇱냼濡� �뱾�뼱�솕�쓣�븣�쓽 泥섎━
public class Board1Controller {
	
	@Autowired
	Board1Service service;
	
	@GetMapping("/list")	//�뼱�뼚�븳 二쇱냼濡� �뱾�뼱�솕�쓣�븣 臾댁뾿�쓣 泥섎━ �븷 寃껋씤媛�?
	public String list(Model model) {
		List<Post> list = service.list();
		
		model.addAttribute("list", list); 
		
		return "board1/list";	// �뿉 �엳�뒗 list�씪�뒗 紐낆묶�쓽 jsp濡� 諛섑솚
	}
	
	@GetMapping("/add")
	String add() { 
		return "board1/add";
	}
	
	@GetMapping("/add")
	String add(Post item) {
		service.add(item);
		return "redirect:list";
	}

	
	@GetMapping("/detail")
	String detail() {
		return "board1/detail";
	}
}
