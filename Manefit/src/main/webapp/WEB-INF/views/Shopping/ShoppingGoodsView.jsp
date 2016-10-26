<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>
<script>

(function(scroll){
	 var scroll = scroll;
	 $('html, body').animate({scrollTop : scroll}, 1); 
})('${scroll}')

</script>
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
	    
</script>

<link rel="stylesheet" href="../resources/css/GoodsView.css">

<script>
count = 0;  count2 = 0;
money0 = 0;money1 = 0;money2 = 0;money3 = 0;money4 = 0;totalM =0;
	$(document).ready(function(){
		$("#bBtn").click(function(){
			var id = $("#id").val();
			var color = $("#color100 option:selected").val();
			var size = $("#size100 option:selected").val();
			
			var quantity = $("#quantity").val();
			var kkk = 1;
			$("#kind").val(kkk);
			if(id == ""){
				sweetAlert("", "로그인 해주세요", "warning");
// 				alert("로그인 해주세요");
			} else{
				if(color == 0) {
					sweetAlert("", "색상을 선택해주세요", "warning");
// 					alert("색상을 선택해주세요");
					$("#color100").focus();
					return;
				}
				if(size == 0) {
					sweetAlert("", "사이즈를 선택해주세요", "warning");
// 					alert("사이즈를 선택해주세요");
					$("#size100").focus();
					return;
				}
				if(quantity == 0){
					sweetAlert("", "수량을 정해주세요", "warning");
// 					alert("수량을 정해주세요");
					$("#quantity").focus();
					return;
				}
				if(count == 0){
					sweetAlert("", "상품을 선택해 주세요", "warning");
// 					alert("상품을 선택해 주세요");
					$("#size100").focus();
					return;
				}
				 
				// 품절된 상품 구입 못하게 하는 기능
				for( i = 0; i <count; i++){
					var checkSoldOut = $("#size2"+i).val();
					var index = checkSoldOut.indexOf("/");
					var goodsName = checkSoldOut.substring(index+1,checkSoldOut.length);
						if(goodsName == "품절"){
							goodsName = checkSoldOut.substring(0,index);
							sweetAlert("", goodsName+"사이즈는 품절된 상품입니다.", "warning");
// 							alert(goodsName+"사이즈는 품절된 상품입니다.");
							return;
					}
							}
				
				// 현재 잔량보다 많이 못사게
				for(i = 0; i < count; i++){
					var checkQuantity = $("#quantity"+i).val();
					var checkGoodsQ = $("#size2"+i).val();
					var index = checkGoodsQ.indexOf("/");
					var goodsQuantity = checkGoodsQ.substring(index+1,checkGoodsQ.length);
				
					if(checkQuantity*1 > goodsQuantity*1){
						var color = $("#color100 option:selected").val();
						var goodsSize = checkGoodsQ.substring(0,index);
						sweetAlert("", "현재 재고량보다 많습니다. ("+color+" "+goodsSize+"상품의 재고량 : "+goodsQuantity+")", "warning");
// 						alert("현재 재고량보다 많습니다. ("+color+" "+goodsSize+"상품의 재고량 : "+goodsQuantity+")");
						return;
					}
					
				}
				
			
			
// 				if(window.confirm("구입창으로 이동합니다")){
// 					$("#gfrm").attr("action", "../Shopping/BuyForm.com");
//  				$("#gfrm").submit();
// 				}
				if(sweetAlert({title: "",   
					text: "구입창으로 이동하시겠습니까?",   
					type: "warning",   
					showCancelButton: true,   
					confirmButtonColor: "#00b33c",   
					confirmButtonText: "이동",
					cancelButtonText: "취소",
					closeOnConfirm: false }, 
					function(){
					$("#gfrm").attr("action", "../Shopping/BuyForm.com");
	 				$("#gfrm").submit(); })){
				}
				else{
					return;
				}
 					
			
			}
		
		});	 
		$("#cBtn").click(function(){
			var id = $("#id").val();
			var color = $("#color100 option:selected").val();
			var size = $("#size100 option:selected").val();
			var quantity = $("#quantity").val();
			
			if(id == ""){
				sweetAlert("", "로그인 해주세요", "warning");
// 				alert("로그인 해주세요");
			} else{
				if(color == 0) {
					sweetAlert("", "색상을 선택해주세요", "warning");
// 					alert("색상을 선택해주세요");
					$("#color100").focus();
					return;
				}
				if(size == 0) {
					sweetAlert("", "사이즈를 선택해주세요", "warning");
// 					alert("사이즈를 선택해주세요");
					$("#size100").focus();
					return;
				}
				if(quantity == 0){
					sweetAlert("", "수량을 정해주세요", "warning");
// 					alert("수량을 정해주세요");
					$("#quantity").focus();
					return;
				}
				
				if(count == 0){
					sweetAlert("", "상품을 선택해 주세요", "warning");
// 					alert("상품을 선택해 주세요");
					$("#size100").focus();
					return;
				}
				// 품절된 상품 장바구니에 못 담게
				for( i = 0; i <count; i++){
					var checkSoldOut = $("#size2"+i).val();
					var index = checkSoldOut.indexOf("/");
					var goodsName = checkSoldOut.substring(index+1,checkSoldOut.length);
						if(goodsName == "품절"){
							goodsName = checkSoldOut.substring(0,index);
							sweetAlert("", "품절된 상품은 장바구니에 담지 못합니다."+" 사이즈 : "+goodsName, "warning");
// 							alert("품절된 상품은 장바구니에 담지 못합니다."+" 사이즈 : "+goodsName);
							return;
					}
				}
				
							
// 				if(window.confirm("해당 상품을 장바구니에 추가 하시겠습니까?")){
					
// 				$("#kind").val("1");
// 				$("#gfrm").attr("action", "../Shopping/ShoppingInsertCart.com");
// 				$("#gfrm").submit();
// 				}
				if(sweetAlert({title: "",   
					text: "해당 상품을 장바구니에 추가 하시겠습니까?",   
					type: "warning",   
					showCancelButton: true,   
					confirmButtonColor: "#00b33c",   
					confirmButtonText: "추가",
					cancelButtonText: "취소",
					closeOnConfirm: false }, 
					function(){
					$("#kind").val("1");
					$("#gfrm").attr("action", "../Shopping/ShoppingInsertCart.com");
					$("#gfrm").submit();})){
				}
				else{
					return;
				}
			}
		});
		$("#wBtn").click(function(){
			var id = $("#id").val();
			var id = $("#id").val();
			var color = $("#color100 option:selected").val();
			var size = $("#size100 option:selected").val();
			var quantity = $("#quantity").val();
			if(id == ""){
				sweetAlert("", "로그인 해주세요", "warning");
// 				alert("로그인 해주세요");
			} else{
				if(color == 0) {
					sweetAlert("", "색상을 선택해주세요", "warning");
// 					alert("색상을 선택해주세요");
					$("#color100").focus();
					return;
				}
				if(size == 0) {
					sweetAlert("", "사이즈를 선택해주세요", "warning");
// 					alert("사이즈를 선택해주세요");
					$("#size100").focus();
					return;
				}
				if(quantity == 0){
					sweetAlert("", "수량을 정해주세요", "warning");
// 					alert("수량을 정해주세요");
					$("#quantity").focus();
					return;
				}
				
				if(count == 0){
					sweetAlert("", "상품을 선택해 주세요", "warning");
// 					alert("상품을 선택해 주세요");
					$("#size100").focus();
					return;
				}
// 				if(window.confirm("해당 상품을 찜 목록에 추가 하시겠습니까?")){
// 					$("#kind").val("2");
// 					$("#gfrm").attr("action", "../Shopping/ShoppingInsertCart.com");
// 					$("#gfrm").submit();
// 				}
				if(sweetAlert({title: "",   
					text: "해당 상품을 찜 목록에 추가 하시겠습니까?",   
					type: "warning",   
					showCancelButton: true,   
					confirmButtonColor: "#00b33c",   
					confirmButtonText: "추가",
					cancelButtonText: "취소",
					closeOnConfirm: false }, 
					function(){
					$("#kind").val("2");
					$("#gfrm").attr("action", "../Shopping/ShoppingInsertCart.com");
					$("#gfrm").submit(); })){
				}
				else{
				return;
				}
			}
		});
		

	    $("#color100").change(function(){
	    	
			var	color = $("#color100 option:selected").val();
			var cate = $("#cate").val();
			$.ajax({
				url : "../Shopping/getSizeList.com",
				type : "get",
				data : "color="+color + "&cate="+cate,
				dataType : "json",
				success : function(data){
					var sss = size100;

				$("#size100 option").remove();
				    $("#size100").append("<option value='0'>선택</option>");

					var list = data.size;
				
					$.each(list,  function(index){
						var code = list[index].code;
						var quantity = list[index].quantity;
						if(quantity <= 0){
							quantity = "품절";
							var op = "<option value = '"+code+"/"+quantity+"'> "+code+" ("+quantity+")</option>";
						}
						else{
							var op = "<option value = '"+code+"/"+quantity+"'> "+code+"</option>";
						}
									
						$("#size100").append(op);
					});	
	
				},
				error : function(){
					sweetAlert("", "실패", "error");
				}
			});
		});	

	
	    oTbl;
	    totalmoney = 0;
    
	    $("#size100").change(function() {
	    	
		var size = $("#size100 option:selected").val();
		
		
		
		if(size == 0){
			return;
		}
		else if(count == 5){
			sweetAlert("", "6개 이상 품목은 고를 수 없습니다.", "error");
// 			alert("그만 사.");
			return;
		}
					
		c = $("#color100 option:selected").val();
		
		
		p = document.getElementById("price100").value;
 		var tempsize = size.indexOf("/");
		var orisize = c + "  "+size.substring(0,tempsize);
			 	
	 	 	
		for(var start = 0; start < 5; start++){
	 	
	 		var checkform = $("#c"+start).val();
	 	
			if(checkform==orisize){
				sweetAlert("", "이미 같은 상품이 존재 합니다.", "warning");
// 	 			alert("이미 같은 상품이 존재 합니다.");
	 			return;
	 		}
	 	}
	
		oTbl = document.getElementById("GoodsView");
		var oRow = oTbl.insertRow(6);
		oRow.onmouseover=function(){oTbl.clickedRowIndex=this.rowIndex}; 

//clickedRowIndex - 클릭한 Row의 위치를 확인;
 		var oCell = oRow.insertCell();
		oCell.align="right";
		oCell.width="250"
		//삽입될 Form Tag 
		var frmTag = "<input type ='text' id ='c"+count+"' class='INPUTSTYLE1 W70'style='width:110px;border:0px'>"+
		"<input type='number' id='quantity"+count+"' name='quantity'oninput='myFunction("+count+")' min='1' value ='1' class='INPUTSTYLE1 W70'style='width:40px;'>개"+
 		"<input type='hidden' id='price"+count+"' name='price' value='${INFO.price}'>"+
 		"<input type='hidden' id='size"+count+"' name='size'>"+
 		"<input type='hidden' id='size2"+count+"' name='size2'>"+
 		"<input type='hidden' id='color"+count+"' name='color'>"+
 		"<input type='hidden' id='name2"+count+"' name='name2' value=${INFO.name}>"+
 		"<input type='hidden' id='image"+count+"' name='image' value=${INFO.savename}>"+
 		"<input type='hidden' id='save3"+count+"' name='save3' value='${INFO.save}'>"+
 		"<input type='hidden' id='cate2"+count+"' name='cate2' value='${INFO.cate}'>";
 		
		oCell.innerHTML = frmTag;
		
		var oCell = oRow.insertCell();
		oCell.align="right"
		frmTag ="<a href='#'><img src ='../resources/img/deleteBtn.png'onClick='removeRow("+count+"); return false;' width ='38' height ='40'></a><input type='text' id ='t"+count+"' class='INPUTSTYLE1 W70' style='width:60px;border:0px' readonly>원"
		oCell.innerHTML = frmTag;
		
		var tempsize = size.indexOf("/");
		var orisize = size.substring(0,tempsize);
		var SQ = c +"  "+orisize;
				
		$("#c"+count).val(SQ);
		$("#size"+count).val(orisize);
		$("#size2"+count).val(size);
		$("#color"+count).val(c);
		$("#t"+count).val(p);

		myFunction(count);

		++count2;
		++count;
		});
	});
	
	
	function myFunction(temp) {
		
		var price = document.getElementById("diresult").value;
		var su 	= document.getElementById("quantity"+temp).value;
		var total = price * su;
		
		
		if(temp==0){money0=total}else if(temp==1){money1=total}else if(temp==2){money2=total}else if(temp==3){money3=total}
		else if(temp==4){money4=total}
				
		$("#t"+temp).val(total);
		
		$("#price"+temp).val(total);
		
		totalM = money0+money1+money2+money3+money4;		
		$("#totalMoney").val(totalM);
		$("#totalprice").val(totalM);
		}
	
	function removeRow(temp) {
		
		count2--;
		count--;
		if(temp==0){totalM=totalM-money0; money0 = 0}
		else if(temp==1){totalM=totalM-money1; money1=0}
		else if(temp==2){totalM=totalM-money2; money2=0}
		else if(temp==3){totalM=totalM-money3; money3=0}
		else if(temp==4){totalM=totalM-money4; money4=0}
		
		$("#totalMoney").val(totalM);
		$("#totalprice").val(totalM);
		oTbl.deleteRow(oTbl.clickedRowIndex);

		}	
	
