package com.project.page.board1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.page.board1.dao.ReplyDao;
import com.project.page.board1.model.Reply;
import com.project.page.board1.model.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired private ReplyDao replydao;

    private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<ReplyDTO> getCommentTreeByPostId(int postId) throws Exception {
        try {
            List<Reply> allReplies = replydao.selectReply(postId);
            return buildReplyTreeDTO(allReplies);
        } catch (DataAccessException e) {
            throw new ReplyServiceException("댓글 목록 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    @Transactional
    public Reply writeReply(Reply reply) throws Exception {
        try {
            int newReplyId = replydao.getNextReplyId();
            reply.setReplyId(newReplyId);

            if (reply.getReplyClass() == 0) {
                initRootReply(reply);
            } else {
                initChildReply(reply);
            }

            replydao.insertReply(reply);
            return reply;
        } catch (DataAccessException e) {
            throw new ReplyServiceException("댓글 작성 중 오류가 발생했습니다.", e);
        }
    }

    private void initRootReply(Reply reply) {
        reply.setReplyGroup(reply.getReplyId());
        reply.setReplyOrder(1);
        reply.setReplyClass(0); // 댓글 (루트)
    }

    private void initChildReply(Reply reply) throws ReplyServiceException {
        int parentId = reply.getReplyClass(); // 부모 ID를 replyClass로 사용
        Reply parentReply = replydao.selectReplyById(parentId);

        if (parentReply == null) {
            logger.error("부모 댓글을 찾을 수 없습니다. replyClass (parentId): {}", parentId);
            throw new ReplyServiceException("부모 댓글을 찾을 수 없습니다.");
        }

        replydao.updateReplyOrderAfter(parentReply.getReplyGroup(), parentReply.getReplyOrder());

        reply.setReplyGroup(parentReply.getReplyGroup());
        reply.setReplyOrder(parentReply.getReplyOrder() + 1);
    }

    private List<ReplyDTO> buildReplyTreeDTO(List<Reply> replies) {
        Map<Integer, ReplyDTO> map = new HashMap<>();
        List<ReplyDTO> rootReplies = new ArrayList<>();

        for (Reply reply : replies) {
            ReplyDTO dto = new ReplyDTO(
                reply.getReplyId(),
                reply.getReplyContent(),
                reply.getRegDt(),
                reply.getUserId(),
                reply.getReplyClass() // 부모 ID로 사용됨
            );
            map.put(reply.getReplyId(), dto);
        }

        for (Reply reply : replies) {
            ReplyDTO dto = map.get(reply.getReplyId());
            Integer parentId = reply.getReplyClass();

            if (parentId == null || parentId == 0 || parentId.equals(reply.getReplyId())) {
                rootReplies.add(dto);
            } else {
                ReplyDTO parentDTO = map.get(parentId);
                if (parentDTO != null) {
                    parentDTO.getChildReplies().add(dto);
                    logger.info("부모 댓글 ID {}에 해당하는 DTO를 찾았습니다", parentId);
                } else {
                    logger.warn("부모 댓글 ID {}에 해당하는 DTO를 찾을 수 없습니다.", parentId);
                }
            }
        }

        return rootReplies;
    }

    public static class ReplyServiceException extends RuntimeException {
        public ReplyServiceException(String message) {
            super(message);
        }
        public ReplyServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}