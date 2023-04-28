package com.kh.app.member.vo;

public class MemberVo {
	
	private String memNum;
	private String id;
	private String pwd;
	private String nick;
	private String birth;
	private String phon;
	private int point;
	private String status;
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhon() {
		return phon;
	}
	public void setPhon(String phon) {
		this.phon = phon;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MemberVo [memNum=" + memNum + ", id=" + id + ", pwd=" + pwd + ", nick=" + nick + ", birth=" + birth
				+ ", phon=" + phon + ", point=" + point + ", status=" + status + "]";
	}
	public MemberVo(String memNum, String id, String pwd, String nick, String birth, String phon, int point,
			String status) {
		super();
		this.memNum = memNum;
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.birth = birth;
		this.phon = phon;
		this.point = point;
		this.status = status;
	}
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	

}
