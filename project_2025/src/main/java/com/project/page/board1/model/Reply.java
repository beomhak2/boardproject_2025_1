package com.project.page.board1.model;

import java.util.Date;

public class Reply {
	private int replyId;
	private String replyContent;
	private Date regDt;
	private Date mdfDt;
	private int replyClass;
	private Long replyGroup;
	private int replyOrder;
	private String userId;
	private int postId;
	private Member member;
	private Post post;
	
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
	public int getReplyClass() {
		return replyClass;
	}
	public void setReplyClass(int replyClass) {
		this.replyClass = replyClass;
	}
	public Long getReplyGroup() {
		return replyGroup;
	}
	public void setReplyGroup(Long replyGroup) {
		this.replyGroup = replyGroup;
	}
	public int getReplyOrder() {
		return replyOrder;
	}
	public void setReplyOrder(int replyOrder) {
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
