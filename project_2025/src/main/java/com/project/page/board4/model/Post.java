package com.project.page.board4.model;

import java.util.Date;

import com.project.page.model.Member;

public class Post {
	private int rnum;
	private int postId;
	private String title;
	private String postContent;
	private int viewCnt;
	private String delYn;
	private Date regDt;
	private Date mdfDt;
	private int boardId;
	private String userId;
	private Member member;
	private int nextPostId;
	private int prevPostId;
	private String nextPostContent;
	private String prevPostContent;
	private int replyCnt;
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
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
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getNextPostId() {
		return nextPostId;
	}
	public void setNextPostId(int nextPostId) {
		this.nextPostId = nextPostId;
	}
	public int getPrevPostId() {
		return prevPostId;
	}
	public void setPrevPostId(int prevPostId) {
		this.prevPostId = prevPostId;
	}
	public String getNextPostContent() {
		return nextPostContent;
	}
	public void setNextPostContent(String nextPostContent) {
		this.nextPostContent = nextPostContent;
	}
	public String getPrevPostContent() {
		return prevPostContent;
	}
	public void setPrevPostContent(String prevPostContent) {
		this.prevPostContent = prevPostContent;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	
	 
}
