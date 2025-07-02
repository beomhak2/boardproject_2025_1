package com.project.page.board1.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.util.HtmlUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReplyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int replyId;

	@NotBlank(message = "댓글 내용을 입력해주세요.")
	private String replyContent;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date regDt;

	private String userId;
	
	/** 
	 * 부모 댓글 ID (null이면 최상위 댓글)
	 */
	private Integer parentId;

	private Integer replyClass;
	
	private Integer replyOrder;

	private List<ReplyDTO> childReplies = new ArrayList<>();



	public ReplyDTO() {}

	public ReplyDTO(int replyId, String replyContent, Date regDt, String userId, Integer parentId ,Integer replyClass) {
		this.replyId = replyId;
		this.setReplyContent(replyContent); // escape 적용
		this.regDt = regDt;
		this.userId = userId;
		this.parentId = parentId;
		this.replyClass = replyClass;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		// XSS 방지용 HTML Escape
		this.replyContent = replyContent == null ? null : HtmlUtils.htmlEscape(replyContent);
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getReplyClass() {
		return replyClass;
	}

	public void setReplyClass(Integer replyClass) {
		this.replyClass = replyClass;
	}

	public Integer getReplyOrder() {
		return replyOrder;
	}

	public void setReplyOrder(Integer replyOrder) {
		this.replyOrder = replyOrder;
	}

	public List<ReplyDTO> getChildReplies() {
		return childReplies;
	}

	public void setChildReplies(List<ReplyDTO> childReplies) {
		this.childReplies = childReplies;
	}
}
