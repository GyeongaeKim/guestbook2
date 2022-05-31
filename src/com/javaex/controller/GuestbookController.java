package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
    //생성자(기본생성자 사용)
	//메소드gs
	
	//메소드 일반
	//get방식으로 요청할 때,
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//포스트 방식일때 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		WebUtil webUtil = new WebUtil();
		
		if("list".equals(action)) { //리스트
			//데이터 가져오기
			GuestbookDao guestDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestDao.getList();
			System.out.println(guestList);
			
			//request에 데이터 추카
			request.setAttribute("gList", guestList);
			
			//데이터 + html
			webUtil.forward(request, response, "/WEB-INF/list.jsp");
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			*/
		}else if("insert".equals(action)){ //등록
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookDao guestDao = new GuestbookDao();
			GuestbookVo guestVo = new GuestbookVo(name, password, content);
			int count = guestDao.insert(guestVo);
			
			
			webUtil.redirect(request, response, "/guestbook2/gbc?action=list");
			
			//response.sendRedirect("/guestbook2/gbc?action=list");
			
		}else if("deleteForm".equals(action)) { //삭제폼
			//System.out.println("삭제폼");
			
			//deleteForm 포워드하기
			webUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			rd.forward(request, response);
			*/
		}else if("delete".equals(action)){ //삭제
			//System.out.println("삭제");
			
			//파라미터에서 값 꺼내오기
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			
			GuestbookDao dao = new GuestbookDao();
			dao.delete(vo);
			
			//리다이렉트 list
			webUtil.redirect(request, response, "/guestbook2/gbc?action=list");
			
			//response.sendRedirect("/guestbook2/gbc?action=list");
			
		}else {
			System.out.println("action 파라미터 없음");
		}
	}

	//post방식으로 요청할 때,
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
