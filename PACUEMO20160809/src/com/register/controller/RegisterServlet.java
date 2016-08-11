package com.register.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;

@WebServlet("/checkMail.do")
public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String model = request.getParameter("model");
		String mail = request.getParameter("email");
		
		
		
		MemberService ms;
		ms = new MemberService();
		
		
		if("checkMail".equals(model)){
			
		out.write(ms.checkMail(mail));	
		
		}
	
	}
}
