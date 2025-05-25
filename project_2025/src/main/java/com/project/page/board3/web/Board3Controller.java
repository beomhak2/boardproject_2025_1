package com.project.page.board3.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.page.board3.service.Board3Service;

import com.project.page.model.Post;

@Controller
@RequestMapping("/board3")//board3�씠�씪�뒗 二쇱냼濡� �뱾�뼱�솕�쓣�븣�쓽 泥섎━
public class Board3Controller {
	final String path = "board3/"; 
	
	@Autowired
	Board3Service service;
	
	@GetMapping("/list")	//�뼱�뼚�븳 二쇱냼濡� �뱾�뼱�솕�쓣�븣 臾댁뾿�쓣 泥섎━ �븷 寃껋씤媛�?
	String list(Post post, Model model) {
		List<Post> list = service.list(post);
		
		model.addAttribute("list", list);
		
		return path + "list";	// �뿉 �엳�뒗 list�씪�뒗 紐낆묶�쓽 jsp濡� 諛섑솚
	}
	
	@GetMapping("/detail/{postid}")
	String detail(@PathVariable int postid, Model model) {
	    Post item = service.item(postid);
	    
	    System.out.println("item: " + item); 
	    model.addAttribute("item", item);
	    
	    return path + "detail";
	}
	
	@GetMapping("/add")
	String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	String add(Post item) {
		
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/update/{postId}")
	String update(@PathVariable int postId, Model model) {
		Post item =service.item(postId);
		
		model.addAttribute("item",item);
		
		return "board3/update";
	}
	
	@PostMapping("/update/{postId}")
	String update(@PathVariable int postId, Post item) {
		item.setPostId(postId);
		
		service.update(item);
		
		return "redirect:../list";
	}
	
	@GetMapping("/delete/{postId}")
	String delete(@PathVariable int postId) {
		service.delete(postId);
		return "redirect:../list";
	}
}
	
