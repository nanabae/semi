package com.kh.app.mypage.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
