package com.kh.notice.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList() {
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		close(conn);
		return list;
	}

	public int insertNotice(Notice notice) {
		Connection conn = getConnection();
		int result = new NoticeDao().insertNotice(conn, notice);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Notice increaseCount(int noticeNo) {
		Connection conn = getConnection();
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		Notice notice = null;
		
		if (result > 0) {
			commit(conn);
			notice = new NoticeDao().selectNotice(conn, noticeNo);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return notice;
	}

	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		
		Notice notice = new NoticeDao().selectNotice(conn, noticeNo);
		
		close(conn);
		return notice;
	}

	public int updateNotice(Notice notice) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, notice);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
}