</script>

<!-- 장바구니  -->
<c:if test="${!empty sessionScope.ID}">
<div style="position:absolute;float:left;width:100px;height:250px;top:350px;left:90%;">  
        <div id="banner">
			<a href ="../MyPage/CartList.com"><img src ="../resources/img/cart1.jpg" width = "100" height = "130"></a>
			<a href ="#" onclick="mypageList(1)"><img src ="../resources/img/mypage.png" width = "100" height = "130"></a>
		</div>  
</div>
</c:if>


<form method="POST" action="" id="gfrm">
<input type="hidden" value="${sessionScope.ID }" id="id" name="id"><br><br><br><br>
<div class ="container">
	<input type="hidden" id="price100" name="price100" value="${INFO.price}">
	<input type="hidden" id="save" name="save" value="${INFO.save}">
	<input type="hidden" id="name" name="name" value="${INFO.name}">
	<input type="hidden" id="kind" name="kind" value="1">
	<input type="hidden" id="cate" name="cate" value="${INFO.cate}">
	<input type="hidden" id="savename" name="savename" value="${INFO.savename}">

	<table class="table" id = "GoodsView">
			<tr>
				<td rowspan="15" width="60%" height="100%"><img
					src="../resources/img/Goods/${INFO.savename}" width="560"
					height="600"></td>
			</tr>
			<tr>
				<td colspan="2">
					<h2>${INFO.name}</h2>
					<h5>
						[
						<c:forEach var="color" items="${COLOR}" varStatus="st">
							<c:if test="${st.last}">
				${st.index+1}
				</c:if>
						</c:forEach>
						Color.
						<c:forEach var="size" items="${SIZE}" varStatus="st">
							<c:if test="${not st.last}">
				${size.size} /
				</c:if>
							<c:if test="${st.last}">
				${size.size} 
				</c:if>

				 </c:forEach>]</h5>
			</td>
		</tr>
			<tr>
				<td colspan="2" height="50">
					<c:if test = "${INFO.result eq 0}">
						<h3>${INFO.price}</h3>
					</c:if>
					<c:if test = "${INFO.result ne 0}">
						<h4 style="text-decoration: line-through;">${INFO.price}</h4>
						<h3>${INFO.result}
						<input type = "hidden" id = "diresult" value = "${INFO.result}">
						</h3>
					</c:if>
					<b>적립금 : ${INFO.save}%</b>
				</td>
			</tr>
			<tr>
				<td align="right" width="250">색상</td>
				<td align="right"><select id="color100" name="color100"
					class="INPUTSTYLE1 W70">
						<option value="0">옵션선택</option>
						<c:forEach var="list" items="${COLOR}">
							<option value="${list.color}">${list.color}</option>
						</c:forEach>

				</select></td>
			</tr>
		
		
		
			<tr>
				<td align="right">사이즈</td>
				<td align="right"><select id="size100" name="size100"
					class="INPUTSTYLE1 W70" onChange="insRow2()">

						<option value="0">옵션선택</option>
				</select></td>
			</tr>
			<tr id="temp">
		</tr>
			<tr>
				<td colspan="2" align="right">
					<h4>
						<input type="text" id="totalMoney"
							style="width: 80px; border: 0px; text-align: right" value="0" readonly>
						원
					</h4>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><br> 
					<input class="button button5" type="button" name="bBtn" id="bBtn" value="Buy">
					 <input class="button button4" type="button" name="cBtn" id="cBtn" value="Cart"> 
					 <input	class="button button4" type="button" name="wBtn" id="wBtn" value="Wish"> <br> <br></td>
			</tr>
		</table>
	</div><br><br><br><br>
	
	<div class = "container">
	<table class="table">
		<tr>
			<td><h1>상품 상세보기</h1></td>
		</tr>
		<tr>
		
			<td align="center">
		
				<c:forEach var = "image" items ="${IMAGE}">
				
				<img src = "../resources/img/Goods/${image.savename}"><br>
				
				</c:forEach>
				<input type = "hidden" id ="totalprice" name = "totalprice">
			</td>
		</tr>
	</table>
	</div>
