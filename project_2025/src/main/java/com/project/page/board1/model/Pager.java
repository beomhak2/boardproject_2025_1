package com.project.page.board1.model;

import java.util.ArrayList;
import java.util.List;

public class Pager {
	private int page = 1;		//	현재 페이지
	private int perPage = 5;	//	페이지당 게시글 수
	private float total = 0; 			//  총 게시글 수
	private int perGroup = 3;
	
	private int search = 1;
	private String keyword = "";
	
	public int getPage() { return page; }
	public void setPage(int page) { 
		if(page > 0) this.page = page; }
		
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	public float getTotal() { return total; }
	public void setTotal(int total) { this.total = total; }

	public int getPerGroup() {
		return perGroup;
	}

	public void setPerGroup(int perGroup) {
		this.perGroup = perGroup;
	}

	public int getSearch() {
		return search;
	}

	public void setSearch(int search) {
		if(search >= 1 && search <= 3) this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = (keyword == null) ? "" : keyword.trim();
	}

	public int getLast() {
		return (int)Math.ceil((double)total / perPage);
	}
	
	public int getNext() {
		int next = (((page-1) / perGroup) + 1) * perGroup +1;
		return Math.min(next, getLast());
	}
	public int getPrev() {
		int prev = (((page-1) / perGroup) -1) * perGroup +1;
		return Math.max(prev, 1);
	}
	
	public List<Integer> getList() {
		List<Integer> list = new ArrayList<>();
		
		int startPage = (((page-1) / perGroup)) * perGroup + 1;
		int endPage = Math.min(startPage + perGroup - 1, getLast());
		
		for(int i = startPage; i <= endPage; i++) {
			list.add(i);
		}
		
		return list;
	}
	
	public String getQuery() {
		
		StringBuilder query = new StringBuilder();
		
		boolean hasSearch = (search >= 1 && search <= 3);
		boolean hasKeyword = (keyword != null && !keyword.trim().isEmpty());
		
		if (hasSearch && hasKeyword) {
			query.append("search=").append(search);
			query.append("&keyword=").append(keyword.trim());
		}
		
		return query.toString();
	}
	
	public String getQueryString() {
		String query = getQuery();
		return query.isEmpty() ? "" : "?" + query;
	}
}
