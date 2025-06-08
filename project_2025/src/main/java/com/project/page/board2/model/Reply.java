package com.project.page.board2.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
	CREATE TABLE reply (
		reply_id	number(10)		NOT NULL,
		reply_content	varchar2(1000)		NOT NULL,
		reg_dt		date		NOT NULL,
		mdf_dt	date		NULL,
		reply_class	number(10)		NULL,
		reply_group	number(10)		NULL,
		reply_order	number(10)		NULL,
		user_id	varchar2(30)		NOT NULL,
		post_id	number(10)		NOT NULL
	);
 */

public class Reply {
	private int replyId;
	private String replyContent;
	
	//jsp가 아닌 VO에서 직접 날짜 형식 변환
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date regDt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
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
