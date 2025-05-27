package com.project.page.board1.web;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.page.board1.service.Board1Service;
import com.project.page.model.Board;
import com.project.page.model.Post; 

@Controller
@RequestMapping("/board1")//board1이라는 주소로 들어왔을때의 처리
public class Board1Controller {
	final String path = "board1/"; 
	
	@Autowired
	Board1Service service;
	
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	public String list(Model model) {
		
		List<Post> list = service.list();
		
		model.addAttribute("list", list); 
		
		return path + "list";	// 에 있는 list라는 명칭의 jsp로 반환
	}
	
	@GetMapping("/add")
	public String addGet() { 
		return path + "add";
	}
	
	@PostMapping("/add")
	public String addPost(Post post) {
		
		service.writePost(post);
		
		return "redirect:list";
	}
	
	@GetMapping("/detail/{postId}")
 	public String detail(@PathVariable int postId, Model model) {
		
		service.increaseViewCount(postId);	// 조회수 증가
		Post post = service.getPostById(postId);	//게시글 조회
		
	    model.addAttribute("post", post);
		
		return path + "detail";
	}
	
	@GetMapping("/update/{postId:\\d+}")
	public String updateGet(@PathVariable int postId, Model model) {
		
		Post post = service.getPostById(postId);
	
		model.addAttribute("post", post);
		
		return path + "update";
	}
	
	@PostMapping("/update/{postId}")
	public String updatePost(@PathVariable int postId, @ModelAttribute Post post, Model model) {
	    post.setPostId(postId);            // URL에서 받은 ID 세팅 (보안용)
	    post.setMdfDt(new Date());         // 수정일 설정

	    int result = service.updatePost(post);

	    if(result > 0) {
	        return "redirect:/board1/detail/" + postId;
	    } else {
	        model.addAttribute("error", "수정에 실패했습니다.");
	        return path + "update"; // 수정 폼으로 다시 이동
	    }
	}
	
	@PostMapping("/delete/{postId}")
	public String deletePost(@PathVariable int postId) {
		service.deletePost(postId);
		return "redirect:/board1/list";
	}
	
}
