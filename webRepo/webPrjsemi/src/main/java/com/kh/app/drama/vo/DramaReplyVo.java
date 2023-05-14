package com.kh.app.drama.vo;

public class DramaReplyVo {
	
	private String reNo;
	private String reWriter;
	private String dramaBrdNum;
	private String reRef;
	private String reContent;
	private String reAnonymity;
	private String reEnrollEate;
	private String reStatus;
	private String writerName;
	public String getReNo() {
		return reNo;
	}
	public void setReNo(String reNo) {
		this.reNo = reNo;
	}
	public String getReWriter() {
		return reWriter;
	}
	public void setReWriter(String reWriter) {
		this.reWriter = reWriter;
	}
	public String getDramaBrdNum() {
		return dramaBrdNum;
	}
	public void setDramaBrdNum(String dramaBrdNum) {
		this.dramaBrdNum = dramaBrdNum;
	}
	public String getReRef() {
		return reRef;
	}
	public void setReRef(String reRef) {
		this.reRef = reRef;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getReAnonymity() {
		return reAnonymity;
	}
	public void setReAnonymity(String reAnonymity) {
		this.reAnonymity = reAnonymity;
	}
	public String getReEnrollEate() {
		return reEnrollEate;
	}
	public void setReEnrollEate(String reEnrollEate) {
		this.reEnrollEate = reEnrollEate;
	}
	public String getReStatus() {
		return reStatus;
	}
	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	@Override
	public String toString() {
		return "DramaReplyVo [reNo=" + reNo + ", reWriter=" + reWriter + ", dramaBrdNum=" + dramaBrdNum + ", reRef="
				+ reRef + ", reContent=" + reContent + ", reAnonymity=" + reAnonymity + ", reEnrollEate=" + reEnrollEate
				+ ", reStatus=" + reStatus + ", writerName=" + writerName + "]";
	}
	public DramaReplyVo(String reNo, String reWriter, String dramaBrdNum, String reRef, String reContent,
			String reAnonymity, String reEnrollEate, String reStatus, String writerName) {
		super();
		this.reNo = reNo;
		this.reWriter = reWriter;
		this.dramaBrdNum = dramaBrdNum;
		this.reRef = reRef;
		this.reContent = reContent;
		this.reAnonymity = reAnonymity;
		this.reEnrollEate = reEnrollEate;
		this.reStatus = reStatus;
		this.writerName = writerName;
	}
	public DramaReplyVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
