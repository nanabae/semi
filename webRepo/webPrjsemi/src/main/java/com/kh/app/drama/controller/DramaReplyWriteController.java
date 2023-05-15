package com.kh.app.drama.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.drama.service.DramaService;
import com.kh.app.drama.vo.DramaReplyVo;
import com.kh.app.member.vo.MemberVo;


@WebServlet("/drama/reply/write")
public class DramaReplyWriteController extends HttpServlet{
	private DramaService ds = new DramaService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			String reWriter = loginMember.getMemNum();
			
			//데꺼
			String reRef = "";
			String dramaBrdNum = req.getParameter("dramaNum");
			String reContent = req.getParameter("comment");
			String reRef_ = req.getParameter("reRef");
			
			if(reRef_ != null) reRef = reRef_ ;

			//데뭉
			DramaReplyVo vo = new DramaReplyVo();
			vo.setDramaBrdNum(dramaBrdNum);
			vo.setReContent(reContent);
			vo.setReWriter(reWriter);
			vo.setReRef(reRef);
			
			//서비스			
			int result = ds.replyWrite(vo);
			
			System.out.println("댓글 작성 결과 : " + result);
			
			//화면 == 문자열 내보내기
			PrintWriter out = resp.getWriter();
			if(result == 1) {
				out.write("ok");
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] drama reply doPost errr~~~");
			e.printStackTrace();
			
		}
		

	}
	

	

}
