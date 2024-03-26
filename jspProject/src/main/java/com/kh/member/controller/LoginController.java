package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("로그인 정보 도착!");
		
		// 1) 전달값에 한글이 있을 경우 인코딩 처리(POST)
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 전달값 추출해서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) 요청하기(db에 sql문 활용해서 조회)
		// service 클래스에 담당 메서드 호출
		Member loginUser = new MemberService().loginMember(userId, userPwd);
		
		// 4) 처리된 결과를 가지고 사용자가 보게될 응답뷰를 지정해서 포워딩 또는 url 재요청(redirect)
		if(loginUser == null) {
			// 조회결과 없음 => 로그인 실패 => 에러문구가 보여지는 에러페이지로 포워딩 할 예정
			request.setAttribute("errorMsg", "로그인에 실패하였습니다.");
			
			// 응답페이지에게 위임
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		} else {
			// 조회결과 있음 => 로그인 성공
			// 로그인한 회원정보(loginUser)를 session영역에 담기(다른곳에서도 가져다 쓸 수 있도록)
			// Servlet에서는 session영역에 접근하고자 한다면 세션객체를 얻어야 한다.
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",loginUser);
			
			response.sendRedirect(request.getContextPath());
			
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
