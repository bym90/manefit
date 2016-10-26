<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!-- jQeury -->
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="../resources/js/admin.js"></script>
	
	<!-- Menu CSS -->
	<link rel="stylesheet" href="../resources/css/nav.css">
    <link rel="stylesheet" href="../resources/css/owl.carousel.css">
    <link rel="stylesheet" href="../resources/css/owl.theme.css">
    <link rel="stylesheet" href="../resources/css/side.css">
    <link rel="stylesheet" href="../resources/css/common.css">
                                 
    <!-- slider CSS -->          
    <link rel="stylesheet" href="../resources/css/MainSlider.css">
                                 
    <!-- admin css -->           
    <link rel="stylesheet" href="../resources/css/admin.css">
                                 
 	<!-- Bootstrap Core CSS -->  
	<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap-theme.css">
	<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap.css">
	<link rel="stylesheet" href="../resources/css/JKitBoot.css">
	
	<!-- alert library -->
	<link rel="stylesheet" href="../resources/css/sweetalert.css">
	<script src="../resources/js/jquery/sweetalert.min.js"></script>

	<!-- font-awesome -->
	<script src="https://use.fontawesome.com/a849bb573c.js"></script>
	
	<!-- google font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans">
	
	<!-- Bootstrap Core JavaScript -->
	<script src="../resources/js/bootstrap/bootstrap.js"></script>
	
	<!-- slider js -->
    <script src="../resources/js/owl.carousel.js"></script>
    <script src="../resources/js/bootstrap-collapse.js"></script>
    <script src="../resources/js/bootstrap-transition.js"></script>
    <script src="../resources/js/bootstrap-tab.js"></script>
    <script src="../resources/js/prettify.js"></script>
	<script src="../resources/js/application.js"></script>
	
	<!-- 스마트 에디터 js -->
	<script src="../resources/se/js/HuskyEZCreator.js"></script>
	
<title>ShoppingMall</title>
</head>

    <script>
    	$(document).ready(function(){
    		$("#signin").click(function(){
    			$(location).attr("href", "../Login/LoginForm.com");
    		});
    		$("#logout").click(function(){
    			$(location).attr("href", "../Login/LogoutProc.com")
    		})
       	});
    	
    	function link(lcate,mcate){
    		    		   		
    		$("#lcate").val(lcate);
    		$("#mcate").val(mcate);
    		$("#sfrm").attr("action","../Shopping/ShoppingGoodsList.com");
    		$("#sfrm").submit();
    	}
    	function mypageList(kind){
    		$("#kind3").val(kind);
    		$("#sfrm").attr("action","../Shopping/OrderList.com");
    		$("#sfrm").submit();
    		
    	}
    	function cartList100(){
    		$("#cfrm").attr("action","../MyPage/CartList.com");
    		$("#cfrms").submit();
    	}
    </script>
   	<script>
   		// 새창 열기
   		function OpenMessage(){
   			meg = window.open("../MyPage/Message.com", "Message", "location=0, scrollbars=1, status=0, width=500, height=400, realzable=0, channelmode=1, left=1050 top=150");
   			meg.focus();
   			checkChild();
   		}
   		// 새창 닫을시 부모창 리로드
   		function checkChild() {
   			if(meg.closed) {
   				window.location.reload(); 
   			} else{ 
   		      setTimeout("checkChild()",1); 
   		 	} 
   		}
   	</script>
    
   
<body>

	<script src="../resources/js/mainslider.js"></script>
	
	<div class="BACKGROUND1">
	<div align="right">
		<c:if test="${!empty sessionScope.ID}">
		<a class="btn btn-default btn-sm" href="#" id="my">${NAME} 님 <i class="fa fa-user fa-1x" aria-hidden="true">등급 : ${GRADE }</i></a>
		</c:if>
		<c:if test="${empty sessionScope.ID}">
		<a class="btn btn-default btn-sm" href="#" id="signin"><i class="fa fa-sign-in fa-1x" aria-hidden="true">Login</i></a>
		</c:if>
		<c:if test="${!empty sessionScope.ID}">
		<a class="btn btn-default btn-sm" href="#" id="logout"><i class="fa fa-sign-out fa-1x" aria-hidden="true">Logout</i></a>
		</c:if>
		<c:if test="${empty sessionScope.ID}">
		<a class="btn btn-default btn-sm" href="../Login/SignUpForm.com" id="signup"><i class="fa fa-user-plus fa-1x" aria-hidden="true">Sign-up</i></a>
		</c:if>
		<c:if test="${sessionScope.GRADE ne '관리자'}">
		<c:if test="${!empty sessionScope.ID}">
		<a class="btn btn-default btn-sm" href="../MyPage/CartList.com" id = "cart" ><i class="fa fa-shopping-cart fa-1x" aria-hidden="true">Cart</i></a>
		<a class="btn btn-default btn-sm" href="../MyPage/Favorite.com" id="jjim"><i class="fa fa-heart fa-1x" aria-hidden="true">찜</i></a>
		<a class="btn btn-default btn-sm" href="#" id="cart" onclick="OpenMessage()">
		<i class="fa fa-sticky-note-o fa-1x" aria-hidden="true"> Message 
		<c:if test = "${sessionScope.MEG gt 0}"><img src = "../resources/img/new.gif" style="width:45px;height:20px"></c:if></i></a>
