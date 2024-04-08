package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.model.vo.Person;

/**
 * Servlet implementation class ELServlet
 */
@WebServlet("/el.do")
public class ELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ELServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * 데이터를 담을 수 있는 JSP 내장 객체 종류
		 * 
		 * 1. ServletContext(application scope)
		 * 	  한 애플리케이션당 단 1개 존재하는 객체
		 *    이 영역에 데이터를 담으면 애플리케이션 전역에서 사용이 가능하다.
		 *    공유범위가 가장 크다.
		 * 
		 * 2. HttpSession(session scope)
		 * 	  한 브라우저당 1개 존재하는 객체
		 *    이 영역에 데이터를 담으면 jsp/servlet 단에서 사용이 가능
		 *    
		 * 3. HttpServletRequest
		 * 	  요청때마다 매번 생성되는 객체
		 * 	  이 영역에 데이터를 담으면 해당 request 객체를 포워딩 받는 응답 jsp에서만 사용가능
		 * 
		 * 4. PageContext(page scope)
		 *    jsp마다 존재하는 객체
		 *    공유범위가 가장 작다(해당 페이지에서만 공유 가능)
		 * 
		 * 위 4개 객체들에 
		 * 데이터를 담을때는 .setAttribute('키', 데이터);
		 * 데이터를 꺼낼 때 .getAttribute('키');
		 * 데이터를 지울 때 .removeAttribute('키');
		 * 
		 * 
		 */
		
		//request scope에 데이터 담기
		request.setAttribute("classroom", "I강의장");
		request.setAttribute("student",new Person("오동건",20,"남자"));
		
		//session scope에 담기
		HttpSession session = request.getSession();
		session.setAttribute("academy", "kh");
		session.setAttribute("teacher",new Person("김지원",26,"여자"));
		
		//각 scope에 동일한 키로 데이터 담기
		request.setAttribute("scope", "request");
		session.setAttribute("scope", "session");
		
		//applicationScope에 담기
		ServletContext application = request.getServletContext();
		application.setAttribute("scope", "application");
		
		//응답페이지를 지정하여 해당 페이지가 포워딩되도록 설정
		request.getRequestDispatcher("views/1_el/01_el.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
