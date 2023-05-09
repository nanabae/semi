package com.kh.app.drama.header.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HeaderDao {

	public int regHeader(Connection conn, String headerName) throws Exception {
		String sql = "INSERT INTO HEADER(HEADER_NUM ,HEADER_NAME) SELECT SEQ_HEADER_NO.NEXTVAL,? FROM DUAL WHERE NOT EXISTS (SELECT * FROM HEADER WHERE HEADER_NAME = ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, headerName);
		
		int result = pstmt.executeUpdate();
		return result;
		
		
	}

	public int regmemHeader(Connection conn, String headerName) throws Exception {
		String sql = "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		
		int result2 = pstmt.executeUpdate();
		return result2;
	}

}
