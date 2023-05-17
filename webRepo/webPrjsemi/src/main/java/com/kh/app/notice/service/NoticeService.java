package com.kh.app.notice.service;

import java.sql.Connection;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.drama.vo.DramaVo;
import com.kh.app.notice.dao.NoticeDao;
import com.kh.app.notice.vo.NoticeVo;


public class NoticeService {
	
	private final NoticeDao dao = new NoticeDao();

	public int getNoticeListCnt(String searchType, String searchValue) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getDramaListCnt(conn,searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return cnt;

	}

	public List<NoticeVo> selectNoticeList(PageVo pv, String searchType, String searchValue) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<NoticeVo> list = dao.selectNoticeList(conn , pv , searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return list;
		
	}
	
	

}