<%-- 		<c:if test = "${sessionScope.MEG gt 0}"> --%>
<!-- 		<a class="btn btn-default btn-sm" href="#" id="cart" onclick="OpenMessage()"> -->
<!-- 		<i class="fa fa-sticky-note-o fa-1x" aria-hidden="true"> -->
<!-- 		Message <img src = "../resources/img/new.gif" style="width:45px;height:20px"> </i></a> -->
<%-- 		</c:if> --%>
		<a class="btn btn-default btn-sm" href="#" id ="myPage" onclick = "mypageList(1)"><i class="fa fa-user fa-1x" aria-hidden="true"></i> MyPage </a>

		</c:if>
		</c:if>
		<c:if test="${sessionScope.GRADE eq '관리자' }">
		<a class="btn btn-default btn-sm" href="../Admin/AdminUser.com" id="usermanage"><i class="fa fa-user fa-1x" aria-hidden="true">회원관리</i></a>
		<a class="btn btn-default btn-sm" href="../Admin/AdminGoodsAdd.com" id="regist"><i class="fa fa-th-large fa-1x" aria-hidden="true">상품등록</i></a>
		<a class="btn btn-default btn-sm" href="../Admin/AdminOrder.com" id="order"><i class="fa fa-shopping-bag fa-1x" aria-hidden="true">주문관리</i></a>
		<a class="btn btn-default btn-sm" href="../Admin/AdminStock.com" id="inven"><i class="fa fa-cube fa-1x" aria-hidden="true">재고관리</i></a>
		<a class="btn btn-default btn-sm" href="../Admin/AdminBoard.com" id="inven"><i class="fa fa-list fa-1x" aria-hidden="true">게시판관리</i></a>
		<a class="btn btn-default btn-sm" href="../Admin/AdminBannerAdd.com" id="bannerimg"><i class="fa fa-picture-o fa-1x" aria-hidden="true">배너등록</i></a>
		</c:if>
	</div>
	</div>
	
	<div align="center">
		<a href="#"><img src="../resources/img/manefit2.png" class="MAIN_BANNER1"/></a>
	</div>
	
	
	<div class="nav_wrapper"> 
  <div class="spinner-master">
    <input type="checkbox" id="spinner-form" />
    <label for="spinner-form" class="spinner-spin">
    <div class="spinner diagonal part-1"></div>
    <div class="spinner horizontal"></div>
    <div class="spinner diagonal part-2"></div>
    </label>
  </div>
<!--   <a href="#search_box" class="btn" id="search">&#9740;</a> -->
 
 
 <form id = "sfrm" method ="post">
   <nav id="menu" class="menu">
    <ul class="dropdown">
      <li><a href="../" title="Link">Home</a>
	  <li><a href="../Shopping/NewAndBest.com?kind=New" title="Link" >New</a>
	  <li><a href="../Shopping/NewAndBest.com?kind=Best" title="Link">Best50</a>
     
    <c:forEach var = "Lcate" items = "${LCATE}">
        <li ><a href="#" onClick ="link('${Lcate.cate}')">${Lcate.name}</a>      	 
        <ul >
        <c:forEach var = "Mcate" items = "${MCATE}">
        <c:if test = "${Mcate.cate eq Lcate.cate}">
			<li ><a href="#" onClick ="link('${Lcate.cate}','${Mcate.mcate}')">${Mcate.name}</a>
            </li>
        </c:if>
        </c:forEach>
        </ul>
      </li>
  </c:forEach>
   
       <li ><a href="../FAQ/List.com" title="Link">FAQ</a></li>
      	 <ul >
          <li ><a href="../Admin/AdminBannerAdd.com" title="Link">배너등록</a></li>
          <li ><a href="../Admin/AdminGoodsAdd.com" title="Link">상품등록</a></li>
         </ul>
      </li>
    </ul>
	  </nav>
	  <input type = "hidden" id = "lcate" name = "lcate">
	  <input type = "hidden" id = "mcate" name = "mcate">
	  <input type = "hidden" id = "getcolor" name = "getcolor">
	  <input type = "hidden" id = "tema" name = "tema">	  
	  <input type = "hidden" id = "kind3" name = "kind3">	 
	  <input type = "hidden" id = "sort" name = "sort"> 
	  </form>
<!-- 	 <form class="search_box" id="search_box" action="/search/"> -->
<!-- 	    <input name="search_criteria" placeholder="Search" value="" type="text"> -->
<!-- 	    <input class="search_icon" value="Search" type="submit"> -->
<!-- 	  </form> -->
	</div>
	

		<script src="../resources/js/jquery/nav.js"></script>
			