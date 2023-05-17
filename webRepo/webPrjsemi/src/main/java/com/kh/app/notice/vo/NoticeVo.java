package com.kh.app.notice.vo;

public class NoticeVo {

	private String noticeNum;
	private String adminNum;
	private String title;;
	private String content;
	private String status;
	private String enrollDate;
	private String modifyDate;
	private String hit;
	public String getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(String noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
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
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "NoticeVo [noticeNum=" + noticeNum + ", adminNum=" + adminNum + ", title=" + title + ", content="
				+ content + ", status=" + status + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate
				+ ", hit=" + hit + "]";
	}
	public NoticeVo(String noticeNum, String adminNum, String title, String content, String status, String enrollDate,
			String modifyDate, String hit) {
		super();
		this.noticeNum = noticeNum;
		this.adminNum = adminNum;
		this.title = title;
		this.content = content;
		this.status = status;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.hit = hit;
	}
	public NoticeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