</form>
<!-- 상품후기 글쓰기 -->
<script src="../resources/js/jquery/PSBoard.js"></script>
<!-- <script src="../resources/js/jquery/jquery.raty.js"></script> -->

<form method="POST" id="efrm" action="">
<input type="hidden" id="cate" name="cate" value="${INFO.cate}">
<div class="container" id="psArea">
	<table class="table">
		<thead>
		<tr>
			<td colspan="3"><h1>상품 후기</h1></td>
			<td colspan="2" align="right">
			<h4><font color="red">총 평점: ${AVG}점</font></h4>
			<c:if test="${AVG eq 0}"><img src="../resources/img/star/0.png"></c:if>
			<c:if test="${AVG eq 1}"><img src="../resources/img/star/1.png"></c:if>
			<c:if test="${AVG eq 2}"><img src="../resources/img/star/2.png"></c:if>
			<c:if test="${AVG eq 3}"><img src="../resources/img/star/3.png"></c:if>
			<c:if test="${AVG eq 4}"><img src="../resources/img/star/4.png"></c:if>
			<c:if test="${AVG eq 5}"><img src="../resources/img/star/5.png"></c:if>
			<c:if test="${AVG eq 6}"><img src="../resources/img/star/6.png"></c:if>
			<c:if test="${AVG eq 7}"><img src="../resources/img/star/7.png"></c:if>
			<c:if test="${AVG eq 8}"><img src="../resources/img/star/8.png"></c:if>
			<c:if test="${AVG eq 9}"><img src="../resources/img/star/9.png"></c:if>
			<c:if test="${AVG eq 10}"><img src="../resources/img/star/10.png"></c:if>
			</td>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="5">
				<b>작성자 :</b> <input type="text" id="id" name="id" value="${NAME}" readonly style="border: 0px;">
