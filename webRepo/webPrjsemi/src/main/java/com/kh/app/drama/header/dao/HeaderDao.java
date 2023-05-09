package com.kh.app.drama.header.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.drama.header.vo.HeaderVo;

public class HeaderDao {
	
	//말머리 등록(결과 2 or 1)
	public int regHeader(Connection conn, HeaderVo vo) throws Exception {

	String sql = "INSERT ALL WHEN NOT EXISTS (SELECT * FROM HEADER WHERE HEADER_NAME = ?) THEN INTO HEADER(HEADER_NUM, HEADER_NAME) VALUES(SEQ_HEADER_NO.NEXTVAL, ?) INTO MEM_HEADER(MEM_HEAD_NUM, MEM_NUM, HEADER_NUM) VALUES (SEQ_HEADER_NO.CURRVAL, ?, SEQ_HEADER_NO.CURRVAL) WHEN EXISTS (SELECT * FROM HEADER WHERE HEADER_NAME = ?) THEN INTO MEM_HEADER(MEM_HEAD_NUM, MEM_NUM, HEADER_NUM) VALUES (SEQ_HEADER_NO.NEXTVAL, ?, (SELECT HEADER_NUM FROM HEADER WHERE HEADER_NAME = ?)) SELECT 1 FROM DUAL";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, vo.getHeaderName());
	pstmt.setString(2, vo.getHeaderName());
	pstmt.setInt(3, Integer.valueOf(vo.getMemNum()));	
	pstmt.setString(4, vo.getHeaderName());
	pstmt.setInt(5, Integer.valueOf(vo.getMemNum()));	
	pstmt.setString(6, vo.getHeaderName());

	int result = pstmt.executeUpdate();
	JDBCTemplate.close(pstmt);
	return result;
	
	}
	//화면으로 보내 줄 값 조회
	public HeaderVo selectHeader(Connection conn, HeaderVo vo) throws Exception, Exception {
		String sql ="SELECT MH.MEM_HEAD_NUM, MH.MEM_NUM,H.HEADER_NUM,H.HEADER_NAME FROM HEADER H JOIN MEM_HEADER MH ON (H.HEADER_NUM = MH.HEADER_NUM) WHERE MEM_NUM = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.valueOf(vo.getMemNum()));
		ResultSet rs = pstmt.executeQuery();
		
		HeaderVo dbVo = null;
		while(rs.next()) {
			String memHeadNum = rs.getString("MEM_HEAD_NUM");
			String memNum = rs.getString("MEM_NUM");
			String headerNum = rs.getString("HEADER_NUM");
			String headerName = rs.getString("HEADER_NAME");
			
			dbVo = new HeaderVo();
			dbVo.setMemHeadNum(memHeadNum);
			dbVo.setMemNum(memNum);
			dbVo.setHeaderNum(headerNum);
			dbVo.setHeaderName(headerName);
						
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		System.out.println("dao에서 출력"+dbVo);
		return dbVo;
	}

}

