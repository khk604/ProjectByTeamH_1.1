<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	int flag=(Integer)request.getAttribute("flag");
	// 페이지 자동 이동
	out.println("<script type='text/javascript'>");
	if (flag == 0) {
		out.println("alert('삭제를 성공하였습니다.');");
		out.println("location.href='./list.do';");
	} else if (flag == 1) {
		out.println("alert('비밀번호를 다시 확인 해주세요.');");
		out.println("history.back();");
	} else if (flag == 2) {
		out.println("alert('삭제 실패 하셨습니다.');");
		out.println("history.back();");
	}
	out.println("</script>");
%>

