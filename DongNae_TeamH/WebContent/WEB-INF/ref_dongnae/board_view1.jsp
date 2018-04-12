<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/board_view.css">
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById('rbtn').onclick=function(){
			
			if(document.cfrm.cwriter.value.trim()==""){
				alert("이름을 입력해 주세요. ");
				return false;
			}
			if(document.cfrm.ccontent.value.trim()==""){
				alert("제목을 입력해 주세요. ");
				return false;
			}
			if(document.cfrm.cpassword.value.trim()==""){
				alert("비밀번호를 입력해 주세요. ");
				return false;
			}
			
			// 전송
			document.cfrm.submit();
		};
		 document.getElementById('cdelbtn').onclick=function(){
			if($('#cpw1').val().trim()==""){
				alert("비밀번호를 입력해 주세요.");
				return false;
			}
			document.repform.submit();
			
		}
	});
	</script>
</head>

<body>
<!-- 상단 디자인 -->
<div class="contents1"> 
	<div class="con_title"> 
		<p style="margin: 0px; text-align: right">
			<img style="vertical-align: middle" src="./images/home_icon.gif" /> &gt; 커뮤니티 &gt; <strong>여행지리뷰</strong>
		</p>
	</div>

	<div class="contents_sub">	
	<!--게시판-->
		<div class="board_view">
			<table>
			<tr>
				<th width="10%">제목</th>
				<td width="60%"> ${to.subject }</td>
				<th width="10%">등록일</th>
				<td width="20%">${to.wdate }</td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td>${to.writer }</td>
				<th>조회</th>
				<td>${to.hit } </td>
			</tr>
			<tr>
				<td colspan="4" height="200" valign="top" style="padding:20px; line-height:160%">
					<div id="bbs_file_wrap">
						<div>
							<img src="./upload/${to.fileName }" width="300" onerror="" /><br />
						</div>
					</div>
					${to.content }
				</td>
			</tr>			
			</table>
			
			<table>
			<c:forEach var="i" items="${to2 }" varStatus="rs">
			
			<tr id="tr"> 
				<td class="coment_re" >
					<strong>${i.writer }</strong> ${i.wdate }
					<div class="coment_re_txt">
						${i.content }
					</div>
				</td>
				<td class="coment_re" width="20%" align="right">
				<!--  -->
				<form action="./rep_del.do" method="post" name="repform">
					<input type="hidden" name="seq" value="${i.seq }"/>
					<input type="hidden" name="pseq" value="${to.seq }"/>
					<input type="password" id="cpw1" name="cpassword1" placeholder="비밀번호" class="coment_input pR10" />
					<input type="submit" id="cdelbtn" class="btn_comment btn_txt02" style="cursor: pointer;" value="삭제">
				</form>
				<!--  -->
				</td>
			</tr>
			</c:forEach>
			</table>

			<!-- 코멘트 폼 -->
			<form action="./write_rep.do" method="post" name="cfrm">
			<input type="hidden" name="seq" value="${to.seq }">
			<table>
			<tr>
				<td width="94%" class="coment_re">
					글쓴이 <input type="text" name="cwriter" maxlength="5" class="coment_input" />&nbsp;&nbsp;
					비밀번호 <input type="password" name="cpassword" class="coment_input pR10" />&nbsp;&nbsp;
				</td>
				<td width="6%" class="bg01"></td>
			</tr>
			<tr>
				<td class="bg01">
					<textarea name="ccontent" cols="" rows="" class="coment_input_text"></textarea>
				</td>
				<td align="right" class="bg01">
					<button type="button" id="rbtn" class="btn_re btn_txt01">댓글등록</button>
				</td>
			</tr>
			</table>
			</form>
			<!-- 폼 끝 -->
			
		</div>
		<div class="btn_area">
			<div class="align_left">			
				<button type="button" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./list.do'">목록</button>
			</div>
			<div class="align_right">
				<button type="button" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./modify.do?seq=${to.seq}'">수정</button>
				<button type="button" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./delete.do?seq=${to.seq}'">삭제</button>
			</div>	
		</div>
		<!--//게시판-->
	</div>
<!-- 하단 디자인 -->
</div>

</body>
</html>