<!-- 				<div id="star" style="cursor: pointer; width: 200px;"> -->
<!-- 				</div> -->
<!-- 				<div style="padding-top:20px;"> -->
<!-- 				<label for="displayStarRating">Value : </label><span id="displayStarRating" style="padding-left:20px;">3</span> -->
<!-- 				</div> -->
				<span class="star-input">
				  <span class="input">
				    <input type="radio" name="star-input" id="p1" value="1"><label for="p1">1</label>
				    <input type="radio" name="star-input" id="p2" value="2"><label for="p2">2</label>
				    <input type="radio" name="star-input" id="p3" value="3"><label for="p3">3</label>
				    <input type="radio" name="star-input" id="p4" value="4"><label for="p4">4</label>
				    <input type="radio" name="star-input" id="p5" value="5"><label for="p5">5</label>
				    <input type="radio" name="star-input" id="p6" value="6"><label for="p6">6</label>
				    <input type="radio" name="star-input" id="p7" value="7"><label for="p7">7</label>
				    <input type="radio" name="star-input" id="p8" value="8"><label for="p8">8</label>
				    <input type="radio" name="star-input" id="p9" value="9"><label for="p9">9</label>
				    <input type="radio" name="star-input" id="p10" value="10"><label for="p10">10</label>
				  </span>
				  <output for="star-input" id="score" name="score"><b>0</b></output><b>점</b>
				</span>
				</td>
			</tr>
			<tr>
				<td colspan="5"><textarea id="ps" name="ps" rows="3" placeholder="구매후기를 작성해주세요"  style="resize:none; width: 100%;"></textarea></td>
			</tr>
			<tr>
				<td align="right" colspan="5"><input type="button" id="sBtn1" name="sBtn1" value="글쓰기" class="btn btn-info"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th width="5%">No.</th>
				<th width="*">내용</th>
				<th width="10%">작성일</th>
				<th width="10%">평점</th>
				<th width="10%">작성자</th>
			</tr>
			<c:forEach var="list1" items="${LIST1}">
			<tr>
				<td width="5%">${list1.rownum}</td>
				<td width="*">${list1.body }</td>
				<td width="10%">${list1.pdate }</td>
				<td width="10%">${list1.score}점</td>
				<td width="10%">${list1.name }</td>
			</tr>
			</c:forEach>
			<tr>
			<td align="center" colspan="5">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO1.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO1.startPage ne 1}">
							<a href="../Shopping/ShoppingGoodsView.com?nowPage1=${PINFO1.startPage - 1}&cate=${INFO.cate}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO1.startPage}" end="${PINFO1.endPage}">
						<li>
								<a href="../Shopping/ShoppingGoodsView.com?nowPage1=${page}&cate=${INFO.cate}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO1.endPage ne PINFO1.totalPage}">
							<a href="../Shopping/ShoppingGoodsView.com?nowPage1=${PINFO1.endPage + 1}&cate=${INFO.cate}" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
							<c:if test="${PINFO1.endPage eq PINFO1.totalPage}">
							<a href="#" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
					    </li>
					</ul>
				</nav>
			</td>
			</tr>
		</tfoot>
	</table>
