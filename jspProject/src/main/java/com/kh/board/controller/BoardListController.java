package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징 처리
		// 페이징 처리를 하기 위해서는 게시글 수가 필요하다
		
		int listCount; // 현재 총 게시글 수
		int currentPage; // 현재 페이지 (사용자가 요청한 페이지)
		int pageLimit; // 하단에 보여질 페이징바의 페이지 개수
		int boardLimit; // 한 페이지 내에 보여질 게시글 최대개수
		// 위 4개 값을 기준으로 아래 3개 값을 구해야 한다.
		
		int maxPage; //가장 마지막 페이지(총 페이지 수)
		int startPage; // 페이징 바의 시작 수
		int endPage; // 페이징 바의 마지막 끝 수
		
		// listCount: 총 게시글 수
		listCount = new BoardService().selectListCount();
		
		// currentPage : 현재 페이지(사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
	
		// pageLimit: 페이징 바의 최대개수
		pageLimit = 10;
		
		// boardLimit: 한 페이지내에 보여질 게시글 최대개수 
		boardLimit = 10;
		
		/**
		 * maxPage = 제일 마지막 페이지 수(총 페이지 수)
		 * 
		 * 총 게시글 개수 / boardLimit => 올림처리
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		/**
		 * startPage : 페이징바 시작 수
		 * 
		 * startPage = ((currentPage -1) / pageLimit) * pageLimit + 1;
		 */
		startPage = ((currentPage -1) / pageLimit) * pageLimit + 1;
		
		/**
		 * endPage: 페이징바의 끝수
		 * 
		 * pageLimit: 10이라는 가정하에
		 * 
		 * startPage: 1-> endPage : 10,
		 * startPage: 11-> endPage : 20,
		 * startPage: 21-> endPage : 30
		 * 
		 * 
		 */
		
		endPage = startPage + pageLimit -1;
		
		// startPage가 11이면 endPage는 20
		// maxPage가 13이라면?
		
		endPage = endPage > maxPage ? maxPage : endPage;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit,boardLimit,maxPage, startPage,endPage);

		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
