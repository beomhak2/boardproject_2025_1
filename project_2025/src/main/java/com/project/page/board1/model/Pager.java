package com.project.page.board1.model;

import java.util.ArrayList;
import java.util.List;


public class Pager {
	private int page;		//	현재 페이지
	private int perPage = 5;	//	페이지당 게시글 수
	private float totalCount = 0; 			//  총 게시글 수
	
	private int startPage;	// 첫 페이지
	private int endPage;	// 마지막 페이지
	private int totalPage;	// 페이지 안의 게시글 개수
	
	private int prev;
	private int next;
	
	private int last;
	private List<Integer> list;
	
	private String search;
	private String keyword;
	
	public Pager() {
		this.page = 1;
	}
	
	public void makePage(int total) {
		this.totalCount = total;
		
		this.totalPage = (int) Math.ceil((double)total / perPage);
		if(totalPage == 0) totalPage = 1;
		
		int pageBlock = 5;
		int blockNum = (page - 1) / pageBlock;
		
		this.startPage = blockNum * pageBlock + 1;
		this.endPage = Math.min(startPage + pageBlock - 1, totalPage);
		this.prev = Math.max(startPage - 1, 1);
		this.next = Math.min(endPage + 1, totalPage);
		this.last = totalPage;
		
		list = new ArrayList<>();
		for(int i = startPage; i <= endPage; i++) {
			list.add(i);
		}
	}
	
	public String getQueryString(int page) {
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("page=").append(page);
	    
	    if (search != null && !search.isBlank()) {
	        sb.append("&search=").append(search);
	    }
	    if (keyword != null && !keyword.isBlank()) {
	    	sb.append("&keyword=").append(keyword);
	    }
	    return sb.toString();
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public float getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(float totalCount) {
		this.totalCount = totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
	
}
