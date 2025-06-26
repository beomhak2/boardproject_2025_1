package com.project.page.board1.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.page.board1.model.Reply;
import com.project.page.board1.model.ReplyDTO;
import com.project.page.board1.service.ReplyService;

@RestController
@RequestMapping("/{postId}/reply")
public class Board1ReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(Board1ReplyController.class);
	
	private final ReplyService replyService;
	
	public Board1ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}

	@GetMapping("/list")
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable int postId){
		try {
			List<ReplyDTO> replies = replyService.getCommentTreeByPostId(postId);
			
			if(replies == null || replies.isEmpty()) {
				logger.warn("댓글 없음 - postId: {}", postId);
			}else {
				logger.info("댓글 개수: {}", replies.size());
			}
			
			return ResponseEntity.ok(replies);
		} catch (Exception e) {
			logger.error("댓글 리스트 조회 실패 - postId: {}", postId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

	@PostMapping("/write")
	public ResponseEntity<?> write(@PathVariable int postId,
	                               @RequestBody @Validated ReplyDTO replyDTO,
	                               BindingResult bindingResult,
	                               HttpSession session) {
	    String userId = (String) session.getAttribute("userId");
	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
	                "status", "fail",
	                "message", "로그인이 필요합니다"
	        ));
	    }

	    if (bindingResult.hasErrors()) {
	        String errorMessage = Optional.ofNullable(bindingResult.getFieldError())
	        								.map(e -> e.getDefaultMessage())
	        								.orElse("입력값이 올바르지 않습니다.");
	        return ResponseEntity.badRequest().body(Map.of(
	                "status", "fail",
	                "message", errorMessage
	        ));
	    }

	    try {
	        Reply reply = new Reply();
	        reply.setPostId(postId);
	        reply.setReplyContent(replyDTO.getReplyContent());
	        reply.setParentId(replyDTO.getParentId());
	        reply.setUserId(userId);

	        Reply savedReply = replyService.writeReply(reply);

	        return ResponseEntity.ok(Map.of(
	                "status", "success",
	                "reply", savedReply
	        ));
	    } catch (Exception e) {
	    	// 확실한 방법 (가장 추천)
	    	logger.error("댓글 등록 실패 - postId: " + postId + ", userId: " + userId, e);

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
	                "status", "fail",
	                "message", "서버에 오류가 발생했습니다."
	        ));
	    }
	}

}
