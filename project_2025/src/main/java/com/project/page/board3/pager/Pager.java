package com.project.page.board3.pager;

import java.util.ArrayList;
import java.util.List;

//검색 기능
public class Pager {
	private int page = 1;
	private int perPage = 5;
	private float total;
	private int perGroup = 3;
	
	private int search;
	private String keyword;
	
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
	public int getPerGroup() {
		return perGroup;
	}
	public void setPerGroup(int perGroup) {
		this.perGroup = perGroup;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
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
	public int getLast() {
		return (int)Math.ceil(total/perPage);
	}
	
	public int getNext() {
		int next = (((page - 1) / perGroup) + 1) * perGroup + 1;

		int last = getLast();
		
		return next >= last ? last : next;
		
	}
	public int getPrev() {
		int prev =  (((page -1)/perGroup) - 1) * perGroup + 1;
		
		return prev <= perGroup ? 1 : prev;
	}
	
	public List<Integer> getList(){
		List<Integer> list = new ArrayList<Integer>();
		
		int startPage = (((page -1)/perGroup) + 0) * perGroup + 1;
		
		for(int i = startPage; i < (startPage + perGroup) && i <= getLast(); i++) {
			list.add(i);
		}
		
		if(list.isEmpty())
			list.add(1);
		
		return list;
	}
	public String getQuery() {
		String query="";
		
		if(search >= 1 && search<=3 && keyword != null && !keyword.isEmpty()) {
			query = "&search=" + search + "&keyword=" + keyword; 
		}
		return query;
	}
}

