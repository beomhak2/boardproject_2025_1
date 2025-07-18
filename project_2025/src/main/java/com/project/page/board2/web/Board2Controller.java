package com.project.page.board2.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.page.board2.model.Pager;
import com.project.page.board2.model.Post;
import com.project.page.board2.service.Board2Service;

@Controller
@RequestMapping("/board2")
public class Board2Controller {
	
	@Autowired
	Board2Service service;
	
	@GetMapping("/list")
	String list(Pager pager, Model model) {
		List<Post> list = service.list(pager);
		
		//댓글 개수 표시
		Map<Integer, Integer> replyCount = new HashMap<>();
		for (Post post : list) {
			int count = service.getReplyCount(post.getPostId());
			replyCount.put(post.getPostId(), count);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("replyCount", replyCount);
		
		return "board2/list";
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
