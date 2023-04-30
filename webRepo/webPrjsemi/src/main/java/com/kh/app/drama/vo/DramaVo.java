package com.kh.app.drama.vo;

public class DramaVo {

	private String dramaNum;
	private String dramaWriter;
	private String catNum;
	private String title;
	private String content;
	private String status;
	private String enrollDate;
	private String modifyDate;
	private int hit;
	private String anonymity;
	private String catName;
	private String writerName;
	
	public String getDramaNum() {
		return dramaNum;
	}
	public void setDramaNum(String dramaNum) {
		this.dramaNum = dramaNum;
	}
	public String getDramaWriter() {
		return dramaWriter;
	}
	public void setDramaWriter(String dramaWriter) {
		this.dramaWriter = dramaWriter;
	}
	public String getCatNum() {
		return catNum;
	}
	public void setCatNum(String catNum) {
		this.catNum = catNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getAnonymity() {
		return anonymity;
	}
	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	@Override
	public String toString() {
		return "DramaVo [dramaNum=" + dramaNum + ", dramaWriter=" + dramaWriter + ", catNum=" + catNum + ", title="
				+ title + ", content=" + content + ", status=" + status + ", enrollDate=" + enrollDate + ", modifyDate="
				+ modifyDate + ", hit=" + hit + ", anonymity=" + anonymity + ", catName=" + catName + ", writerName="
				+ writerName + "]";
	}
	public DramaVo(String dramaNum, String dramaWriter, String catNum, String title, String content, String status,
			String enrollDate, String modifyDate, int hit, String anonymity, String catName, String writerName) {
		super();
		this.dramaNum = dramaNum;
		this.dramaWriter = dramaWriter;
		this.catNum = catNum;
		this.title = title;
		this.content = content;
		this.status = status;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.hit = hit;
		this.anonymity = anonymity;
		this.catName = catName;
		this.writerName = writerName;
	}
	public DramaVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}