</div>
</form>
<script type="text/javascript">
// 		var avg = ${AVG}
// 		$(function() {
// 			$('div#star').raty({
// 				score: avg
// 				,path : "../resources/img"
// 				,width : 200
// 				,click: function(score, evt) {
// 					$("#starRating").val(score);
// 					$("#displayStarRating").html(score);
// 				}
// 			});
// 		});
</script>





<link rel="stylesheet" href="../resources/css/InqBoard.css">
<!-- 상품문의 게시판 글쓰기 -->
<script src="../resources/js/jquery/InqBoard.js"></script>

<!-- 상품문의 답변달기 -->
<script>
	function AnswerWrite(no , rtitle , rbody){
		var no = no;
		var rbody = $("#"+rbody).val();
		var temp1 = $("#"+rtitle).val();
		var temp2 = temp1.indexOf("[답변완료]");
		if(temp2 != -1){
			sweetAlert("", "이미 답변했습니다.", "warning");
//	 		alert("이미 답변했습니다.");
			return;
		}
		var rtitle = "<font color='red'>[답변완료]</font>" + temp1;
		
		if(rbody == ""){
			sweetAlert("", "내용을 입력하세요", "warning");
// 			alert("내용을 입력하세요");
			return;
		}
		
		$("#ifrm").attr("action", "../Board/IAnswerProc.com?no="+no+"&rtitle="+rtitle+"&rbody="+rbody);
		$("#ifrm").submit();
	}
