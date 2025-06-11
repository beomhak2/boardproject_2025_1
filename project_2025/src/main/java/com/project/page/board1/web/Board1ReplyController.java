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
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.page.board1.model.Reply;
import com.project.page.board1.service.ReplyService;

@Controller
@RequestMapping("/board1/reply")
public class Board1ReplyController {
	
	@Autowired
	ReplyService replyService;

	@GetMapping("/list/{postId}")
	public String list(@PathVariable("postId") int postId, Model model){
		List<Reply> replyList = replyService.list(postId);
		model.addAttribute("replyList", replyList);
		
		return "reply/replyList";
	}

	@PostMapping("/add")
	public String replyWrite(@ModelAttribute Reply reply){
		replyService.add(reply);
		return "redirect:/board1/reply/list/" + reply.getPostId();
	}

}
