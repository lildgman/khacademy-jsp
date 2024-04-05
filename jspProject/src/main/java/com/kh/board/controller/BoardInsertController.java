package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.Attachment;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//일반 방식이 아닌 multipart/form-data로 전송하는 경우 request로 부터 값을 추출이 불가
//		String boardTitle = request.getParameter("title");
//		System.out.println(boardTitle);
		
		//먼저 multipart로 왔는지 확인해야함
		// enctype이 multipart/form-data로 잘 전송되었을 경우 전반적인 내용이 실행되도록 코드 작성
		if(ServletFileUpload.isMultipartContent(request)) {
			// 파일 업로드를 위한 라이브러리: cos.jar
			// www.servlets.com에서 다운로드
			
			// 1. 전달되는 파일을 처리할 작업내용(전달되는 파일의 용량제한, 저장시킬 폴더 경로설)
			// 1_1) 용량제한(byte 단위)
			
			int maxSize = 1024 * 1024 * 10; // 10mb
			
			// 1_2) 전달된 파일을 저장시킬 폴더 경로 알아내기
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfile/");
			
			// 2. 전달된 파일의 파일명 수정 및 서버에 업로드 작업 진행하기
			/**
			 * HttpServletRequest request -> MultipartRequest multiRequest로 변환
			 * 
			 * 위 구문 한 줄 실행만으로 넘어온 첨부파일이 해당 폴더에 업로드 된다.
			 * 단, 업로드 시 파일명을 수정해줘야한다. 그래서 파일명을 수정시켜주는 객체를 제시해야한다. 
			 *	=> 같은 파일명이 있을 경우 덮어씌워질 수 있고, 파일명에 한글이나 특수문자 띄어쓰기가 포함될 경우 서버에 따라 문제가 발생할 수 있다.
			
			 * 기본적으로 파일명이 안겹치도록 수정작업해주는 객체
			 * -> DefaultFileRenamePolicy 객체 (cos에서 제공)
			 * -> 내부적으로 rename(원본파일명) {
			 * 		기본에 동일한 파일명이 존재할 경우
			 * 		파일명 뒤에 숫자를 붙여줌
			 * 		ex) aaa.jpg, aaa1.jpg, aaa2.jpg
			 * 	  }
			 * 나만의 방식대로 절대 겹치치 않도록 rename 할 수 있게 FileRenamePolicy 클래스 만들기
			 * -> com.kh.common.MyFileRenamePolicy
			 */
//			MultipartRequest multiRequest = new MultipartRequest(request, 저장시킬 폴더 경로, 용량제한, 인코딩, new DefaultFileRenamePolicy());
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize,"UTF-8" , new MyFileRenamePolicy());
			
			// 3. DB에 기록할 데이터를 추출해서 VO에 차곡차곡 담아주자
			// > 카테고리번호, 제목, 내용, 작성자번호
			// > 첨부파일이 있다면 원본명, 수정명, 저장폴더 경로
			
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			String boardWriter = multiRequest.getParameter("userNo");
			
			Board board = new Board();
			board.setCategory(category);
			board.setBoard_title(boardTitle);
			board.setBoard_content(boardContent);
			board.setBoard_writer(boardWriter);
			
			Attachment attachment = null;
			
			if (multiRequest.getOriginalFileName("upfile") != null) {
				attachment = new Attachment();
				attachment.setOriginName(multiRequest.getOriginalFileName("upfile"));
				attachment.setChangeName(multiRequest.getFilesystemName("upfile"));
				attachment.setFilePath("resources/board_upfile/");
			}
			// 4. 서비스 요청
			int result = new BoardService().insertBoard(board, attachment);
			
			// 5. 응답해주기
			if(result > 0) { // 성공 -> 목록페이지(kh/list.bo?cpage=1)
				
				request.getSession().setAttribute("alertMsg", "일반게시글 작성 성공");
				response.sendRedirect(request.getContextPath()+"/list.bo?cpage=1");
				
			} else { // 실패 -> 업로드된 파일 삭제해주고 에러페이지 보내주기
				if(attachment != null) {
					new File(savePath + attachment.getChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "일반게시판 작성 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
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