</script>



<form method="POST" id="ifrm" action="">
<input type="hidden" id="cate" name="cate" value="${INFO.cate}">
<%-- <input type="hidden" id="nowPage1" name="nowPage1" value="${page}"> --%>
<div class="container" id="inqArea">
	<table class="table">
		<thead>
		<tr>
			<th><h1>상품/배송 문의</h1></th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<b>작성자 :</b> <input type="text" id="name" name="name" value="${NAME}" readonly style="border: 0px;" class="W65">
					<b>비밀번호 : </b> <input type="password" id="pw" name="pw" placeholder="비밀번호">
				</td>
			</tr>
			<tr>
				<td><b>제목 : </b><input type="text" id="title" name="title" class="W45"></td>
			</tr>
			<tr>
				<td><textarea id="inq" name="inq" rows="3" placeholder="상품/배송문의를 작성해주세요"  style="resize:none; width: 100%;"></textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td align="right"><input type="button" id="sBtn2" name="sBtn2" value="글쓰기" class="btn btn-info"></td>
			</tr>
		</tfoot>
	</table>
	<table id="report" class="table">
        <tr>
            <th width="1%"></th>
            <th width="10%" align="center">No.</th>
            <th width="*" align="center">제목</th>
            <th width="12%" align="center">작성일</th>
            <th width="10%" align="center">작성자</th>
        </tr>
        <c:forEach var="list2" items="${LIST2}">
        <tr>
            <td width="1%"><span id="lock${list2.no }"><i class="fa fa-lock" aria-hidden="true"></i></span><div class="arrow"></div></td>
            <td width="10%">${list2.rownum }</td>
            <td width="*" >${list2.title }</td>
            <td width="12%">${list2.idate }</td>
            <td width="10%">${list2.name }</td>
        </tr>
        <tr style="background-color: #f2f2f2;">
        	<td width="1%"></td>
            <td width="10%"><b>내용</b></td>
            <td width="*" colspan="3" >
            <c:if test="${!empty sessionScope.ID}">
            <c:if test="${sessionScope.GRADE ne '관리자'}">
            <div id="result${list2.no}">
			<input type="password" id="pwcheck+${list2.no }" name="pwcheck+${list2.no }" placeholder="비밀번호">
            <input type="button" id="chBtn" name="chBtn" onclick="pwCheck('${list2.no }', 'pwcheck+${list2.no }', 'result${list2.no }', '${INFO.cate}', 'pwconf${list2.no}')" value="확인" class="btn btn-danger">
            <span id="pwconf${list2.no}"></span>
            </div>
            </c:if>
            </c:if>
            <c:if test="${empty sessionScope.ID}">
            <div id="result${list2.no}">
			<input type="password" id="pwcheck+${list2.no }" name="pwcheck+${list2.no }" placeholder="비밀번호">
            <input type="button" id="chBtn" name="chBtn" onclick="pwCheck('${list2.no }', 'pwcheck+${list2.no }', 'result${list2.no }', '${INFO.cate}', 'pwconf${list2.no}')" value="확인" class="btn btn-danger">
            <span id="pwconf${list2.no}"></span>
            </div>
            </c:if>
            <c:if test="${!empty sessionScope.ID}">
            <c:if test="${sessionScope.GRADE eq '관리자'}">
            ${list2.body }
           	<input type="hidden" id="rtitle${list2.no }" name="rtitle${list2.no }" class="W45" value="${list2.title }">
         	<hr color="red">
           	<b>답변내용 :</b> <textarea id="rbody${list2.no }" name="rbody${list2.no }" rows="3" style="resize:none; width: 100%;"></textarea>
           	<input type="button" id="rBtn" name="rBtn" value="답변달기" class="btn btn-success" onclick="AnswerWrite('${list2.no }', 'rtitle${list2.no}', 'rbody${list2.no }')">
           	</c:if>
           	</c:if>
            </td>
        </tr>
         </c:forEach>
     
        <tr>
			<td align="center" colspan="5">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO2.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO2.startPage ne 1}">
							<a href="../Shopping/ShoppingGoodsView.com?nowPage2=${PINFO2.startPage - 1}&cate=${INFO.cate}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO2.startPage}" end="${PINFO2.endPage}">
						<li>
								<a href="../Shopping/ShoppingGoodsView.com?nowPage2=${page}&cate=${INFO.cate}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO2.endPage ne PINFO2.totalPage}">
							<a href="../Shopping/ShoppingGoodsView.com?nowPage2=${PINFO2.endPage + 1}&cate=${INFO.cate}" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
							<c:if test="${PINFO2.endPage eq PINFO2.totalPage}">
							<a href="#" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
					    </li>
					</ul>
				</nav>
			</td>
			</tr>
    </table>
</div>
</form>



<script src="../resources/js/jquery/star-rating.js"></script>
<link rel="stylesheet" href="../resources/css/star-rating.css">

<%@ include file="../inc/Footer.jsp"%>