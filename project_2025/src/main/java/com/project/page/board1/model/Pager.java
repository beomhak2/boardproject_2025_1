package com.project.page.board1.model;

import java.util.ArrayList;
import java.util.List;

public class Pager {
	private int page = 1;		//	현재 페이지
	private int perPage = 5;	//	페이지당 게시글 수
	private float total; 			//  총 게시글 수
	private int perGroup = 3;
	
	private int search;
	private String keyword;
	
	public int getPage() { return page; }
	public void setPage(int page) { this.page = page; }
		
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
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getLast() {
		return (int)Math.ceil(total / perPage);
	}
	
	public int getNext() {
		int next = (((page-1) / perGroup) + 1) * perGroup +1;
		int last = getLast();
		
		return next >= last ? last : next;
	}
	public int getPrev() {
		int prev = (((page-1) / perGroup) -1) * perGroup +1;
		return prev <= perGroup ? 1 : prev;
	}
	
	public List<Integer> getList() {
		List<Integer> list = new ArrayList<Integer>();
		
		int startPage = (((page-1) / perGroup)) * perGroup + 1;
		
		for(int i = startPage; i < startPage + perGroup && i <= getLast(); i++) {
			list.add(i);
		}
		
		if(list.isEmpty()) {
			list.add(1);
		}
		
		return list;
	}
	
	public String getQuery() {
		
		String query = "";
		
		if (search >= 1 && search <= 3 && keyword != null && !keyword.isEmpty()) {
			query = "&search=" + search + "&keyword=" + keyword;
		}
		
		return query;
	}
}
