<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>
<script>
$(window).scroll(function()  
	    {  
	        $('#banner').animate({top:$(window).scrollTop()+"px" },{queue: false, duration: 350});    
	    });  
	    //when the close button at right corner of the message box is clicked   
	    $('#banner').click(function()  
	    {  
	        //the messagebox gets scrool down with top property and gets hidden with zero opacity   
	        $('#banner').animate({ top:"+=15px",opacity:0 }, "slow");  
	    });
	    
function catesubmit(lcate,mcate){
	
	$("#lcate").val(lcate);
	$("#mcate").val(mcate);
	$("#sfrm").attr("action","../Shopping/ShoppingGoodsList.com");
	$("#sfrm").submit();
}

function getList(lcate,mcate,color){
	
	$("#lcate").val(lcate);
	$("#mcate").val(mcate);
	$("#getcolor").val(color);
	$("#sfrm").attr("action","../Shopping/ShoppingGoodsList.com");
	$("#sfrm").submit();
}

function getTamaList(lcate,mcate){
	
	var tema = document.getElementById("temaList").value;
		
	$("#lcate").val(lcate);
	$("#mcate").val(mcate);
	$("#tema").val(tema);
	$("#sfrm").attr("action","../Shopping/ShoppingGoodsList.com");
	$("#sfrm").submit();
}

</script>

<style>

.colormagin{
	margin-bottom : 5%;
	margin-top : 10px;
	margin-left : 45%;
}
.temamagin{
	margin-left : 100px;
}

.OuterButton {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 16px 30px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    margin: 4px 2px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    cursor: pointer;
}

.OuterButton5 {
  	background-color: white;
    color: black;
    border: 2px solid black;
	border-radius: 40px;
}

.OuterListbutton {
    border: none;
    padding: 8px 13px;
    text-align: center;
    font-size: 15px;
    margin: 2px 1px;
    font-weight:bold;
    font-family:Impact; 
}

.OuterListbutton:hover {
    border-bottom: solid #080808 2px;
}
.OuterListbutton5 {
    background-color: white;
   
}

.listdiv{
	border : 1px solid black
}

#banner {
position: absolute;
width : 100%;
height : 100%;
z-index: 10;
padding:5px;

text-align:center;
font-weight:bold;
}
</style>


<br><br><br>

<!-- 장바구니  -->

<div style="position:absolute;float:left;width:100px;height:250px;top:350px;left:90%;">  
        <div id="banner">
			<a href ="../MyPage/CartList.com"><img src ="../resources/img/cart1.jpg" width = "100" height = "130"></a>
			<a href ="#" onclick="mypageList(1)"><img src ="../resources/img/mypage.png" width = "100" height = "130"></a>
		</div>  
</div>



<br><br>

<div id="show3">
	
	<div class="container">
		<c:if test="${KIND eq 'New'}">
			<h1>New</h1>
			</c:if>
		<c:if test="${KIND eq 'Best'}">
			<h1>Best 50</h1>
		</c:if>
		<hr>
		<div class="row">
		
			<div class="span12">
				
				<c:forEach var = "data" items = "${LIST}">
		
				<div class="img" style="height:420px;margin-top:20px"><a href = "../Shopping/ShoppingGoodsView.com?cate=${data.cate}">
					<img src="../resources/img/Goods/${data.savename}" alt="Trolltunga Norway" height = "300" ></a>
					<div class="desc" style = "text-align:left">
					 <div style="width:60px;height:20px;position:relative;top:-10px;left:-10px;"><c:if test = "${data.status eq '1' }"><img src = "../resources/img/new.gif" style="width:45px;height:20px"></c:if>
					 <c:if test = "${data.status eq '3' }"><img src = "../resources/img/out.gif" style="width:60px;height:20px"></c:if></div>
					
					 <b>${data.name}</b><br>
					<c:forEach var = "color" items = "${COLOR}" varStatus="st">
					<c:if test = "${data.cate eq color.cate}">
						${color.color2}
					</c:if>
					 </c:forEach><br>
					<c:if test = "${data.result eq 0}">
					<h4>${data.price}원</h4>
					</c:if>
					<c:if test = "${data.result ne 0}">
					<h6 style="text-decoration: line-through;">${data.price}원</h6>
					<h4><font color ="red">${data.result}원</font></h4>
					</c:if>
					</div>
				</div>
						
				</c:forEach>
		
			</div>
		</div>
	</div>
</div>



<%@ include file="../inc/Footer.jsp"%>