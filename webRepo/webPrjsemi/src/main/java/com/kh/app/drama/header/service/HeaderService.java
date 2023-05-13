package com.kh.app.drama.header.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
			dbVo = dao.selectHeader(conn, vo);
			JDBCTemplate.commit(conn);
			
		}else{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
	   }
		return dbVo;
		
	}

	public List<HeaderVo> selectListHeader(HeaderVo vo) throws Exception {
		 Connection conn = JDBCTemplate.getConnection();
		 
		 List<HeaderVo> listVo =  dao.selectListHeader(conn,vo);
		 
		 JDBCTemplate.close(conn);


		return listVo;
	}

	public int deleteHeader(HeaderVo vo) throws Exception {
		int result ;
		try(Connection conn = JDBCTemplate.getConnection();){
			result = dao.deleteHeader(conn, vo);
			//tx || rs
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}
		return result;
	}
	
	
	
	
	
}