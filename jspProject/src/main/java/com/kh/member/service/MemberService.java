package com.kh.member.service;

import java.sql.Connection;

import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = getConnection();
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		close(conn);
		return m;
	}
	
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, member);
		
		if (result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Member updateMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, member);
		Member updateMember = null;
		
		if(result > 0) {
			commit(conn);
			// 갱신된 회원 객체 다시 조회해오기 잊지말자!
			updateMember = new MemberDao().selectMember(conn, member.getUserId());
		} else {
			rollback(conn);
		}
		
		close(conn);
		return updateMember;
	}

	public Member updatePwd(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		int result = new MemberDao().updatePwd(conn, userId, userPwd ,updatePwd);
		Member updateMember = null;
		if(result > 0) {
			commit(conn);
			// db에서 불러와야함-> 변경됐는지 안됐는지 확인해야되자너
			updateMember = new MemberDao().selectMember(conn, userId);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return updateMember;
		
	}

	public int deleteMember(String userId, String userPwd) {
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int idCheck(String checkId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		return result;
	}
}
 