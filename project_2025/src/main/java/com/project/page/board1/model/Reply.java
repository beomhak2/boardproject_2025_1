package com.project.page.board1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reply {
	private int replyId;
	private String replyContent;
	private Date regDt;
	private Date mdfDt;

	private String userId;
	private int postId;
	private Integer parentId;
	
	private int replyGroup;
	private int replyOrder;
	private Integer replyClass;
	
	private Member member;
	private List<Reply> childReplies = new ArrayList<>();
	
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
	public Date getMdfDt() {
		return mdfDt;
	}
	public void setMdfDt(Date mdfDt) {
		this.mdfDt = mdfDt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public int getReplyGroup() {
		return replyGroup;
	}
	public void setReplyGroup(int replyGroup) {
		this.replyGroup = replyGroup;
	}
	public int getReplyOrder() {
		return replyOrder;
	}
	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}
	public Integer getReplyClass() {
		return replyClass;
	}
	public void setReplyClass(Integer replyClass) {
		this.replyClass = replyClass;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<Reply> getChildReplies() {
		return childReplies;
	}
	public void setChildReplies(List<Reply> childReplies) {
		this.childReplies = childReplies;
	}	
	
}
