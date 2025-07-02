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

    @Autowired
    private ReplyDao replydao;

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

            if (reply.getParentId() == null || reply.getParentId() == 0) { // 부모 댓글인 경우
                initRootReply(reply);
            } else { // 대댓글인 경우
                initChildReply(reply);
            }

            replydao.insertReply(reply);
            return reply;
        } catch (DataAccessException e) {
            throw new ReplyServiceException("댓글 작성 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 최상위 댓글 초기화
     */
    private void initRootReply(Reply reply) {
        reply.setReplyGroup(reply.getReplyId()); // 그룹은 자기 ID로
        reply.setReplyOrder(1);                   // 최상위 댓글 순서는 1
        reply.setReplyClass(0);                   // 레벨 0 (부모)
    }

    /**
     * 대댓글 초기화
     */
    private void initChildReply(Reply reply) throws ReplyServiceException {
        int parentId = reply.getParentId(); // 부모 댓글 ID 정확히 사용
        Reply parentReply = replydao.selectReplyById(parentId);

        if (parentReply == null) {
            logger.error("부모 댓글을 찾을 수 없습니다. parentId: {}", parentId);
            throw new ReplyServiceException("부모 댓글을 찾을 수 없습니다.");
        }

        reply.setReplyClass(parentReply.getReplyClass() + 1); // 부모 레벨 + 1
        replydao.updateReplyOrderAfter(parentReply.getReplyGroup(), parentReply.getReplyOrder());

        reply.setReplyGroup(parentReply.getReplyGroup()); // 부모 그룹과 동일
        reply.setReplyOrder(parentReply.getReplyOrder() + 1); // 부모 댓글 다음 순서
    }

    /**
     * 댓글 리스트를 트리 형태의 DTO 리스트로 변환
     */
    private List<ReplyDTO> buildReplyTreeDTO(List<Reply> replies) {
        Map<Integer, ReplyDTO> map = new HashMap<>();
        List<ReplyDTO> rootReplies = new ArrayList<>();

        // 모든 댓글 DTO 생성 및 map 저장
        for (Reply reply : replies) {
            ReplyDTO dto = new ReplyDTO(
                reply.getReplyId(),
                reply.getReplyContent(),
                reply.getRegDt(),
                reply.getUserId(),
                reply.getParentId(),  // 부모 댓글 ID
                reply.getReplyClass() // 레벨(깊이)
            );
            map.put(reply.getReplyId(), dto);
        }

        // 부모-자식 관계 설정
        for (Reply reply : replies) {
            ReplyDTO dto = map.get(reply.getReplyId());
            Integer parentId = reply.getParentId();

            if (parentId == null || parentId == 0) {
                // 최상위 댓글이면 rootReplies에 추가
                rootReplies.add(dto);
            } else {
                // 자식 댓글이면 부모 DTO의 childReplies에 추가
                ReplyDTO parentDTO = map.get(parentId);
                if (parentDTO != null) {
                    parentDTO.getChildReplies().add(dto);
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
