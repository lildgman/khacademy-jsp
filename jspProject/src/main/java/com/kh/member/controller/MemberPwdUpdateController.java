package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class EditPasswordController
 */
@WebServlet("/updatePwd.me")
public class MemberPwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updateMember = new MemberService().updatePwd(userId, userPwd, updatePwd);
		
		if(updateMember != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("alertMsg", "비밀번호 변경을 완료하였습니다.");
			// 비밀번호가 바뀐 updateMember로 바꿔줘야함
			session.setAttribute("loginUser", updateMember);
			
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
		} else {
//			HttpSession session = request.getSession();
//			session.setAttribute("alertMsg", "비밀번호 변경에 실패하였습니다.");
//			response.sendRedirect(request.getContextPath()+"/myPage.me");
			request.setAttribute("errorMsg", "비밀번호 변경에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
