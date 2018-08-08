package com.cos.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cos.action.Action;
import com.cos.dao.MemberDAO;
import com.cos.dto.MemberVO;
import com.cos.util.Script;

public class MemberUpdateAction implements Action{
	private static String naming = "MemberUpdateAction : ";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "main.jsp";
		
		MemberVO member = new MemberVO();
		MemberDAO dao = new MemberDAO();
				
		int result = dao.update(member);
				
		if(result == 1) {
			
			HttpSession session = request.getSession();
			session.setAttribute("id", member.getId());
			Script.moving(response, "데이터가 변경이 되었습니다.", url);
			
		}else if(result == 2) {
			
			Script.moving(response, "데이터 변경에 실패했습니다.");
			
		}
				
	}
				
		
}
