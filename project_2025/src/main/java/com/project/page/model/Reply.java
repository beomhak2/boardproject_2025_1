package com.project.page.model;

import java.util.Date;

public class Reply {
	private int replyId;
	private String replyContent;
	private Date regDt;
	private Date mdfDt;
	private int replyClass;
	private int replyGroup;
	private int replyOrder;
	private String userId;
	private int postId;
	
	
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
	
	
}
