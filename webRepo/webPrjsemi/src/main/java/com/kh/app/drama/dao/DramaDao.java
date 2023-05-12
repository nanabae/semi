package com.kh.app.drama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.drama.vo.DramaVo;

public class DramaDao {
	
	public int getDramaListCnt(Connection conn, String catNum, String searchType, String searchValue , String headerNum) throws Exception {
		String sql = "SELECT COUNT(*) FROM DRAMA_BOARD D JOIN MEMBER M ON (D.DRAMA_WRITER = M.MEM_NUM) JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM) LEFT OUTER JOIN HEADER H ON (D.HEADER_NUM = H.HEADER_NUM)";
		if( headerNum.equals("") && catNum.equals("")) {
			sql += " WHERE STATUS = 'O'";
		} else if( catNum.equals("") && !headerNum.equals("")) {
		    sql += " WHERE STATUS = 'O' AND D.HEADER_NUM = " + headerNum +"";
		}else if( headerNum.equals("") && !catNum.equals("")) {
		    sql += " WHERE STATUS = 'O' AND D.Cat_NUM = " + catNum +"";
		}else {
			sql += " WHERE STATUS = 'O' AND D.Cat_NUM = " + catNum + "AND D.HEADER_NUM = " + headerNum +"";
			
		}
		
		if ("all".equals(searchType)) {
			sql += " AND  TITLE LIKE '%" + searchValue + "%' OR MEM_NICK LIKE '%" + searchValue + "%' OR CONTENT LIKE '%" + searchValue + "%'";
	        }

	    else if ("title".equals(searchType)) {
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
	
	//그냥 조회+검색 조회
	public List<DramaVo> selectDramaList(Connection conn, PageVo pv, String catNum, String searchType,String searchValue , String headerNum) throws Exception {
		String sql = "";
		//카테고리 번호,말머리 번호 유무
		if(headerNum.equals("") && catNum.equals("")) {
			sql ="SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT D.DRAMA_BRD_NUM , D.CAT_NUM ,H.HEADER_NUM,H.HEADER_NAME ,D.TITLE , D.CONTENT , D.DRAMA_WRITER ,  D.ENROLL_DATE , D.STATUS , D.MODIFY_DATE , D.HIT ,D.ANONYMITY, M.MEM_NICK ,C.CAT_NAME FROM DRAMA_BOARD D JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM) LEFT OUTER JOIN HEADER H ON (D.HEADER_NUM = H.HEADER_NUM) WHERE D.STATUS = 'O'";
			
		}else if( catNum.equals("") && !headerNum.equals("")){
			sql ="SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT D.DRAMA_BRD_NUM , D.CAT_NUM ,H.HEADER_NUM,H.HEADER_NAME ,D.TITLE , D.CONTENT , D.DRAMA_WRITER ,  D.ENROLL_DATE , D.STATUS , D.MODIFY_DATE , D.HIT ,D.ANONYMITY, M.MEM_NICK ,C.CAT_NAME FROM DRAMA_BOARD D JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM)  JOIN HEADER H ON ( D.HEADER_NUM = H.HEADER_NUM) WHERE D.STATUS = 'O' AND D.HEADER_NUM = " + headerNum +"";
			
		}else if(headerNum.equals("") && !catNum.equals("")) {
			sql ="SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT D.DRAMA_BRD_NUM , D.CAT_NUM ,H.HEADER_NUM,H.HEADER_NAME ,D.TITLE , D.CONTENT , D.DRAMA_WRITER ,  D.ENROLL_DATE , D.STATUS , D.MODIFY_DATE , D.HIT ,D.ANONYMITY, M.MEM_NICK ,C.CAT_NAME FROM DRAMA_BOARD D JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM)  LEFT OUTER JOIN HEADER H ON ( D.HEADER_NUM = H.HEADER_NUM) WHERE D.STATUS = 'O' AND C.CAT_NUM = " + catNum +"";
		}else {
			sql ="SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT D.DRAMA_BRD_NUM , D.CAT_NUM ,H.HEADER_NUM,H.HEADER_NAME ,D.TITLE , D.CONTENT , D.DRAMA_WRITER ,  D.ENROLL_DATE , D.STATUS , D.MODIFY_DATE , D.HIT ,D.ANONYMITY, M.MEM_NICK ,C.CAT_NAME FROM DRAMA_BOARD D JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM)  JOIN HEADER H ON ( D.HEADER_NUM = H.HEADER_NUM) WHERE D.STATUS = 'O' AND C.CAT_NUM = " + catNum + "AND D.HEADER_NUM = " + headerNum +"";
			
		}


		if ("all".equals(searchType)) {
		    sql += " AND (D.TITLE LIKE '%" + searchValue + "%' OR M.MEM_NICK LIKE '%" + searchValue + "%' OR CONTENT LIKE '%" + searchValue + "%' )ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?"; //()중요
		} else if ("title".equals(searchType)) {
		    sql += "AND D.TITLE LIKE '%" + searchValue + "%' ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		} else if ("writer".equals(searchType)) {
		    sql += " AND M.MEM_NICK LIKE '%" + searchValue + "%'  ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		} else if ("content".equals(searchType)) {
		    sql += " AND D.CONTENT LIKE '%" + searchValue + "%'  ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			sql += "ORDER BY DRAMA_BRD_NUM DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<DramaVo> list = new ArrayList<>();
		
		while(rs.next()) {
			String dramaNum = rs.getString("DRAMA_BRD_NUM");
			String dramaWriter = rs.getString("DRAMA_WRITER");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String status = rs.getString("STATUS");
			String enrollDate = rs.getString("ENROLL_DATE");					
			String modifyDate = rs.getString("MODIFY_DATE");
			int hit = rs.getInt("HIT");
			String anonymity = rs.getString("ANONYMITY");
			String writerName = rs.getString("MEM_NICK");
			String catName= rs.getString("CAT_NAME");
			 headerNum = rs.getString("HEADER_NUM");
			String headerName = rs.getString("HEADER_NAME");
			
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
			vo.setWriterName(writerName);
			vo.setCatName(catName);
			vo.setHeaderNum(headerNum);
			vo.setHeaderName(headerName);
			
			list.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return list;
	}
	
	//조회수 증가
	public int increaseHit(Connection conn, String dramaNum) throws Exception {
		String sql = "UPDATE DRAMA_BOARD SET HIT = HIT+1 WHERE DRAMA_BRD_NUM = ? AND STATUS = 'O'";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dramaNum);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//상세 조회
	public DramaVo selectDramaOneByNo(Connection conn, String dramaNum) throws Exception {
		//SQL
				String sql = "SELECT DRAMA_BRD_NUM , DRAMA_WRITER ,M.MEM_NICK, D.CAT_NUM , C.CAT_NAME, H.HEADER_NAME , TITLE , CONTENT ,  STATUS , TO_CHAR(D.ENROLL_DATE, 'YYYY-MM-DD HH:MM') AS ENROLL_DATE , MODIFY_DATE , HIT, ANONYMITY FROM DRAMA_BOARD D JOIN CATEGORY C ON (D.CAT_NUM = C.CAT_NUM) JOIN MEMBER M ON(D.DRAMA_WRITER = M.MEM_NUM) JOIN HEADER H ON ( D.HEADER_NUM = H.HEADER_NUM) WHERE DRAMA_BRD_NUM = ? AND STATUS = 'O'";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dramaNum);
				ResultSet rs = pstmt.executeQuery();
				
				//tx || rs
				DramaVo vo = null;
				if(rs.next()) {
					String dramaWriter = rs.getString("DRAMA_WRITER");
					String catNum = rs.getString("CAT_NUM");
					String headerName = rs.getString("HEADER_NAME");
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
					vo.setHeaderName(headerName);
				}
				
				//close
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return vo;
	}

	public int write(Connection conn, DramaVo vo) throws Exception {
		//getHeaderNum() String 타입. "" 값 형변환시 예외 발생
		String headerNumStr = vo.getHeaderNum();
		Integer headerNum = null;
		if (headerNumStr != null && !headerNumStr.trim().isEmpty()) {
		    headerNum = Integer.valueOf(headerNumStr);
		}
		//SQL
		String sql = "INSERT INTO DRAMA_BOARD (DRAMA_BRD_NUM, DRAMA_WRITER,CAT_NUM,HEADER_NUM,TITLE,CONTENT) VALUES (SEQ_DRAMA_BOARD_NO.NEXTVAL, ?, ?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.valueOf(vo.getDramaWriter()));
		pstmt.setInt(2, Integer.valueOf(vo.getCatNum()));
		pstmt.setObject(3, headerNum, Types.INTEGER);
		pstmt.setString(4, vo.getTitle());
		pstmt.setString(5, vo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}
	
	
	
	
	
	}


