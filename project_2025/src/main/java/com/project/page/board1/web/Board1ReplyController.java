package com.project.page.board1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.project.page.board1.model.Reply;
import com.project.page.board1.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class Board1ReplyController {
	
	@Autowired
	ReplyService replyService;

	@GetMapping(value = "/list")
	public String list(@RequestParam("postId") int postId, Model model){
		List<Reply> replyList = replyService.list(postId);
		model.addAttribute("replyList", replyList);
		
		return "reply/replyList";
	}

	@PostMapping("/write")
	@ResponseBody
	public String insertReply(Reply reply){
		
		reply.setUserId("USER");
		replyService.add(reply);
		
		return "success";
	}

}
