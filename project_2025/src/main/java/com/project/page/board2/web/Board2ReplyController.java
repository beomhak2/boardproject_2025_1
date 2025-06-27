package com.project.page.board2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	 * 댓글 조회
	 * @param postId
	 * @return 댓글 조회
	 */
	@GetMapping("/selectReplyList")
	public ResponseEntity<List<Reply>> selectReplyList(@RequestParam int postId) {
        List<Reply> replies = service.selectReplyList(postId);
        return ResponseEntity.ok().body(replies);
    }
	
	/**
	 * 댓글 등록
	 * @param reply
	 * @return
	 */
	@PostMapping("/insertReply")
	public ResponseEntity<String> insertReply(@RequestBody Reply reply) {
	    try {
	        service.insertReply(reply);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 로그 출력
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
	
	/**
	 * 댓글 삭제
	 * @param replyId
	 * @return
	 */
	@PostMapping("/deleteReply")
	public ResponseEntity<String> deleteReply(@RequestParam int replyId) {
		try {
			service.deleteReply(replyId);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			e.printStackTrace(); // 예외 로그 출력
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
		}
	}
	
	/**
	 * 답글 등록
	 * @param reply
	 * @return
	 */
	@PostMapping("/insertReplyAnswer")
	public ResponseEntity<String> insertReplyAnswer(Reply reply) {
	    try {
	    	reply.setReplyClass(1);
	        service.insertReplyAnswer(reply);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 로그 출력
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
}
