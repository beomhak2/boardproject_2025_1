package com.project.page.board2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.page.board2.model.Reply;
import com.project.page.board2.service.Board2ReplyService;

@RestController
@RequestMapping("/reply")
public class Board2ReplyController {

	@Autowired
	Board2ReplyService service;
	
	/**
	 * ajax 비동기 댓글 조회
	 * @param postId
	 * @return 댓글 조회
	 */
	@GetMapping("/selectReplyList")
	public ResponseEntity<List<Reply>> selectReplyList(@RequestParam int postId) {
        List<Reply> replies = service.selectReplyList(postId);
        return ResponseEntity.ok().body(replies);
    }
	
}
