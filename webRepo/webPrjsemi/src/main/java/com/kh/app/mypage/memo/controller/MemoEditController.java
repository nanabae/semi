package com.kh.app.mypage.memo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.mypage.memo.service.MemoService;
import com.kh.app.mypage.memo.vo.MemoVo;

@WebServlet("/memo/edit")
public class MemoEditController extends HttpServlet{
	private final MemoService ms = new MemoService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String memoNum = req.getParameter("memoNum");
			String memoContent = req.getParameter("memoContent");
			String dramaNum = req.getParameter("dramaNum");
			
			MemoVo vo = new MemoVo();
			vo.setMemoNum(memoNum);
			vo.setMemoContent(memoContent);
			
			int result = ms.editMemo(vo);
			
			if(result == 1) {
				resp.sendRedirect(req.getContextPath() + "/drama/detail?dramaNum="+ dramaNum);
			}else {
				throw new Exception();
			}
			
		}catch(Exception e){
			System.out.println("[ERROR] MemoEditController.doPost errr~~~");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "memo edit err~~~");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}

		
	}

}
