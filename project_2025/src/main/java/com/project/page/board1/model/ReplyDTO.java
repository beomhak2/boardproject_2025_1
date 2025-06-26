package com.project.page.board1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

// 읽기 전용
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReplyDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int replyId;
	private String replyContent;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
	private Date regDt;
	private String userId;
	private Integer parentId;
	private int replyClass;
	
	private List<ReplyDTO> childReplies = new ArrayList<>();
	
	public ReplyDTO() {}
	
	public ReplyDTO(int replyId, String replyContent, Date regDt, String userId, Integer parentId, int replyClass) {
		this.replyId = replyId;
		this.replyContent = replyContent;
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
		this.replyContent = replyContent;
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public int getReplyClass() {
		return replyClass;
	}

	public void setReplyClass(int replyClass) {
		this.replyClass = replyClass;
	}

	public List<ReplyDTO> getChildReplies() {
		return childReplies;
	}

	public void setChildReplies(List<ReplyDTO> childReplies) {
		this.childReplies = childReplies;
	}
	
}
