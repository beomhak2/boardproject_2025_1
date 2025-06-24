package com.project.page.board4.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.page.board4.model.Pager;
import com.project.page.board4.model.Post;
import com.project.page.board4.service.Board4Service;
import com.project.page.model.Reply;

@Controller
@RequestMapping("/board4")//board3이라는 주소로 들어왔을때의 처리
public class Board4Controller {
	
	@Autowired
	Board4Service service;
	
	
	/**
    * 이 메서드는 두 개의 정수를 입력받아 합을 반환합니다.
    *
    * @param a 첫 번째 정수
    * @param b 두 번째 정수
    * @return 두 정수의 합
    */
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	String list(Pager pager,Model model) {
		List<Post> list = service.list(pager);
		
		model.addAttribute("list", list);
		
		model.addAttribute("pager", pager);
		
		model.addAttribute("msg" ,"list");
		
		return "board4/list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/detail")
	String detail(@RequestParam String postId,Model model) {
		
		Post postDetail =  service.detail(postId);
		
		service.viewCntUp(postId);
		
		model.addAttribute("vo", postDetail);
		
		return "board4/detail";
	}
	
	@GetMapping("/dummy")
	String dummy() {
		service.dummy();
		
		return "redirect:list";
	}
	
	@GetMapping("/add")
	String addGet() {
		return "board4/add";
	}
	
	@PostMapping("/add")
	String addPost(Post post) {
		
		service.add(post);
		
		return "redirect:list";
	}
	
	@GetMapping("/update")
	String updateGet(@RequestParam String postId,Model model) {
		
		Post postDetail =  service.detail(postId);
		
		model.addAttribute("vo", postDetail);
		
		return "board4/update";
	}
	
	@PostMapping("/update")
	String updatePost(Post post) {
		service.update(post);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete")
	String delete(@RequestParam String postId,Model model) {
		
		service.delete(postId);
		
		return "redirect:list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/replyList", method = RequestMethod.POST)
	List<Reply> replyList(Reply post) {
		List<Reply> replyList = service.getReplyList(post);
		
		return replyList;
	}
	
    @ResponseBody
	@RequestMapping(value = "/addReply", method = RequestMethod.POST)
	String addReply(Reply reply) {
    	String result;
    	
		int InsSts = service.replyInsert(reply);
		if (InsSts == 1) {
			result = "sucess";
		}else {
			result = "fail";
		}
		return result;
	}
	
    @ResponseBody
    @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
    String deleteReply(Reply reply) {
    	String result;
    	
    	int DelSts = service.replyDelete(reply);
    	if (DelSts == 1) {
    		result = "sucess";
    	}else {
    		result = "fail";
    	}
    	return result;
    }
    
	
}
