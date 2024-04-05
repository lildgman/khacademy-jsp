package com.kh.board.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.Reply;
import com.kh.common.Attachment;
import com.kh.common.vo.PageInfo;

public class BoardService {

	public int selectListCount() {
		Connection conn = getConnection();
		int result = new BoardDao().selectListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Board> list= new BoardDao().selectList(conn,pi);
		
		close(conn);
		return list;
	}

	public Board increaseCount(int boardNo) {
		Connection conn = getConnection();
		Board board = null;
		BoardDao bDao = new BoardDao();
		int result = bDao.increaseCount(conn, boardNo);
		
		if (result > 0) {
			commit(conn);
			board = bDao.selectBoard(conn, boardNo);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return board;
	}

	public ArrayList<Category> selectCategoryList() {
		Connection conn = getConnection();
		
		ArrayList<Category> result = new BoardDao().selectCategoryList(conn);
		
		close(conn);
		return result;
	}

	public int insertBoard(Board board, Attachment attachment) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		int result1 = bDao.insertBoard(conn, board);
		int result2 = 1;
		
		if(attachment != null) {
			result2 = bDao.insertAttachment(conn,attachment);
		}
		
		if(result1 > 0 && result2 > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}

	public Attachment selectAttachment(int boardNo) {
		Connection conn = getConnection();
		Attachment attachment = new BoardDao().selectAttachment(conn, boardNo);
		close(conn);
		return attachment;
	}

	public Board selectBoard(int boardNo) {
		Connection conn = getConnection();
		Board board = new BoardDao().selectBoard(conn, boardNo);
		close(conn);
		return board;
	}

	public int updateBoard(Board board, Attachment at) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		int result1 = bDao.updateBoard(conn, board);
		
		int result2 = 1;
		if(at != null) { // 새 첨부파일이 있을 때
			if(at.getFileNo() != 0) { // 기존 첨부파일이 있었을 경우 -> update
				result2 = bDao.updateAttachment(conn, at);
			} else { // 기존 첨부파일이 없음 -> insert
				result2 = bDao.insertNewAttachment(conn, at);
			}
		}

		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}

	public int insertThumbnailBoard(Board board, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertThumbnailBoard(conn, board);
		int result2 = new BoardDao().insertAttachmentList(conn, list);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}

	public ArrayList<Board> selectThumbnailList() {
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		return list;
	}

	public ArrayList<Attachment> selectAttachmentList(int boardNo) {
		Connection conn = getConnection();
		ArrayList<Attachment> list = new BoardDao().selectAttachmentList(conn, boardNo);
		
		close(conn);
		return list;
	}

	public int insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public ArrayList<Reply> selectReplyList(int boardNo) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		close(conn);
		return list;
	}

}
