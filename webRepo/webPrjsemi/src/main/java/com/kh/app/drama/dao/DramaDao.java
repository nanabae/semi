package com.kh.app.drama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.drama.vo.DramaVo;

public class DramaDao {

	public int selectCnt(Connection conn, String catNum) throws Exception {
		//SQL	
		        String sql = "SELECT COUNT(*)  FROM DRAMA_BOARD WHERE STATUS = 'O'";

		        if("1".equals(catNum) || "2".equals(catNum) || "3".equals(catNum)  )  {
		        	sql += " AND CAT_NUM = ?";
				}
				

				PreparedStatement pstmt = conn.prepareStatement(sql);
				if("1".equals(catNum) || "2".equals(catNum) || "3".equals(catNum) ){
					pstmt.setString(1, catNum);
				}
				
				ResultSet rs = pstmt.executeQuery();
				
				//tx || rs
				int cnt = 0;
				if(rs.next()) {
					cnt = rs.getInt(1);
				}
				
				//close
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return cnt;
	}
	//일반 조회
	public List<DramaVo> selectDramaList(Connection conn, PageVo pv) throws Exception {
		//SQL
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT D.DRAMA_BRD_NUM, D.DRAMA_WRITER, M.MEM_NICK, D.CAT_NUM, C.CAT_NAME , TITLE , D.CONTENT, D.STATUS, TO_CHAR(D.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, D.MODIFY_DATE, D.HIT, D.ANONYMITY FROM DRAMA_BOARD D JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM) JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) WHERE STATUS = 'O' ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<DramaVo> list = new ArrayList<>();
		while(rs.next()) {
			String dramaNum = rs.getString("DRAMA_BRD_NUM");
			String dramaWriter = rs.getString("DRAMA_WRITER");
			String catNum = rs.getString("CAT_NUM");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String status = rs.getString("STATUS");
			String enrollDate = rs.getString("ENROLL_DATE");					
			String modifyDate = rs.getString("MODIFY_DATE");
			int hit = rs.getInt("HIT");
			String anonymity = rs.getString("ANONYMITY");
			String catName = rs.getString("CAT_NAME");
			String writerName = rs.getString("MEM_NICK");
			
			DramaVo vo = new DramaVo();
			vo.setDramaNum(dramaNum);
			vo.setDramaWriter(dramaWriter);
			vo.setCatNum(catNum);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setStatus(status);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
			vo.setHit(hit);
			vo.setAnonymity(anonymity);
			vo.setCatName(catName);
			vo.setWriterName(writerName);
			
			list.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return list;
	}

