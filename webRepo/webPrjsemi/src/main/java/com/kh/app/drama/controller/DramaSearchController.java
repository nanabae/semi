package com.kh.app.drama.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.app.common.page.PageVo;
import com.kh.app.drama.service.DramaService;
import com.kh.app.drama.vo.DramaVo;

@WebServlet("/drama/search")
public class DramaSearchController extends HttpServlet{
			
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			DramaService bs = new DramaService();
			
			int cnt = bs.getDramaListCnt(searchType , searchValue);
			int page = Integer.valueOf(req.getParameter("page"));
			PageVo pv = new PageVo(cnt, page, 10, 20);
			
			//서비스
			List<DramaVo> voList = null;
			if(searchType == null || searchType.equals("") || searchValue == null || searchValue.equals("")) {
				voList = bs.selectDramaList(pv);
			}else {
				 voList = bs.selectDramaList(pv,searchType , searchValue);
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			//화면
			req.setAttribute("searchVo", map);
			req.setAttribute("pv", pv);
			req.setAttribute("list", voList);
			req.getRequestDispatcher("/WEB-INF/views/drama/list.jsp").forward(req, resp);
			
			
			
		}catch(Exception e) {
			System.out.println("[ERROR] 드라마 게시판 검색 에러 ...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg" , "검색 실패 ..");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}

}
