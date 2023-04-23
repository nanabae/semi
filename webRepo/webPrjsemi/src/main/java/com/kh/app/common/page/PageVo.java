package com.kh.app.common.page;

public class PageVo {
	
	private int listCount;
	private int currentPage;
	private int pageLimit;
	private int boardLimit;
	
	private int maxPage;
	private int startPage;
	private int endPage;
	
	private int beginRow;
	private int lastRow;
	
	public PageVo(int listCount , int currentPage, int pageLimit, int boardLimit) {
		
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		this.maxPage = (int) Math.ceil((double)listCount / boardLimit);
		this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		this.endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		this.beginRow = boardLimit * (currentPage-1) + 1;
		this.lastRow = beginRow + boardLimit - 1;
	}

	@Override
	public String toString() {
		return "PageVo [listCount=" + listCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit
				+ ", boardLimit=" + boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", beginRow=" + beginRow + ", lastRow=" + lastRow + "]";
	}

	public int getListCount() {
		return listCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public int getLastRow() {
		return lastRow;
	}
	
	
	

}