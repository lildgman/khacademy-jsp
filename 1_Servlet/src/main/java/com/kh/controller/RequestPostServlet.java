package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get 호출 완료");
		
		//POST 방식 요청 같은 경우에는
		//데이터를 추출하기 전에 인코딩설정을 해야함
		request.setCharacterEncoding("UTF-8");
		// 요청 시 전달된 값들은 request의 parameter 영역에 담겨있다.
		
		String name = request.getParameter("name"); // 
		String gender = request.getParameter("gender"); // "M" | "W" | null
		int age = Integer.parseInt(request.getParameter("age")); // 23 => 23
		String city = request.getParameter("city"); // "서울", "경기" 등 중에 1개 반환
		double height = Double.parseDouble(request.getParameter("height")); 
		
		// 체크박스처럼 복수의 벨류값들을 추출하고자 할 때는 
		// String 배열로 받아주자.
		
		String[] foods = request.getParameterValues("food");
		
		System.out.println(name);
		System.out.println(gender);
		System.out.println(age);
		System.out.println(city);
		System.out.println(height);
		
		
		if(foods == null) {
			System.out.println("foods: 없음");
		} else {
			System.out.println(String.join("/", foods));
		}

		
		for(String s : foods) {
			System.out.println(s);
		}
		
		// 추출한 값들을 가지고 요청처리를 해야됨(db와 상호작용)
				// > Service > Dao > DB sql로 실행
				
				// int result = new MemberService().insertMember(name, gender, age, city, height, foods);
				/*
				 * if(result > 0) {
				 * 		성공 -> 성공페이지 응답
				 * } else {
				 * 		실패 -> 실패페이지 응답
				 * }
				 */
		// 요청처리가 다 되었다는 가정하에 사용자가 보게될 응답 html이 필요하다.
		
		// 순수 Servlet방식: java코드 내에 html을 기술
		// JSP(Java Server Page) 방식: html 내에 java 코드를 쓸 수 있음
		
		// 응답페이지를 만드는 과정을 jsp에게 위임(떠넘긴다.)
		// 단, 응답화면(jsp)에서 필요로하는 데이터들을 담아 전달해 줘야한다.
		// 데이터들을 담기위한 공간 == request의 attribute 영역
		// request.setAttribute("키", 벨류)
		
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("gender", gender);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("foods", foods);
		
		// 응답하고자하는 뷰(jsp) 선택해서 넘겨줌 => RequestDispatcher 객체 생성
		RequestDispatcher view = request.getRequestDispatcher("/views/responsePage.jsp");
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post요청 성공");
		doGet(request,response);
	}

}
