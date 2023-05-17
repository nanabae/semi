package com.kh.app.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.notice.vo.NoticeVo;

public class NoticeDao {

	public int getDramaListCnt(Connection conn, String searchType, String searchValue) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM NOTICE_BOARD WHERE STATUS = 'O'";
		
		if ("all".equals(searchType)) {
			sql += " AND  TITLE LIKE '%" + searchValue + "%' OR CONTENT LIKE '%" + searchValue + "%'";
        }else if ("title".equals(searchType)) {
        	sql += " AND TITLE LIKE '%" + searchValue + "%'";
		}else if ("content".equals(searchType)) {
		    sql += " AND CONTENT LIKE '%" + searchValue + "%'";
		}

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}

	public List<NoticeVo> selectNoticeList(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String sql = "SELECT NOTICE_NUM , ADMIN_NUM , TITLE , CONTENT , STATUS , TO_CHAR(ENROLL_DATE , 'YYYY-MM-DD HH:MM') AS ENROLL_DATE , MODIFY_DATE ,  HIT FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT * FROM NOTICE_BOARD WHERE STATUS = 'O' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		return null;
	}


	
}
