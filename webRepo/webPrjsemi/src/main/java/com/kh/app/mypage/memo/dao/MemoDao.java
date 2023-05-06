package com.kh.app.mypage.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.mypage.memo.vo.MemoVo;


public class MemoDao {

	public int write(Connection conn, MemoVo vo) throws Exception {
		//SQL
		String sql = "INSERT INTO MEMO (MEMO_NUM, MEMO_WRITER, MEMO_TARGET,MEMO_CONTENT) VALUES (SEQ_MEMO_NO.NEXTVAL, ?, ?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemoWriter());
		pstmt.setString(2, vo.getMemoTarget());
		pstmt.setString(3, vo.getMemoContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public MemoVo selectMemoOneByno(Connection conn, String memoWriter, String dramaNum) throws Exception {
		String sql = "SELECT MEMO_NUM, MEMO_WRITER, MEMO_TARGET,MEMO_CONTENT , TO_CHAR(MEMO_ENROLL_DATE , 'YYYY-MM-DD') AS ENROLL_DATE FROM MEMO WHERE MEMO_TARGET = ( SELECT DRAMA_WRITER FROM DRAMA_BOARD WHERE DRAMA_BRD_NUM = ? ) AND MEMO_WRITER = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dramaNum);
		pstmt.setString(2, memoWriter );
		ResultSet rs = pstmt.executeQuery();

		MemoVo vo = null;
		if(rs.next()) {
			String memoNum = rs.getString("MEMO_NUM");
			String memoTarget = rs.getString("MEMO_TARGET");
			String content = rs.getString("MEMO_CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
		
			vo = new MemoVo();
			vo.setMemoNum(memoNum);
			vo.setMemoWriter(memoWriter);
			vo.setMemoTarget(memoTarget);
			vo.setMemoContent(content);
			vo.setMemoEnrollDate(enrollDate);

		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;

	}

	public int editMemo(Connection conn, MemoVo vo) throws Exception {
		//SQL
		String sql = "UPDATE MEMO SET MEMO_CONTENT = ? WHERE MEMO_NUM = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemoContent());
		pstmt.setString(2, vo.getMemoNum());
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;

	}

}
