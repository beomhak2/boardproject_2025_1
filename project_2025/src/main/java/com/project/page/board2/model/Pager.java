package com.project.page.board2.model;

import java.util.ArrayList;
import java.util.List;

public class Pager {
	private int page = 1;
	private int perPage = 10;
	private float total;
	private int perGroup = 5;
	
	private int condition;
	private String search;

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
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}	
	public int getPerGroup() {
		return perGroup;
	}
	public void setPerGroup(int perGroup) {
		this.perGroup = perGroup;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}

	public int getLast() {
		return (int) Math.ceil(total / perPage);
	}

	public int getPrev() {
		int prev = (((page - 1) / perGroup) - 1) * perGroup + 1;
		
		return prev <= perGroup ? 1 : prev;
	}
	
	public int getNext() {
		int next = (((page - 1) / perGroup) + 1) * perGroup + 1;
		int last = getLast();
		
		return next >= last ? last : next;
	}
	
	public List<Integer> getList() {
		List<Integer> list = new ArrayList<Integer>();
		
		int startPage = (((page - 1) / perGroup) + 0) * perGroup + 1;
		
		for (int i = startPage; i < (startPage + perGroup) && i <= getLast(); i++) {
			list.add(i);
		}
		
		if (list.isEmpty()) {
			list.add(1);
		}
		return list;
	}
	
	public String getQuery() {
		String query = "";
		
		if (condition >= 1 && condition <= 3 && search != null && !search.isEmpty()) {
			query += "&condition=" + condition + "&search=" + search;
		}
		return query;
	}
}
