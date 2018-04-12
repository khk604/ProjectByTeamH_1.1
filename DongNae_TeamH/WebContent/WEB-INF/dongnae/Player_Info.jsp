<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="HandheldFriendly" content="True">
	<meta name="MobileOptimized" content="320"/>
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta http-equiv="cleartype" content="on">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

	<title>Slide Menu by Tegan Snyder</title>

	<!--Include JQM and JQ-->
	<link rel="stylesheet" href="css/themes/jqmfb.min.css" />
	<link rel="stylesheet" href="http://code.jquery.com/mobile/latest/jquery.mobile.structure.min.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
	<script src="js/jquery.animate-enhanced.min.js"></script>
	
	<!--JQM globals you can edit or remove file entirely... note it needs to be loaded before jquerymobile js -->
	<script src="js/jqm.globals.js"></script>
	
	<script src="http://code.jquery.com/mobile/latest/jquery.mobile.min.js"></script>

	<!--JQM SlideMenu-->
	<link rel="stylesheet" href="css/jqm.slidemenu.css" />
	<script src="js/jqm.slidemenu.js"></script>
</head>
<body>

	<!-- 메뉴  -->
	<script type="text/javascript">
		$(document).ready(function(){
			$("ul.sub").hide();
			$("ul.menu li").click(function(){
				$("ul",this).slideToggle("fast");
			});
		});
	</script>

	<div id="slidemenu">
<!-- user profile  -->
      <div id="profile">
         <a href="My_page.jsp"><img src="./img/tegan.jpg"></a>
         <div class="profile_info"><strong>kim ju hun</strong><br><small>Mobile Developer</small></div>
      </div>

	<!-- navbar -->
      <h3>MENU</h3>

      <ul>
         <li><a href="Main.jsp"><img src="img/smico3.png">Home</a></li>
         <ul class="menu"> 
         <li><img src="img/smico3.png">우리팀
	        <ul class="sub">
		     <li><a href="Notice.jsp">공지사항</a></li>
		     <li><a href="Team_Info.jsp">팀 관리</a></li>
		     <li><a href="Player_Info.jsp">선수관리</a></li>
	   		</ul>
   		 </li>
   		 </ul>
   
         <li><a href="Game_Schedule.jsp"><img src="img/smico5.png">경기 관리</a></li>
         <li><a href="Game_Record.jsp"><img src="img/smico6.png">경기 결과</a></li>
      </ul>

   </div>

	<!-- main page -->
   <div data-role="page" id="main_page" data-theme="a">

	<!-- header -->
    <header data-role="header" data-position="fixed" data-tap-toggle="false" data-update-page-padding="false">
         <a href="#" data-slidemenu="#slidemenu" data-slideopen="false" data-icon="smico" data-corners="false" data-iconpos="notext">Menu</a>
         <h1>동네 축구</h1>
    </header>

	<!-- main page content -->
    <div data-role="content">
     <p>player info Page</p>
    </div>
      
      <!-- footer -->
    <div data-role="footer" data-position="fixed">
 	 <h3>화면 하단</h3>
 	</div>

   </div>
   
   
</body>
</html>