	//카테고리로 조회
	public List<DramaVo> selectDramaList(Connection conn, PageVo pv, String catNum2) throws Exception {
		//SQL
				String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT D.DRAMA_BRD_NUM, D.DRAMA_WRITER, M.MEM_NICK, D.CAT_NUM, C.CAT_NAME , TITLE , D.CONTENT, D.STATUS, TO_CHAR(D.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, D.MODIFY_DATE, D.HIT, D.ANONYMITY FROM DRAMA_BOARD D JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM) JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) WHERE STATUS = 'O' AND D.CAT_NUM = ? ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, catNum2);
				pstmt.setInt(2, pv.getBeginRow());
				pstmt.setInt(3, pv.getLastRow());
				ResultSet rs = pstmt.executeQuery();
				
				//tx || rs
				List<DramaVo> list = new ArrayList<>();
				while(rs.next()) {
					String dramaNum = rs.getString("DRAMA_BRD_NUM");
					String dramaWriter = rs.getString("DRAMA_WRITER");
					String catNum = rs.getString("CAT_NUM");
					String title = rs.getString("TITLE");
					String content = rs.getString("CONTENT");
					String status = rs.getString("STATUS");
					String enrollDate = rs.getString("ENROLL_DATE");					
					String modifyDate = rs.getString("MODIFY_DATE");
					int hit = rs.getInt("HIT");
					String anonymity = rs.getString("ANONYMITY");
					String catName = rs.getString("CAT_NAME");
					String writerName = rs.getString("MEM_NICK");
					
					DramaVo vo = new DramaVo();
					vo.setDramaNum(dramaNum);
					vo.setDramaWriter(dramaWriter);
					vo.setCatNum(catNum2);
					vo.setTitle(title);
					vo.setContent(content);
					vo.setStatus(status);
					vo.setEnrollDate(enrollDate);
					vo.setModifyDate(modifyDate);
					vo.setHit(hit);
					vo.setAnonymity(anonymity);
					vo.setCatName(catName);
					vo.setWriterName(writerName);
					
					list.add(vo);
				}
				
				//close
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return list;
	}

	public int increaseHit(Connection conn, String dramaNum) throws Exception {
		String sql = "UPDATE DRAMA_BOARD SET HIT = HIT+1 WHERE DRAMA_BRD_NUM = ? AND STATUS = 'O'";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dramaNum);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public DramaVo selectDramaOneByNo(Connection conn, String dramaNum) throws Exception {
		//SQL
				String sql = "SELECT DRAMA_BRD_NUM , DRAMA_WRITER ,M.MEM_NICK, D.CAT_NUM ,C.CAT_NAME, TITLE , CONTENT ,  STATUS , TO_CHAR(D.ENROLL_DATE, 'YYYY-MM-DD HH:MM') AS ENROLL_DATE , MODIFY_DATE , HIT, ANONYMITY FROM DRAMA_BOARD D JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM) JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) WHERE DRAMA_BRD_NUM = ? AND STATUS = 'O'";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dramaNum);
				ResultSet rs = pstmt.executeQuery();
				
				//tx || rs
				DramaVo vo = null;
				if(rs.next()) {
					String dramaWriter = rs.getString("DRAMA_WRITER");
					String catNum = rs.getString("CAT_NUM");
					String title = rs.getString("TITLE");
					String content = rs.getString("CONTENT");
					String status = rs.getString("STATUS");
					String enrollDate = rs.getString("ENROLL_DATE");					
					String modifyDate = rs.getString("MODIFY_DATE");
					int hit = rs.getInt("HIT");
					String anonymity = rs.getString("ANONYMITY");
					String catName = rs.getString("CAT_NAME");
					String writerName = rs.getString("MEM_NICK");
					
					vo = new DramaVo();
					vo.setDramaNum(dramaNum);
					vo.setDramaWriter(dramaWriter);
					vo.setCatNum(catNum);
					vo.setTitle(title);
					vo.setContent(content);
					vo.setStatus(status);
					vo.setEnrollDate(enrollDate);
					vo.setModifyDate(modifyDate);
					vo.setHit(hit);
					vo.setAnonymity(anonymity);
					vo.setCatName(catName);
					vo.setWriterName(writerName);
				}
				
				//close
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return vo;
	}

	public int selectCnt(Connection conn, String searchType, String searchValue) throws Exception {
		String sql = "SELECT COUNT(*)  FROM DRAMA_BOARD D JOIN MEMBER M ON (D.DRAMA_WRITER = M.MEM_NUM)  WHERE STATUS = 'O'";

		if ("all".equals(searchType)) {
		    sql += " AND TITLE LIKE '%" + searchValue + "%' OR MEM_NICK LIKE '%" + searchValue + "%' OR CONTENT LIKE '%" + searchValue + "%'";
		} else if ("title".equals(searchType)) {
		    sql += " AND TITLE LIKE '%" + searchValue + "%'";
		} else if ("writer".equals(searchType)) {
		    sql += " AND MEM_NICK LIKE '%" + searchValue + "%'";
		} else if ("content".equals(searchType)) {
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
	public List<DramaVo> selectDramaList(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String sql ="SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT D.DRAMA_BRD_NUM, D.DRAMA_WRITER, M.MEM_NICK, D.CAT_NUM, C.CAT_NAME , TITLE , D.CONTENT, D.STATUS, TO_CHAR(D.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, D.MODIFY_DATE, D.HIT, D.ANONYMITY FROM DRAMA_BOARD D JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM) JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) WHERE STATUS = 'O' ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		
		if ("all".equals(searchType)) {
		    sql += " AND TITLE LIKE '%" + searchValue + "%' OR MEM_NICK LIKE '%" + searchValue + "%' OR CONTENT LIKE '%" + searchValue + "%'";
		} else if ("title".equals(searchType)) {
		    sql += " AND TITLE LIKE '%" + searchValue + "%'";
		} else if ("writer".equals(searchType)) {
		    sql += " AND MEM_NICK LIKE '%" + searchValue + "%'";
		} else if ("content".equals(searchType)) {
		    sql += " AND CONTENT LIKE '%" + searchValue + "%'";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		List<DramaVo> list = new ArrayList<>();
		
		while(rs.next()) {
			String dramaNum = rs.getString("DRAMA_BRD_NUM");
			String dramaWriter = rs.getString("DRAMA_WRITER");
			String catNum = rs.getString("CAT_NUM");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String status = rs.getString("STATUS");
			String enrollDate = rs.getString("ENROLL_DATE");					
			String modifyDate = rs.getString("MODIFY_DATE");
			int hit = rs.getInt("HIT");
			String anonymity = rs.getString("ANONYMITY");
			String catName = rs.getString("CAT_NAME");
			String writerName = rs.getString("MEM_NICK");
			
			DramaVo vo = new DramaVo();
			vo.setDramaNum(dramaNum);
			vo.setDramaWriter(dramaWriter);
			vo.setCatNum(catNum);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setStatus(status);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
			vo.setHit(hit);
			vo.setAnonymity(anonymity);
			vo.setCatName(catName);
			vo.setWriterName(writerName);
			
			list.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return list;
	}


}
