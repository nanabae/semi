package com.kh.app.drama.header.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.drama.header.dao.HeaderDao;
import com.kh.app.drama.header.vo.HeaderVo;

public class HeaderService {
	private HeaderDao dao = new HeaderDao();
	
	//말머리 등록
	public HeaderVo regHeader(HeaderVo vo) throws Exception {
		HeaderVo dbVo = null;
		try(Connection conn = JDBCTemplate.getConnection();){
		int result = dao.regHeader(conn,vo);
		if(result == 2 || result == 1) {
			JDBCTemplate.commit(conn);
			dbVo = dao.selectHeader(conn, vo);
			
		}else{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
	   }
		return dbVo;
		
	}
}