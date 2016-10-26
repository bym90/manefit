<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>	
<script>
	(function(kind){
		
		$("#kind3").val(kind);
		
	})(1)

	function parcelCheck(parcel) {
		var parcel = parcel
		if(parcel == "----"){
			sweetAlert("", "아직 운송장에 등록되지 않았습니다.", "warning");
// 			alert("아직 운송장에 등록되지 않았습니다.");
			return;
		}
		window.open("http://nexs.cjgls.com/web/info.jsp?slipno="+parcel);
	}

	function detailView(no, id){
		var viewNo = document.getElementById(no).value;
		var viewId = document.getElementById(id).value;
		
		
		meg = window.open("../Shopping/OrderView.com?no="+viewNo+"&id="+viewId+"", "Message", "location=0, scrollbars=1, status=0, width=800, height=600, realzable=0, channelmode=1, left=20% top=20%");
	}
	
	function cancelOrder(goods,no,id){
		var orderNo = document.getElementById(no).value;
		var orderId = document.getElementById(id).value;
	
// 		if(window.confirm(goods+"의 주문을 취소 하시겠습니까?")){
// 		$("#no").val(orderNo);
// 		$("#id").val(orderId);
		
// 		$("#ofrm").attr("action","../Shopping/cancelOrder.com");
// 		$("#ofrm").submit();
// 		}
		if(sweetAlert({title: "",   
			text: goods+"의 주문을 취소 하시겠습니까?",   
			type: "warning",   
			showCancelButton: true,   
			confirmButtonColor: "#DD6B55",   
			confirmButtonText: "주문취소",
			cancelButtonText: "돌아가기",
			closeOnConfirm: false }, 
			function(){
			$("#no").val(orderNo);
			$("#id").val(orderId);
				
			$("#ofrm").attr("action","../Shopping/cancelOrder.com");
			$("#ofrm").submit();})){
		}
		else{
			return;
		}
		
	}
	
	
	
</script>

<form method="POST" action="" id="ofrm">

<div class="container">
	<table class="table">
		<tr>
			<td align="center" colspan="11">
				<a href="#" onclick="mypageList(1)"><input type="button" value="주문정보" class="btn btn-primary"></a>
				<a href="../MyPage/CartList.com"><input type="button" value="장바구니" class="btn btn-primary"></a>
				<a href="../MyPage/Favorite.com"><input type="button" value="찜목록" class="btn btn-primary"></a>
				<a href="../MyPage/MyBoard.com"><input type="button" value="내가 쓴 글" class="btn btn-primary"></a>
			</td>
		<tr>
		<tr><td></td></tr>
	</table>
</div>
<div class="container">
	<table class="table table-hover">
		<thead>
			<tr><td colspan="9"><h2><b>주문정보</b></h2></td></tr>
<!-- 			<tr> -->
<!-- 				<td colspan="11"> -->
<!-- 					<input type="text" id="word" name="word" class="SEARCHBAR1" placeholder="상품이름으로 검색하세요"> -->
<!-- 					<input type="button" id="sBtn" value="검색하기" class="btn btn-info"> -->
<!-- 				</td>	 -->
<!-- 			</tr> -->

			<tr>
				<td width="10%">사진</td>
				<td width="10%">상품</td>
				<td width="10%">총 가격</td>
				<td width="10%">받는이</td>
				<td width="15%">받는이 주소</td>
				<td width="15%">운송장번호</td>
				<td width="10%">배송상태</td>
				<td width="10%">상태변경</td>
				<td width="10%">자세히</td>
				
			</tr>
		</thead>
		<tbody>
	<c:if test = "${CHECK}">
		<c:forEach var="list" items="${LIST}" varStatus = "st">
			<tr>
				<td>
					<img src ="../resources/img/Goods/${list.savename }" width = "70px" height = "70px">
					<input type = "hidden" id = "no${st.index}" name = "no${st.index}" value = "${list.no}">
					<input type = "hidden" id = "id${st.index}" name = "id${st.index}" value = "${list.id}">
				</td>
				<td>
				<c:if test ="${list.totalcount gt 1 }">
				 	${list.name } 외 ${list.totalcount-1}개 
				</c:if>
				<c:if test ="${list.totalcount lt 2 }">
				 	${list.name }
				</c:if>
				</td>
				<td>${list.totalprice }원</td>
				<td>${list.rname }</td>
				<td>${list.raddr }</td>
				<td>${list.oparcel } <a class="btn btn-default btn-sm" href="#" id ="myPage" onclick = "parcelCheck('${list.oparcel }')"><i class="fa fa-truck fa-1x" aria-hidden="true"></i> 배송조회 </a></td>
				<td>${list.oship2 }</td>
				<c:if test = "${list.oship2 eq '배송대기' or list.oship2 eq '배송중'}">
				<td><input type="button" id="cBtn" value="주문 취소" class="btn btn-danger" onclick = "cancelOrder('${list.name } 외 ${list.totalcount-1}개 ','no${st.index}','id${st.index}')"></td>
				</c:if>
				<c:if test = "${list.oship2 eq '배송완료'}">
				<td></td>
				</c:if>
				<td align="left"><input type="button" id="dBtn" value="자세히" class="btn btn-danger" onclick = "detailView('no${st.index}','id${st.index}')"></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test = "${!CHECK}">
		<tr>
			<td colspan = "9">
			<div style="margin-left:20%;margin-bottom:5%;margin-top:5%;">
				<img src = "../resources/img/emptyOrder.png" width ="70%">
			</div>
			<td>
		</tr>
	</c:if>	
		
		</tbody>
		<tfoot>
			<tr>
			<td align="center" colspan="12">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO.startPage ne 1}">
							<a href="../Shopping/OrderList.com?nowPage=${PINFO.startPage - 1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li>
								<a href="../Shopping/OrderList.com?nowPage=${page}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../Shopping/OrderList.com?nowPage=${PINFO.endPage + 1}" aria-label="Next">
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
		</tfoot>
	</table>
</div>	
	<input type = "hidden" id = "no" name = "no">
	<input type = "hidden" id = "id" name = "id">


</form>


<%@ include file="../inc/Footer.jsp"%>