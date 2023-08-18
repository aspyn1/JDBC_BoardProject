package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplete.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	private Properties prop;
	
	public MemberDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public Member myInformation(Connection conn, Member member) throws Exception{
		
		try {
			
			String sql = prop.getProperty("myInformation");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDate = rs.getString("ENROLL_DT");
				
				member = new Member(memberNo, memberId, memberName, memberGender, enrollDate);
			}
			
		}finally {
			
			close(conn);
			close(rs);
		}
		
		return member;
	}

}
