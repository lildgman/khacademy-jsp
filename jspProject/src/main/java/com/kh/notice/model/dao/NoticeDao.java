package com.kh.notice.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import static com.kh.common.JDBCTemplate.*;

import com.kh.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop = new Properties();

	public NoticeDao() {
		// classes 폴더 밑에서 찾으니까 아래 경로에 찾아가 xml 파일 가져오자
		String filePath = NoticeDao.class.getResource("/db/sql/notice-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Notice> selectNoticeList(Connection conn) {
		// select -> ResultSet(여러행) -> ArrayList<Notice>
		ArrayList<Notice> list = new ArrayList<Notice>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectNoticeList");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Notice notice = new Notice(rset.getInt("NOTICE_NO"), rset.getString("NOTICE_TITLE"),
						rset.getString("USER_ID"), rset.getInt("count"), rset.getDate("CREATE_DATE"));
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertNotice(Connection conn, Notice notice) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, Integer.parseInt(notice.getWriter()));

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int increaseCount(Connection conn, int noticeNo) {
		// update문 -> 처리된 행의 수 -> 트랜잭션 처리

		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("increaseCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Notice selectNotice(Connection conn, int noticeNo) {
		//select -> ResultSet(한행) -> Notice
		
		Notice notice = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectNotice");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				notice = new Notice(
						rset.getInt("notice_no"),
						rset.getString("notice_title"),
						rset.getString("notice_content"),
						rset.getString("user_id"),
						rset.getDate("create_date")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return notice;
	}

	public int updateNotice(Connection conn, int noticeNo, String title, String content) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateNotice(Connection conn, Notice notice) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, notice.getNotice_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		// update -> 처리된 행수 반환
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
