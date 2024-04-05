package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		int result = new MemberService().deleteMember(userId, userPwd);
		
		HttpSession session = request.getSession();
		
		if(result > 0) {
			session.setAttribute("alertMsg", "회원탈퇴를 완료하였습니다.");
			session.removeAttribute("loginUser");
			response.sendRedirect(request.getContextPath());
		} else {
			session.setAttribute("alertMsg", "회원탈퇴에 실패하였습니다.");
			response.sendRedirect(request.getContextPath()+ "/myPage.me");
		}
		
		/**
		 * 정보변경, 비밀번호 변경 -> 데이터를 데이터베이스로 다시 가져와 넣어주기
		 * 
		 * 탈퇴 성공 시 -> 메인페이지 alert(성공했다.)
		 * 			 -> 단, 로그아웃처리해야함(session loginUser라는 키값에 데이터가 없어야 함)
		 *
		 * 탈퇴 실패 시 -> 마이페이지 alert(회원탈퇴 실패)
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
