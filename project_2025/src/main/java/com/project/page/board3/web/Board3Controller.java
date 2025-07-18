package com.project.page.board3.web;

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

import com.project.page.board3.pager.Pager;
import com.project.page.board3.service.Board3Service;

import com.project.page.model.Post;

@Controller
@RequestMapping("/board3")//board3�씠�씪�뒗 二쇱냼濡� �뱾�뼱�솕�쓣�븣�쓽 泥섎━
public class Board3Controller {
	final String path = "board3/"; 
	
	@Autowired
	Board3Service service;
	// Pager 매개변수 추가
	@GetMapping("/list")	//�뼱�뼚�븳 二쇱냼濡� �뱾�뼱�솕�쓣�븣 臾댁뾿�쓣 泥섎━ �븷 寃껋씤媛�?
	String list(Post post, Model model, @ModelAttribute Pager pager) {

		List<Post> list = service.list(pager);
		
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		
		return path + "list";	// �뿉 �엳�뒗 list�씪�뒗 紐낆묶�쓽 jsp濡� 諛섑솚
	}
	
	@GetMapping("/detail/{postId}")
	String detail(@PathVariable int postId, Model model, @RequestParam(defaultValue ="1")int page){
		service.viewCnt(postId);
	    Post item = service.item(postId);

	    model.addAttribute("item", item);
	    model.addAttribute("page", page);
	    
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
	
