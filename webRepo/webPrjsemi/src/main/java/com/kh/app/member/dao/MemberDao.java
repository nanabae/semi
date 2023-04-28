package com.kh.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		//SQL
				String sql = "INSERT INTO MEMBER(MEM_NUM, MEM_ID, MEM_PWD, MEM_NICK, MEM_BIRTH, MEM_PHON) VALUES(SEQ_MEMBER_NO.NEXTVAL , ?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPwd());
				pstmt.setString(3, vo.getNick());
				pstmt.setString(4, vo.getBirth());
				pstmt.setString(5, vo.getPhon());
				int result = pstmt.executeUpdate();
				JDBCTemplate.close(pstmt);
				return result;
			}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		//SQL
				String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PWD = ? AND MEM_STATUS = 'O'";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPwd());
				ResultSet rs = pstmt.executeQuery();
				
				//tx || rs 
				MemberVo loginMember = null;
				if(rs.next()) {
					String no = rs.getString("MEM_NUM");
					String id = rs.getString("MEM_ID");
					String pwd = rs.getString("MEM_PWD");
					String nick = rs.getString("MEM_NICK");
					String birth = rs.getString("MEM_BIRTH");
					String phon = rs.getString("MEM_PHON");
					int point = rs.getInt("MEM_POINT");
					String status = rs.getString("MEM_STATUS");
					
					loginMember = new MemberVo();
					loginMember.setMemNum(no);
					loginMember.setId(id);
					loginMember.setPwd(pwd);
					loginMember.setNick(nick);
					loginMember.setBirth(birth);
					loginMember.setPhon(phon);
					loginMember.setPoint(point);
					loginMember.setStatus(status);
				}
	

				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return loginMember;
	}



}
