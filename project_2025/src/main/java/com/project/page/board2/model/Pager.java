package com.project.page.board2.model;

public class Pager {
	private int page = 1;
	private int perPage = 10;
	private float total;

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
	
	public int getLast() {
		return (int) Math.ceil(total / perPage);
	}

}
