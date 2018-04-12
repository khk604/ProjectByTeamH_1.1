<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model2.BoardTO"%>
<%@ page import="model2.BoardListTO"%>
<%@ page import="model2.BoardDAO"%>
<%@ page import="java.util.ArrayList"%>
<%
	// 페이징 처리된 listTO 를 받는다.
	BoardListTO listTo=(BoardListTO)request.getAttribute("list");
	StringBuffer html = new StringBuffer();
	// 데이터를 받기 위해 배열에 보드 타입의 blist 를 생성하고 listTO 안에 있는 데이터를 받아온다. 
	ArrayList<BoardTO> blist=listTo.getBoardLists();
	// 데이터의 수만큼 포문을 돌리고 
	// 데이터의 컬럼을 갖고 오기 위해 boardTO 타입의 to을 한바퀴마다 생성한다.
	for (int i = 0; i < blist.size(); i++) {
		BoardTO to=blist.get(i);
		String seq = to.getSeq();
		String subject = to.getSubject();
		String writer = to.getWriter();
		String wdate = to.getWdate();
		String hit = to.getHit();
		String filename =to.getFileName();
		int cmt=to.getCmt();
		int wgap = to.getWgap();

		if (i % 5 == 0) {
			html.append("<tr>");
			html.append("<td width='20%' class='last2'>");
			html.append("		<div class='board'>");
			html.append("			<table class='boardT'>");
			html.append("			<tr>");
			html.append("				<td class='boardThumbWrap'>");
			html.append("					<div class='boardThumb'>");
			html.append("						<a href='./view.do?seq=" + seq + "'><img src='./upload/"+ filename + "' border='0' width='100%' /></a>");
			html.append("					</div>");
			html.append("				</td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td>");
			html.append("					<div class='boardItem'>	");
			html.append("						<strong>" + subject + "</strong>");
			if(cmt==0){
				html.append("						<span class='coment_number'></span>");
			}else{
				html.append("						<span class='coment_number'><img src='./images/icon_comment.png' alt='commnet'>"+cmt+"</span>");
			}
			if(wgap==0){
				html.append("						<img src='./images/icon_hot.gif' alt='HOT'>");
			}
			html.append("					</div>");
			html.append("				</td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td><div class='boardItem'><span class='bold_blue'>" + writer + "</span></div></td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td><div class='boardItem'>" + wdate + "<font>|</font>" + hit + "</div></td>");
			html.append("			</tr>");
			html.append("			</table>");
			html.append("		</div>");
			html.append("	 </td>");
		} else if (i % 5 == 4) {
			html.append("<td width='20%' class='last2'>");
			html.append("		<div class='board'>");
			html.append("			<table class='boardT'>");
			html.append("			<tr>");
			html.append("				<td class='boardThumbWrap'>");
			html.append("					<div class='boardThumb'>");
			html.append("						<a href='./view.do?seq=" + seq + "'><img src='./upload/" + filename + "' border='0' width='100%' /></a>");
			html.append("					</div>");
			html.append("				</td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td>");
			html.append("					<div class='boardItem'>	");
			html.append("						<strong>" + subject + "</strong>");
			if(cmt==0){
				html.append("						<span class='coment_number'></span>");
			}else{
				html.append("						<span class='coment_number'><img src='./images/icon_comment.png' alt='commnet'>"+cmt+"</span>");
			}
			if(wgap==0){
				html.append("						<img src='./images/icon_hot.gif' alt='HOT'>");
			}
			html.append("					</div>");
			html.append("				</td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td><div class='boardItem'><span class='bold_blue'>" + writer + "</span></div></td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td><div class='boardItem'>" + wdate + "<font>|</font>" + hit + "</div></td>");
			html.append("			</tr>");
			html.append("			</table>");
			html.append("		</div>");
			html.append("	 </td>");
			html.append("</tr>");
		} else {
			html.append("<td width='20%' class='last2'>");
			html.append("		<div class='board'>");
			html.append("			<table class='boardT'>");
			html.append("			<tr>");
			html.append("				<td class='boardThumbWrap'>");
			html.append("					<div class='boardThumb'>");
			html.append("						<a href='./view.do?seq=" + seq + "'><img src='./upload/" + filename + "' border='0' width='100%' /></a>");
			html.append("					</div>");
			html.append("				</td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td>");
			html.append("					<div class='boardItem'>	");
			html.append("						<strong>" + subject + "</strong>");
			if(cmt==0){
				html.append("						<span class='coment_number'></span>");
			}else{
				html.append("						<span class='coment_number'><img src='./images/icon_comment.png' alt='commnet'>"+cmt+"</span>");
			}
			if(wgap==0){
				html.append("						<img src='./images/icon_hot.gif' alt='HOT'>");
			}
			html.append("					</div>");
			html.append("				</td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td><div class='boardItem'><span class='bold_blue'>" + writer + "</span></div></td>");
			html.append("			</tr>");
			html.append("			<tr>");
			html.append("				<td><div class='boardItem'>" + wdate + "<font>|</font>" + hit + "</div></td>");
			html.append("			</tr>");
			html.append("			</table>");
			html.append("		</div>");
			html.append("	 </td>");
		}
	}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/board_list.css">
