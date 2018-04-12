<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model2.BoardTO"%>

<%
	int flag = (Integer) request.getAttribute("flag");
	String seq = (String) request.getAttribute("seq");
	// 페이지 자동 이동
	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('수정 성공하였습니다. ');");
		out.println("location.href='./view.do?seq="+seq+"'");
	} else if (flag == 1) {
		out.println("alert('비밀번호를 다시 확인 해 주세요. ');");
		out.println("history.back();");
	} else if (flag == 2) {
		out.println("alert('수정에 실패 하였습니다.');");
		out.println("history.back();");
	}
	out.println("</script>");
%>


