package com.kh.app.mypage.memo.service;

import java.sql.Connection;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.mypage.memo.dao.MemoDao;
import com.kh.app.mypage.memo.vo.MemoVo;

public class MemoService {
	private final MemoDao dao = new MemoDao();

	public int write(MemoVo vo) throws Exception {
		int result ;
		try(Connection conn = JDBCTemplate.getConnection();){
			result = dao.write(conn, vo);
			
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
