package com.kh.app.drama.header.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.drama.header.dao.HeaderDao;

public class HeaderService {
	private HeaderDao dao = new HeaderDao();
	
	public int regHeader(String headerName) throws Exception {
		int result;

		
		try(Connection conn = JDBCTemplate.getConnection();){
			result = dao.regHeader(conn,headerName);
			if(result == 1) {
				JDBCTemplate.commit(conn);
				result02 = dao.regmemHeader(conn,headerName);
			}else if(result == 0) {
				result02 = dao.regmemHeader(conn,headerName);
			}else {
				throw new Exception();
			}
			
		}
		

		return result02;
	}

}
