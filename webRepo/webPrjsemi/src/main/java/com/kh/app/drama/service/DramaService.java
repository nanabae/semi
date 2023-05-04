package com.kh.app.drama.service;

import java.sql.Connection;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.drama.dao.DramaDao;
import com.kh.app.drama.vo.DramaVo;

public class DramaService {
	private final DramaDao dao = new DramaDao();
	
	public int getDramaListCnt(String catNum, String searchType, String searchValue) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getDramaListCnt(conn,catNum,searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	public List<DramaVo> selectDramaList(PageVo pv, String catNum, String searchType, String searchValue) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<DramaVo> list = dao.selectDramaList(conn , pv , catNum, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//상세조회(select+update)
	public DramaVo selectDramaOneByNum(String dramaNum) throws Exception {
		DramaVo vo = null;
		//conn
		try (Connection conn = JDBCTemplate.getConnection();){
			//update
			int result = dao.increaseHit(conn , dramaNum);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
				//select
				vo = dao.selectDramaOneByNo(conn , dramaNum);
			}else {
				throw new Exception();
			}
		}
		return vo;
	}



}
	