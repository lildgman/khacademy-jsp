package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza.do")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PizzaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("배달의 민족 주문~");
		// 1) 인코딩 처리 => 전달값 중 한글이 있을 수 있다. (POST일 경우 / GET일때 해도 상관없음)
		// 되도록이면 해주도록 하자.
		request.setCharacterEncoding("UTF-8");

		// 2) 요청시 전달값 추출, 데이터 가공처리 => 변수나 객체에 넘겨받은 값을 기록한다.
		// request.getParameter("키"): 벨류값
		// request.getParameterValues("키") : ["벨류값","벨류값","벨류값" ,...]
		// key가 존재하지 않으면 null

		// 넘겨받은 값을 꺼내보자
		String name = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message");

		String pizza = request.getParameter("pizza");
		String[] toppings = request.getParameterValues("topping");
		String[] sides = request.getParameterValues("side");

		String payment = request.getParameter("payment");

		// 3) 요청처리 (db에 sql문 실행 > service > dao)

		int price = 0;
		switch (pizza) {
		case "콤비네이션":
			price += 5000;
			break;
		case "치즈":
		case "포테이토":
			price += 6000;
			break;
		case "고구마":
			price += 7000;
			break;
		case "하와이안":
			price += 8000;
			break;
		}

		if (toppings != null) {
			for (int i = 0; i < toppings.length; i++) {
				switch (toppings[i]) {
				case "고구마무스":
				case "페퍼로니":
					price += 1500;
					break;
				case "포테이토":
				case "치즈크러스트":
					price += 2000;
					break;
				case "치즈바이트":
				case "파인애플":
					price += 3000;
					break;
				}
			}
		}

		if (sides != null) {
			for (int i = 0; i < sides.length; i++) {
				switch (sides[i]) {
				case "콜라":
				case "사이다":
					price += 2000;
					break;
				case "스파게티":
				case "버팔로윙":
					price += 4500;
					break;
				case "피클":
				case "갈릭":
					price += 500;
					break;
				}
			}
		}
		// 4) 요청처리 후 사용자가 보게 될 응답페이지(결제페이지) 만들어서 넘겨주기
		// 응답페이지(jsp)를 선택해서 포워딩
		// 단, 응답페이지에 필요한 데이터가 없다면 담아서 포워딩 할 것
		
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);
		
		request.setAttribute("pizza", pizza);
		request.setAttribute("topping", toppings);
		request.setAttribute("side", sides);

		request.setAttribute("payment", payment);
		request.setAttribute("price", price);
		
		// 응답할 뷰 선택
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/pizzaPayment.jsp");
		// 선택된 뷰가 사용자에게 보여지도록 포워딩
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
