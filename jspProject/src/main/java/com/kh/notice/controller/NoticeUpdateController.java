package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.vo.Notice;
import com.kh.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNotice_no(noticeNo);
		notice.setTitle(title);
		notice.setContent(content);
		
		int result = new NoticeService().updateNotice(notice);
		
		if(result == 0) {
			// 실패시 -> error
			// 에러페이지는 직접 찾아갈 일이 없어 따로 url이 필요하지 않다.
			// ==> 포워딩 ==> 포워딩 시에는 request를 전달 할 수 있음
			request.setAttribute("errorMsg", "공지사항 수정에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		} else {
			// 성공 시 -> /kh/detail.no?num=
			// 재요청(redirect)을 보내야함
			// 지금 페이지는 kh/update.no이다. 이 페이지는 수정 요청 페이지이다.
			// 하지만 내가 다음으로 보여주고 싶은 페이지는 상세페이지 이다. -> /kh/detail.no?num=
			// url 경로가 다르므로 재요청을 통해 화면과 url을 맞춰주면 된다.
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항을 수정하였습니다.");
			response.sendRedirect(request.getContextPath() + "/detail.no?num=" + notice.getNotice_no());
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
