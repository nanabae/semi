package com.kh.app.drama.header.vo;

public class HeaderVo {
	private String memHeadNum;
	private String memNum;
	private String headerNum;
	private String headerName;
	public String getMemHeadNum() {
		return memHeadNum;
	}
	public void setMemHeadNum(String memHeadNum) {
		this.memHeadNum = memHeadNum;
	}
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getHeaderNum() {
		return headerNum;
	}
	public void setHeaderNum(String headerNum) {
		this.headerNum = headerNum;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public HeaderVo(String memHeadNum, String memNum, String headerNum, String headerName) {
		super();
		this.memHeadNum = memHeadNum;
		this.memNum = memNum;
		this.headerNum = headerNum;
		this.headerName = headerName;
	}
	public HeaderVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "HeaderVo [memHeadNum=" + memHeadNum + ", memNum=" + memNum + ", headerNum=" + headerNum
				+ ", headerName=" + headerName + "]";
	}
	
	
	
	
	
}
