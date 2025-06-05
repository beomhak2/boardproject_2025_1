package com.project.page.board1.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.page.board1.model.Pager;
import com.project.page.board1.service.Board1Service;
import com.project.page.model.Post; 

@Controller
@RequestMapping("/board1")//board1이라는 주소로 들어왔을때의 처리
public class Board1Controller {
	final String path = "board1/"; 
	
	@Autowired
	Board1Service service;
	
	/**
	 * 게시글 목록을 반환합니다.
	 * 2025.06.05
	 * @param model lits, page, "list"를 뷰로 전달
	 * @param page model로 전달
	 * by 한수원
	 * @return 게시물 목록
	 */
	@GetMapping("/list")	//어떠한 주소로 들어왔을때 무엇을 처리 할 것인가?
	public String list(Model model, Pager page) {
		
		List<Post> list = service.list(page);
				
		model.addAttribute("list", list);
		
		model.addAttribute("pager", page);
		
		model.addAttribute("msg", "list");
		
		return path + "list";	// board1에 있는 list라는 명칭의 jsp로 반환
	}
	
	/**
	 * 게시글을 입력합니다.
	 * 2025.06.05
	 * by 한수원
	 * @return 게시물 입력
	 */
	@GetMapping("/add")
	public String addGet() { 
		return path + "add";
	}
	
	/**
	 * 입력된 게시글을 저장합니다.
	 * 2025.06.05
	 * by 한수원
	 * @param post 등록할 게시물 정보
	 * @return 목록으로 이동
	 */
	@PostMapping("/add")
	public String addPost(Post post) {
		
		service.writePost(post);
		
		return "redirect:list";
	}
	
	/**
	 * 상세 게시판 이동
	 * 2025.06.05
	 * by 한수원
	 * @param postId URL 경로변수값을 메서드 파라미터에 바인딩
	 * @param model 조회된 게시글 뷰로 전달
	 * @return 게시판 상세내용
	 */
	@GetMapping("/detail/{postId}")
 	public String detail(@PathVariable int postId, Model model) {
		
		service.increaseViewCount(postId);	// 조회수 증가
		Post post = service.getPostById(postId);	//게시글 조회
		
	    model.addAttribute("post", post);
		
		return path + "detail";
	}
	
	/**
	 * 수정 페이지로 이동 postId:\\d+ : 숫자만 가능
	 * 2025.06.05
	 * by 한수원
	 * @param postId URL경로 변수 바인딩
	 * @param model post를 뷰로 전달
	 * @return 수정 페이지 이동
	 */
	@GetMapping("/update/{postId:\\d+}")
	public String updateGet(@PathVariable int postId, Model model) {
		
		Post post = service.getPostById(postId);
	
		model.addAttribute("post", post);
		
		return path + "update";
	}
	
	/**
	 * 받아온 postId의 게시글을 수정
	 * 2025.06.05
	 * by 한수원
	 * @param postId URL에서 postId를 받음
	 * @param post 오늘의 날짜와 postId를 설정
	 * @param model	에러 시 수정에 실패했습니다.를 보여줌
	 * @return postId를 가지고 /board1/detail/로 이동
	 */
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
	
	/**
	 * 게시글 삭제(del_yn = 'Y')로 변경하여 삭제 표시
	 * 2025.06.05
	 * by 한수원
	 * @param postId URL경로 변수 바인딩
	 * @return 목록페이지로 이동
	 */
	@PostMapping("/delete/{postId}")
	public String deletePost(@PathVariable int postId) {
		service.deletePost(postId);
		return "redirect:/board1/list";
	}
}
