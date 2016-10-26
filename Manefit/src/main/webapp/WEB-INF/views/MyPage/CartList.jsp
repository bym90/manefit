<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>

<script>
	$(document).ready(function(){
	
		$("#allcheck").click(function(){
			if($("#allcheck").prop("checked")){
	            $("input[id=check]").prop("checked",true);
	        }else{
	            $("input[id=check]").prop("checked",false);
	        }
		});
		
		
		$("#dBtn").click(function(){
				if($("input:checkbox[id='check']").is(":checked")==false){
					sweetAlert("", "삭제 할 상품을 선택해 주세요.", "warning");
// 					alert("삭제 할 상품을 선택해 주세요.");
				}
				else{
// 					if(window.confirm("해당 상품을 장바구니에서 삭제 하시겠습니까?")){
					if(sweetAlert({title: "",   
						text: "해당 상품을 장바구니에서 삭제 하시겠습니까?",   
						type: "warning",   
						showCancelButton: true,   
						confirmButtonColor: "#DD6B55",   
						confirmButtonText: "삭제",
						cancelButtonText: "취소",
						closeOnConfirm: false }, 
						function(){
						$("#cate").val("1");
						$("#cfrm").attr("action","../MyPage/CartList.com");
						$("#cfrm").submit(); })){
					}
					else{
						return;
					}
				}
		});
		
		$("#bBtn").click(function(){
			if($("input:checkbox[id='check']").is(":checked")==false){
				sweetAlert("", "구입 할 상품을 선택해 주세요.", "warning");
//					alert("삭제 할 상품을 선택해 주세요.");
			}
			else{
// 			if(window.confirm("장바구니에 담긴 상품을 구입 하시겠습니까?")){
// 				$("#cfrm").attr("action","../Shopping/BuyForm.com");
// 				$("#cfrm").submit();
// 			}
			if(sweetAlert({title: "",   
				text: "장바구니에 담긴 상품을 구입 하시겠습니까?", 
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#00b33c",   
				confirmButtonText: "구입",
				cancelButtonText: "취소",
				closeOnConfirm: false }, 
				function(){
				$("#cfrm").attr("action","../Shopping/BuyForm.com");
	 			$("#cfrm").submit(); })){
			}
			else{
				return;
			}
			}
		});
	});
	
	
</script>

<form method="POST" action="" id="cfrm">
<div class="container">
	<table class="table">
		<tr>
			<td align="center" colspan="9">
				<a href="#" onclick="mypageList(1)"><input type="button" value="주문정보" class="btn btn-primary"></a>
				<a href="../MyPage/CartList.com"><input type="button" value="장바구니" class="btn btn-primary"></a>
				<a href="../MyPage/Favorite.com"><input type="button" value="찜목록" class="btn btn-primary"></a>
				<a href="../MyPage/MyBoard.com"><input type="button" value="내가 쓴 글" class="btn btn-primary"></a>
			</td>
		<tr>
		<tr><td colspan="9"></td></tr>
	</table>
</div>
<div class="container">
	<table class="table table-hover ">
		<thead>
			<tr><td colspan="8"><h2><b>Cart List</b></h2></td></tr>
<!-- 			<tr> -->
<!-- 				<td colspan="9"> -->
<!-- 					<input type="text" id="word" name="word" class="SEARCHBAR1" placeholder="상품이름으로 검색하세요"> -->
<!-- 					<input type="button" id="sBtn" value="검색하기" class="btn btn-info"> -->
<!-- 				</td>	 -->
<!-- 			</tr> -->
			<tr>
				<td width="5%"><input type="checkbox" id="allcheck" name = allcheck></td>
				<td width="17%">사진</td>
				<td width="18%">상품</td>
				<td width="10%">사이즈</td>
				<td width="10%">색상</td>
				<td width="10%">수량</td>
				<td width="15%">적립금(원)</td>
				<td width="15%">가격(원)</td>
			</tr>
		</thead>
		<tbody>
	<c:if test = "${CHECK}">
		<c:forEach var="list" items="${LIST}" varStatus ="st">
				<tr>
					<td><input type="checkbox" id="check" name = "check" value ="${list.ono}"></td>
					<td><img src ="../resources/img/Goods/${list.savename }" width = "80px" height = "80px" >
						<input type = "hidden" name = "image" id = "image" value = "${list.savename}" >
						<input type = "hidden"  value ="${st.index+1}" >
						<input type = "hidden" name = "no" id = "no${st.index}" value ="${list.ono}" >	
					</td>
					<td><input type = "text" name = "name2" id = "name2${st.index}" value ="${list.name}" style="border: 0" readonly class="W100"></td>
					<td><input type = "text" name = "size" id ="size2${st.index}" value ="${list.size2}" style="border: 0" readonly class="W100"></td>
					<td><input type = "text" name = "color" id = "color2${st.index}" value ="${list.color2}" style="border: 0" readonly class="W100"></td>
					<td><input type = "text" name = "quantity" id = "quantity2${st.index}" value ="${list.quantity2}" style="border: 0" readonly class="W100"></td>
					<td><input type = "text" id = "save${st.index}" value = "${Math.round(list.save2*0.01*list.price2)}" style="border: 0" readonly class="W90">
						<input type = "hidden" name = "cate2" id = "cate2${st.index}" value = "${list.cate}">
						<input type = "hidden" name = "save3" id = "save3${st.index}" value = "${list.save2}"></td>
					<td><input type = "text" name = "price" id = "price2${st.index}" value = "${list.price2}" style="border: 0" readonly class="W90"></td>
				</tr>
		</c:forEach>
	</c:if>	
	<c:if test = "${!CHECK}">
		<tr>
			<td colspan = "8">
			<div style="margin-left:20%;margin-bottom:5%;margin-top:5%;">
				<img src = "../resources/img/emptyCart.png" width ="70%">
			</div>
			<td>
		</tr>
	</c:if>
				<tr>
					<td colspan = "8" align = "right">
						<font color = "red" size = "5">${TOTALPRICE} 원</font>
						<input type = "hidden" name = "totalprice" id = "totalprice" value = "${TOTALPRICE}">
						<input type = "hidden" name = "cart" id = "cart" value = "1">
					</td>
				</tr>				
			<tr>
				<td colspan="8" align="center">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO.startPage ne 1}">
							<a href="../MyPage/CartList.com?nowPage=${PINFO.startPage - 1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li>
								<a href="../MyPage/CartList.com?nowPage=${page}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../MyPage/CartList.com?nowPage=${PINFO.endPage + 1}" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
							<c:if test="${PINFO.endPage eq PINFO.totalPage}">
							<a href="#" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
					    </li>
					</ul>
				</nav>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td align="right" colspan = "8">
					<input type="button" id="bBtn" value="구입" class="btn btn-danger">
					<input type="button" id="dBtn" value="삭제" class="btn btn-danger">
					<input type="hidden" id="cate" name = "cate">
				</td>
			</tr>
		</tfoot>
	</table>
<!-- 	  <div class="container" style = "margin:30px"> -->
<!--   	장바구니 이용안내 -->
  	
<!--   </div> -->
<!-- <div class="container" style="padding:100px;"> -->
<!-- 	<img src ="../resources/img/good.png" width = "100%" height = "100%"> -->
<!-- </div> -->

</div>	


</form>

<%@ include file="../inc/Footer.jsp"%>

