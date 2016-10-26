<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ include file="../inc/Header.jsp"%>

<script>
function deleteGoods(no,name){
	
// 	var result = window.confirm("상품을 삭제 하시겠습니까?")
// 	var result = sweetAlert({title: "",   
// 		text: "상품을 삭제 하시겠습니까?",   
// 		type: "warning",   
// 		showCancelButton: true,   
// 		confirmButtonColor: "#DD6B55",   
// 		confirmButtonText: "삭제",
// 		cancelButtonText: "이동",
// 		closeOnConfirm: false }, 
// 		function(){
// 		})
	
// 	if(result == true){
// 		$("#no").val(no);
// 		$("#hiddenfrm").attr("action","../Admin/deleteGoods.com");
// 		$("#hiddenfrm").submit();
// 	}
	if(sweetAlert({title: "",   
		text: "상품을 삭제 하시겠습니까?", 
		type: "warning",   
		showCancelButton: true,   
		confirmButtonColor: "#DD6B55",   
		confirmButtonText: "삭제",
		cancelButtonText: "취소",
		closeOnConfirm: false }, 
		function(){
		$("#no").val(no);
		$("#hiddenfrm").attr("action","../Admin/deleteGoods.com");
		$("#hiddenfrm").submit(); })){
	}
	else{
		return;
	}
}

function updateGoods(no,cate,price,name,savename,save,discount){
	var nowPage = ${PINFO.nowPage};
	$("#no").val(no);
	$("#temp").val(cate);
	$("#price").val(price);
	$("#name").val(name);
	$("#savename").val(savename);
	$("#save").val(save);
	$("#discount").val(discount);
	$("#hiddenfrm").attr("action","../Admin/AdminStockModify.com?nowPage="+nowPage);
	$("#hiddenfrm").submit();
	
}
	
function lineUp(index){
	var index = index;
	location.href="../Admin/AdminStock.com?index="+index;
}	
</script>
<script>
	$(document).ready(function() {
		$("#sBtn").click(function() {
			$("#stockfrm").attr("action", "../Admin/AdminStockSearch.com");
			$("#stockfrm").submit();
		});
	});
</script>
<style>
.Stocktable {
	text-align: center;
}
</style>
</head>

<body>
<div class = "container">

<form method = "post" id = "stockfrm" action = "">
 
	<table class="table" id = "addTable" >
		<tr>
			<td colspan = "3">
				<h2>재고 관리</h2>
			</td>
		</tr>
		<tr>
<!-- 			<td> -->
<!-- 				카테고리 -->
<!-- 			</td>	 -->
<!-- 			<td width="75%"> -->
<!-- 				<select id = "lcate3" name = "lcate3"  onChange ="changeList2()" class="SELECTSTYLE1"> -->
<!-- 					<option value = "0">선택하세요</option> -->
<%-- 					<c:forEach var = "data" items = "${LLIST}"> --%>
<%-- 						<option value = "${data.cate}">${data.name}</option> --%>
						
<%-- 					</c:forEach> --%>
<!-- 				</select> -->
<!-- 				<select id = "mcate1" name = "mcate1" onChange = "changeList3()"  class="SELECTSTYLE1"> -->
<!-- 					<option value = "0">선택하세요</option> -->
<!-- 				</select> -->
<!-- 				<select id = "scate1" name = "scate1"  class="SELECTSTYLE1"> -->
<!-- 					<option value = "0">선택하세요</option> -->
<!-- 				</select> -->
<!-- 			</td> -->
			<!-- 			<tr> -->
				<td colspan="2">
					<input type="text" id="word" name="word" class="SEARCHBAR1">
					<input type="button" id="sBtn" value="검색하기" class="btn btn-info">
				</td>	
			
			<td align="right">
				정렬
				<select id = "status" name = "status" class ="SELECTSTYLE1" onchange="lineUp(this.value)">
					<option>선택</option>
					<option value = "0">전체보기</option>
					<option value = "1">상품이름순</option>
					<option value = "2">신상품</option>
					<option value = "3">품절</option>
					<option value = "4">재고준비중</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan = "3"></td>
		</tr>
	</table>
</form>

	<table class = "table">
		<tr>
			<td height = "100">No.</td>
			<td>이미지</td>
			<td>상품 이름</td>
			<td>가격</td>
			<td>적립금</td>
			<td>할인</td>
			<td>스타일</td>
			<td>상품 상태</td>
			<td>수정/삭제</td>
		</tr>
	<c:forEach var = "stock" items ="${SLIST}">
		<tr>
			<td width = "70">
				${stock.no}.
			</td>
			<td width = "120" height = "100">
				<img src = "../resources/img/Goods/${stock.savename}" width = "130" height = "100">
			</td>
			
			<td>
				${stock.name}
			</td>
			<td>
				${stock.price}원
			</td>
			<td>
				${stock.save}%
			</td>
			<td>
				${stock.discount}%
			</td>
			<td>
				${stock.tema}
			</td>
			<td>
				${stock.status}
			</td>
			<td>
				<a class="btn btn-default btn-sm"><i class="fa fa-refresh fa-1x" aria-hidden="true" onClick = "updateGoods('${stock.no}','${stock.scate1}','${stock.price}','${stock.name}','${stock.savename}','${stock.save}','${stock.discount}')">수정</i></a><br>
				<a class="btn btn-default btn-sm"><i class="fa fa-trash fa-1x" aria-hidden="true" onClick = "deleteGoods('${stock.no}','${stock.name}')">삭제</i></a>
			</td>
		</tr>
		<tr>
			<td height ="1" colspan = "9">
				
			</td>
		</tr>
	</c:forEach>
		<tr>
			<td align="center" colspan="9">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO.startPage ne 1}">
							<a href="../Admin/AdminStock.com?nowPage=${PINFO.startPage - 1}&cate=${INFO.cate}&index=${index}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li>
								<a href="../Admin/AdminStock.com?nowPage=${page}&cate=${INFO.cate}&index=${index}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../Admin/AdminStock.com?nowPage=${PINFO.endPage + 1}&cate=${INFO.cate}&index=${index}" aria-label="Next">
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
	</table>
</div>	




<form method = "post" id = "hiddenfrm">
	<input type = "hidden" name = "no" id = "no">
	<input type = "hidden" name = "temp" id = "temp">
	<input type = "hidden" name = "name" id = "name">
	<input type = "hidden" name = "price" id = "price">
	<input type = "hidden" name = "savename" id = "savename">
	<input type = "hidden" name = "save" id = "save">
	<input type = "hidden" name = "discount" id = "discount">
</form>

	<form method="post" id="hiddenfrm">
		<input type="hidden" name="no" id="no"> <input type="hidden" name="cate" id="cate">
	</form>


<%@ include file="../inc/Footer.jsp"%>

