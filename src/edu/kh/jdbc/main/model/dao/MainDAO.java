package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplete.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MainDAO {
	
	// 필드
	// JDBC 객체 참조 변수
	private Statement stmt; // SQL 수행, 결과반환
	private PreparedStatement pstmt; // placehoder(?)를 포함한 SQL 셋팅/수행
	private ResultSet rs; // SELECT 수행 결과 저장		
	private Properties prop;
	// -Map<String, String> 형태
	// - XML파일 입/출력 메서드를 제공
	
	// 기본생성자
	public MainDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-sql.xml"));
			
			// -> prop.getProperty("key") 호출
			// -> value(SQL) 반환
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 로그인 DAO (== 아이디, 비밀번호 일치 회원 조회)
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return
	 * @throws Exception
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception{
		
		// 1. 결과 저장용 변수 선언
		Member member = null;
		
		try {
			
			// 2. SQL 작성 후 수행
			
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			// placehoder에 알맞은 값대입
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery(); // SELECT 수행 수 결과 반환 받기
			
			// 3. 조회결과를 1행씩 접근해서 얻어오기
			if(rs.next()) {
				
				int memberNo = rs.getInt("MEMBER_NO");
//				String memberId = rs.getString("MEMBER_ID");
				// 입력받은 아이디 == 조회한 아이디 이므로
				// db에서 얻어올 필요가 없음
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDate = rs.getString("ENROLL_DT");
				
				// Member 객체 생성 후 값 셋팅
				member = new Member();
				
				member.setMemberNo(memberNo);
				member.setMemberId(memberId);
				member.setMemberName(memberName);
				member.setMemberGender(memberGender);
				member.setEnrollDate(enrollDate);
				
			}
			
		}finally {
			// 4. 사용한 JDBC 객체 자원 반환
			close(pstmt);
			close(rs);

		}
		
		return member;
	}

	public int signup(Connection conn, Member member) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("signup");
			
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			
			result = pstmt.executeUpdate();
			
		}finally {
			
			close(pstmt);
		}
		
		return result;
	}



}
