package com.kh.app.member.service;

import java.sql.Connection;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.member.dao.MemberDao;
import com.kh.app.member.vo.MemberVo;

public class MemberService {
	
	private final MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}

	public int join(MemberVo vo) throws Exception {
		//conn
				Connection conn = JDBCTemplate.getConnection();
				
				int result = dao.join(conn , vo);
				
				//tx || rs
				if(result == 1) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
				
				//close
				JDBCTemplate.close(conn);
				
				return result;
	}

	public MemberVo login(MemberVo vo) throws Exception {
		//conn
				Connection conn = JDBCTemplate.getConnection();
				
				MemberVo loginMember = dao.login(conn, vo);
				
				//close
				JDBCTemplate.close(conn);
				
				return loginMember;
	}

}
