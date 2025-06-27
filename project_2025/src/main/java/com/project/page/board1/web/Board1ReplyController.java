package com.project.page.board1.web;

import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.project.page.board1.model.Reply;
import com.project.page.board1.model.ReplyDTO;
import com.project.page.board1.service.ReplyService;

@RestController
@RequestMapping("/board1/{postId}/replies")
public class Board1ReplyController {

    private static final Logger logger = LoggerFactory.getLogger(Board1ReplyController.class);

    @Autowired
    private ReplyService replyService;

    // 댓글 리스트 조회 (GET)
    @GetMapping
    public ResponseEntity<List<ReplyDTO>> getReplies(@PathVariable("postId") int postId) {
        try {
            List<ReplyDTO> replies = replyService.getCommentTreeByPostId(postId);
            return ResponseEntity.ok(replies);
        } catch (Exception e) {
            logger.error("댓글 조회 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    // 댓글 등록 (POST)
    @PostMapping
    public ResponseEntity<Map<String, Object>> addReply(
            @PathVariable("postId") int postId,
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult,
            HttpSession session) {

        String userId = (String) session.getAttribute("USER");

        if (userId == null) {
            // 테스트용 하드코딩 유저 (실제 사용 시 제거할 것)
            userId = "USER";
        }

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                .stream().map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(Map.of(
                "status", "fail",
                "message", errorMessage
            ));
        }

        try {
            Reply reply = new Reply();
            reply.setPostId(postId);
            reply.setReplyContent(replyDTO.getReplyContent());
            reply.setReplyClass(replyDTO.getReplyClass() != null ? replyDTO.getReplyClass() : 0);  // 부모 ID
            reply.setUserId(userId);

            Reply savedReply = replyService.writeReply(reply);

            ReplyDTO responseDTO = new ReplyDTO(
                    savedReply.getReplyId(),
                    savedReply.getReplyContent(),
                    savedReply.getRegDt(),
                    savedReply.getUserId(),
                    savedReply.getReplyClass() // 부모 댓글 ID
            );

            return ResponseEntity.ok(Map.of(
                "status", "success",
                "reply", responseDTO
            ));
        } catch (Exception e) {
            logger.error("댓글 등록 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", "fail",
                "message", "서버 오류 발생"
            ));
        }
    }
}