<style type="text/css">

.board_pagetab {
	text-align: center;
}

.board_pagetab a {
	text-decoration: none;
	font: 12px verdana;
	color: #000;
	padding: 0 3px 0 3px;
}

.board_pagetab a:hover {
	text-decoration: underline;
	background-color: #f2f2f2;
}

.on a {
	font-weight: bold;
}

</style>
</head>

<body>
	<!-- 상단 디자인 -->
	<div class="contents1">
		<div class="con_title">
			<p style="margin: 0px; text-align: right">
				<img style="vertical-align: middle" src="./images/home_icon.gif" />
				&gt; 커뮤니티 &gt; <strong>여행지리뷰</strong>
			</p>
		</div>
		<div class="contents_sub">
			<div class="board_top">
				<div class="bold">
					<p>
						총 <span class="txt_orange"><%=listTo.getTotalRecord() %></span>건
					</p>
				</div>
			</div>

			<!--게시판-->
			<table class="board_list">
				<%=html%>
			</table>
			<!--//게시판-->

			<div class="align_right">
				<button type="button" class="btn_write btn_txt01"
					style="cursor: pointer;" onclick="location.href='./write.do'">쓰기</button>
			</div>
			<!--페이지넘버-->
			<div class="paginate_regular">
				<div class="board_pagetab" >
					<%
						int startBlock = listTo.getStartBlock();
						int endBlock = listTo.getEndBlock();
						if (startBlock == 1) {
							out.println("<span class='off'>&lt;&lt;</span>");
						} else {
							out.println("<span class='on'><a href='./list.do?cpage=" + (startBlock - listTo.getBlockPerPage())
									+ "'>&lt;&lt;</a></span>");
						}
					%>
					&nbsp;
					<%
						if (listTo.getCpage() == 1) {
							out.println("<span class='on'>&lt;</span>");
						} else {
							out.println("<span class='on'><a href='./list.do?cpage=" + (listTo.getCpage() - 1) + "'>&lt;</a></span>");
						}
					%>
					&nbsp;&nbsp;
					<%
						for (int i = startBlock; i <= endBlock; i++) {
							System.out.print(endBlock);
							if (listTo.getCpage() == i) {
								out.println("<span class='on'>[" + i + "]</span>");
							} else {
								out.println("<span class='off'><a href='./list.do?cpage=" + i + "'>" + i + "</a></span>");
							}
						}
					%>
					&nbsp;&nbsp;
					<%
						if (listTo.getCpage() == listTo.getTotalPage()) {
							System.out.print(listTo.getTotalPage());
							out.println("<span class='on'>&gt;</span>");
						} else {
							out.println("<span class='on'><a href='./list.do?cpage=" + (listTo.getCpage() + 1) + "'>&gt;</a></span>");
						}
					%>
					<%
						if (endBlock == listTo.getTotalPage()) {
							out.println("<span class='off'>&gt;&gt;</span>");
						} else {
							out.println("<span class='on'><a href='./list.do?cpage=" + (startBlock + listTo.getBlockPerPage())
									+ "'>&gt;&gt;</a></span>");
						}
					%>
					&nbsp;

				</div>
			</div>
			<!--//페이지넘버-->
		</div>
	</div>
	<!--//하단 디자인 -->

</body>
</html>
