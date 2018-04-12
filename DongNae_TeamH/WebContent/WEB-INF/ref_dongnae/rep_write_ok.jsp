<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int flag = (Integer) request.getAttribute("flag");
	String pseq = (String)request.getAttribute("to");
	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('코멘트를 등록 하였습니다.');");
		out.println("location.href='./view.do?seq="+pseq+"';");
	} else if (flag == 1) {
		out.println("alert('코멘트 등록을 실패하였습니다.')");
		out.println("history.back()");
	}
	out.println("</script>");
%>
