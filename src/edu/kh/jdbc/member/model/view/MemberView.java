package edu.kh.jdbc.member.model.view;

import java.util.List;

import javax.annotation.processing.AbstractProcessor;

import edu.kh.jdbc.common.JDBCTemplete;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;
import oracle.jdbc.internal.StateSignatures;

public class MemberView {
	
	private MemberService service = new MemberService();

	/** 내 정보 조회
	 * 
	 */
	public void myInformation() throws Exception{
		
		System.out.println("<내 정보 조회>");
		
		Member member = service.myInformation();

		if(member == null) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		}else{
			System.out.println("  회원번호  |   회원아이디  |  회원이름  |  성별  |  가입일  ");
			System.out.println("-----------------------------------------------------------------");
			
			System.out.printf("  %d  |  %s  |  %s  |  %c  |  %s  ",
					member.getMemberNo(), member.getMemberId(), member.getMemberName(),
					member.getMemberGender(), member.getEnrollDate());
		}
		
	}

	/** 회원 목록 조회(아이디, 이름, 성별)
	 * 
	 */
	public void memberListLookup() throws Exception{
		
		System.out.println("<회원 목록 조회(아이디, 이름, 성별)>");
		
		List<Member> memberList = service.memberListLookup();
		
		if(memberList.isEmpty()) {
			
			System.out.println("등록된 회원 정보가 없습니다.");
		}
		
		
		
	}

	/** 내 정보 수정(이름, 성별)
	 * 
	 */
	public void changeMyInfo() {
		
	}

	/** 비밀번호 변경(현재 비밀번호, 새 비밀번호, 확인)
	 * 
	 */
	public void changePassword() {
		
	}

	/** 회원 탈퇴(보안코드, 비밀번호, UPDATE)
	 * 
	 */
	public void membershipWithdrawal() {
		
	}

}
