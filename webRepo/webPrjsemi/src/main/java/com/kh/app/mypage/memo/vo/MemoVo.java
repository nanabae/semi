package com.kh.app.mypage.memo.vo;

public class MemoVo {
	
	private String memoNum;
	private String memoWriter;
	private String memoTarget;
	private String memoContent;
	private String memoEnrollDate;
	public String getMemoNum() {
		return memoNum;
	}
	public void setMemoNum(String memoNum) {
		this.memoNum = memoNum;
	}
	public String getMemoWriter() {
		return memoWriter;
	}
	public void setMemoWriter(String memoWriter) {
		this.memoWriter = memoWriter;
	}
	public String getMemoTarget() {
		return memoTarget;
	}
	public void setMemoTarget(String memoTarget) {
		this.memoTarget = memoTarget;
	}
	public String getMemoContent() {
		return memoContent;
	}
	public void setMemoContent(String memoContent) {
		this.memoContent = memoContent;
	}
	public String getMemoEnrollDate() {
		return memoEnrollDate;
	}
	public void setMemoEnrollDate(String memoEnrollDate) {
		this.memoEnrollDate = memoEnrollDate;
	}
	@Override
	public String toString() {
		return "MemoVo [memoNum=" + memoNum + ", memoWriter=" + memoWriter + ", memoTarget=" + memoTarget
				+ ", memoContent=" + memoContent + ", memoEnrollDate=" + memoEnrollDate + "]";
	}
	public MemoVo(String memoNum, String memoWriter, String memoTarget, String memoContent, String memoEnrollDate) {
		super();
		this.memoNum = memoNum;
		this.memoWriter = memoWriter;
		this.memoTarget = memoTarget;
		this.memoContent = memoContent;
		this.memoEnrollDate = memoEnrollDate;
	}
	public MemoVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
