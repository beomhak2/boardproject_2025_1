package com.project.page.board2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.page.board2.model.Reply;
import com.project.page.board2.service.Board2ReplyService;

@Controller
@RequestMapping("/reply")
public class Board2ReplyController {

	@Autowired
	Board2ReplyService service;
	
	//´ñ±Û µî·Ï
//	@PostMapping("/insert")
//	String insert(Reply reply, @RequestParam("page") int page) {
//		
//		service.insert(reply);
//		
//		return "redirect:/board2/detail/" + reply.getPostId() + "?page=" + page;
//	}
}
