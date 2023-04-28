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



}
