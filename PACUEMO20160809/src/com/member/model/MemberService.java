package com.member.model;

public class MemberService {
	private MemberDAO_interface dao;
	
	public MemberService(){
		dao = new MemberDAO();
	}
	
	public MemberVO checkMailPwd(String mail,String pwd){
		MemberVO memberVO = dao.findByUserMail(mail);

		if(memberVO != null){
			if(pwd.equals(memberVO.getMemberPassword())){
				return memberVO;
			}
		}
		
		return null;
	}
	
	public String checkMail(String mail){
		MemberVO memberVO = dao.findByUserMail(mail);
		
		if(memberVO != null){
			return "false";
		}
		
		return "true";
	}
}
