package com.project.page.board1.model;

import java.util.Date;

public class Reply {
	private Integer replyId;
	private String replyContent;
	private Date regDt;
	private Date mdfDt;
	private Integer replyClass;
	private Integer replyGroup;
	private Integer replyOrder;
	private String userId;
	private int postId;
	private Integer parentReplyId;
	private Member member;
	private Post post;
	
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Integer getParentReplyId() {
		return parentReplyId;
	}
	public void setParentReplyId(Integer parentReplyId) {
		this.parentReplyId = parentReplyId;
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
	public Integer getReplyClass() {
		return replyClass;
	}
	public void setReplyClass(Integer replyClass) {
		this.replyClass = replyClass;
	}
	public Integer getReplyGroup() {
		return replyGroup;
	}
	public void setReplyGroup(Integer replyGroup) {
		this.replyGroup = replyGroup;
	}
	public Integer getReplyOrder() {
		return replyOrder;
	}
	public void setReplyOrder(Integer replyOrder) {
		this.replyOrder = replyOrder;
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
	
	
}
