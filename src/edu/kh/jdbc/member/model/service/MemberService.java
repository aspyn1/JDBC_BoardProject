package edu.kh.jdbc.member.model.service;

import java.sql.Connection;

import static edu.kh.jdbc.common.JDBCTemplete.*;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	public Member myInformation(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		member = dao.myInformation(conn, member);
		
		close(conn);
		
		return member;
	}

}
