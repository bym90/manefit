<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="../resources/js/jquery/daum-postcode.js"></script>

<script>
	$(document).ready(function(){
		oname = $("#oname").val();
		oaddr1 = $("#oaddr1").val();
		oaddr2 = $("#oaddr2").val();
		otel = $("#otel").val();
		$("#bBtn").click(function(){
			var regName = /^[가-힣]{2,4}$/;
			var regEmail = /[a-z0-9]{2,}@[a-z0-9-]{2,}.[a-z0-9]{2,}/i; 
			var regDate = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
			var regTel = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;

			var name = $("#ono").val();
			var oemail = $("#oemail").val();
			var rname = $("#rname").val();
			var raddr1 = $("#raddr1").val();
			var raddr2 = $("#raddr2").val();
			var rtel = $("#rtel").val();
			var ocount = $("#ocount").val();
			var oprice = $("#oprice").val();
			
			
			if(oaddr1.length == 0) {
				sweetAlert("", "보내는 사람 주소를 검색하세요.", "warning");
// 				alert("보내는 사람 주소를 검색하세요.");
				$("#oaddr1").val("");
				$("#oaddr1").focus();
				return;
			}
			
			if(oaddr2.length == 0) {
				sweetAlert("", "보내는 사람 상세주소를 입력하세요.", "warning");
// 				alert("보내는 사람 상세주소를 입력하세요.");
				$("#oaddr2").val("");
				$("#oaddr2").focus();
				return;
			}
			
			if(!regTel.test(otel)) {
				sweetAlert("보내는 사람 전화번호를 - 포함한 숫자만 입력하세요.", "예시>010/016/017/018/019-xxxx-xxxx", "warning");
// 				alert("보내는 사람 전화번호를 - 포함한 숫자만 입력하세요.\n예시>010/016/017/018/019-xxxx-xxxx");
				$("#otel").val("");
				$("#otel").focus();
				return;
			}
			
			if(!regEmail.test(oemail)) {
				sweetAlert("", "이메일을 @ 포함한 형식에 맞게 입력하세요.", "warning");
// 				alert("이메일을 @ 포함한 형식에 맞게 입력하세요.");
				$("#oemail").val("");
				$("#oemail").focus();
				return;
			}
			
			if(!regName.test(rname)) {
				sweetAlert("", "받는 사람 이름을 2자리 이상 5자리 이하 한글로 입력하세요.", "warning");
// 				alert("받는 사람 이름을 2자리 이상 5자리 이하 한글로 입력하세요.");
				$("#rname").val("");
				$("#rname").focus();
				return;
			}

			if(raddr1.length == 0) {
				sweetAlert("", "받는 사람 주소를 검색하세요.", "warning");
// 				alert("받는 사람 주소를 검색하세요.");
				$("#raddr1").val("");
				$("#raddr1").focus();
				return;
			}
			
			if(raddr2.length == 0) {
				sweetAlert("", "받는 사람 상세주소를 입력하세요.", "warning");
// 				alert("받는 사람 상세주소를 입력하세요.");
				$("#raddr2").val("");
				$("#raddr2").focus();
				return;
			}
		
			if(!regTel.test(rtel)) {
				sweetAlert("받는 사람 전화번호를 - 포함한 숫자만 입력하세요.", "예시>010/016/017/018/019-xxxx-xxxx", "warning");
// 				alert("받는 사람 전화번호를 - 포함한 숫자만 입력하세요.\n예시>010/016/017/018/019-xxxx-xxxx");
				$("#rtel").val("");
				$("#rtel").focus();
				return;
			}
							
			$("#bfrm").attr("action", "../Shopping/OrderList.com");
			$("#bfrm").submit();
		})
		$("#rBtn").click(function() {
			$("#bfrm").attr("action", "../Shopping/ShoppingGoodsView.com?cate=${DATA.cate}");
			$("#bfrm").submit();
		})
		$("#same").click(function(){
			if($("#same").prop("checked")){
				$("#rname").val(oname);
				$("#raddr1").val(oaddr1);
				$("#raddr2").val(oaddr2);
				$("#rtel").val(otel);
	        }else{
	        	$("#rname").val("");
				$("#raddr1").val("");
				$("#raddr2").val("");
				$("#rtel").val("");
	        }
		});
		 
		 
		 // 마일리지 까기 
		 price = $("#totalprice").val();
		 mileage = $("#smoney").val();
		$("#usemoney").on("input", function() {
			var dis = $("#usemoney").val();
			
			if(dis*1 > mileage*1){
				sweetAlert("", "적립금이 부족합니다.", "warning");
// 				alert("적립금이 부족합니다.")
				$("#usemoney").val("");
				$("#totalprice").val(price);
				$("#smoney").val(mileage);
				return;
			}
			if(dis*1 > price*1){
				sweetAlert("", "총 상품 금액 보다 큽니다.", "warning");
// 				alert("총 상품 금액 보다 큽니다.")
				$("#usemoney").val("");
				$("#totalprice").val(price);
				$("#smoney").val(mileage);
				return;
			}
			
				var mile = mileage;
				var dis2 = dis;
				var price2 = price;
				
				var total = price2 - dis2;
				var mile2 = mileage - dis2;
				
				$("#smoney").val(mile2);
				$("#totalprice").val(total);
		
		});
	});
	
	
	
	function smoney(){
		var money = $("#smoney").val();
		$("#totalprice").val(money);
	}
	

</script>



<form method="POST" action="" id="bfrm">
	<input type="hidden" id="cate" name="cate" value="${DATA.cate}">
	<input type="hidden" id="cart" name="cart" value="${DATA.cart}">
	<input type="hidden" id="savename" name="savename" value="${DATA.savename}">
<div class="Container ">
	<table class = "table table-hover ">
		<thead>
			<tr>
				<th colspan="2"><h2>주문 정보</h2></th>
			</tr>
		</thead>
			<tr>
				<td width = "17%">주문상품</td>
				<td width = "17%">색상</td>
				<td width = "17%">사이즈</td>
				<td width = "17%">수량</td>
				<td width = "17%">적립금(원)</td>
				<td width = "15%">가격(원)</td>
			</tr>
			
			<c:forEach var = "data" items = "${DATA2}" varStatus = "st">
				<tr>
					<td><input type = "text" name = "name2" id = "name2${st.index}" value ="${data.name}" style="border:0px" readonly></td>
					<td><input type = "text" name = "color" id = "color${st.index}" value ="${data.color2}" style="border:0px" readonly></td>
					<td><input type = "text" name = "size" id = "size${st.index}" value ="${data.size2}" style="border:0px" readonly></td>
					<td><input type = "text" name = "quantity" id ="quantity${st.index}" value ="${data.quantity2}" style="border:0px" readonly></td>
					<td><input type = "text" name = "save3" id = "save${st.index}" value ="${data.save2}" style="border:0px" readonly></td>
					<td><input type = "text" name = "price" id = "price${st.index}" value ="${data.price2}" style="border:0px" readonly>
						<input type = "hidden" name = "cate2" id = "cate2${st.index}" value = "${data.cate}">
						<input type = "hidden" name = "image" id = "image${st.index}" value = "${data.savename}">
					</td>
					
				</tr>	
					
			</c:forEach>
			
			<tr>
				<td colspan="5"></td>
			<c:if test="${sessionScope.GRADE eq '브론즈'}">
				<td>
					<h3><font color ="red">총  ${DATA.totalprice}원</font></h3>
				</td>
			</c:if>
			<c:if test ="${sessionScope.GRADE eq '실버'}">
				<td>
					<h4 style="text-decoration: line-through;">총  ${DATA.totalprice}원</h4>
					<h3><font color ="red">총  ${LDATA.savemoney}원</font></h3><h4>(실버회원 5%할인)</h4>
				</td>
			</c:if>
			<c:if test ="${sessionScope.GRADE eq '골드'}">
				<td>
					<h5 style="text-decoration: line-through;">총  ${DATA.totalprice}원</h5>
					<h3><font color ="red">총  ${LDATA.savemoney}원</font></h3><h4>(골드회원 7%할인)</h4>
				</td>
			</c:if>
			</tr>
	</table>
	<table class="table table-hover ">
	
		<tbody>
			<tr>
				<td>주문자</td>
				<td colspan="3"><input type="text" name="oname" id="oname" value="${sessionScope.NAME}" class="INPUTSTYLE1 W30" readonly style="border: 0px"></td>
			</tr>
			<tr>
				<td width="20%">보내는사람 주소</td>
				<td width="70%"><input type="text" name="oaddr1" id="oaddr1" value="(${LDATA.pc }) ${LDATA.addr1}" class="INPUTSTYLE1 W100"></td>
				<td width="*"></td>
			</tr>
			<tr>
				<td width="20%"></td>
				<td width="70%"><input type="text" name="oaddr2" id="oaddr2" value="${LDATA.addr2 }" class="INPUTSTYLE1 W100"></td>
				<td width="*"><input type="button" value="주소검색" class="btn btn-info" onClick="openDaumPostcode1()"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td colspan="2"><input type="text" name="otel" id="otel" value="${LDATA.tel }" class="INPUTSTYLE1 W30"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td colspan="2"><input type="text" name="oemail" id="oemail" value="${LDATA.email }" class="INPUTSTYLE1 W30"></td>
			</tr>
			<tr>
				<td>받는사람</td>
				<td><input type="text" name="rname" id="rname" class="INPUTSTYLE1 W30"></td>
				<td><input type="checkbox" id="same" name="same">상동</td>
			</tr>
			<tr>
				<td width="20%">받는사람 주소</td>
				<td width="70%"><input type="text" name="raddr1" id="raddr1" class="INPUTSTYLE1 W100"></td>
				<td width="*"></td>
			</tr>
			<tr>
				<td width="20%"></td>
				<td width="70%"><input type="text" name="raddr2" id="raddr2" class="INPUTSTYLE1 W100"></td>
				<td width="*"><input type="button" value="주소검색" class="btn btn-info" onClick="openDaumPostcode2()"></td>
			</tr>
			<tr>
				<td>받는사람 번호</td>
				<td colspan="2"><input type="text" name="rtel" id="rtel" class="INPUTSTYLE1 W30"></td>
			</tr>
			<tr>
				<td>적립금 사용</td>
				<td colspan="2"><input type="text" name="usemoney" id="usemoney"  placeholder="0"  class="INPUTSTYLE1 W30" style="text-align: right;"> 
				사용가능 적립금 : <input type="text" name="smoney" id="smoney" value="${SMONEY}" class="W10" style="border: 0px; color: red; text-align: right;width:50px;">원</td>
			</tr>
			<tr>
				<td><font color = "red">적립금 적용 가격</font></td>
						
			<c:if test="${sessionScope.GRADE eq '브론즈'}">
				<td colspan = "2" align = "right">
					<input type="text" name="totalprice" id="totalprice" value="${DATA.totalprice}" readonly style="border: 0px; color: red; text-align: right;width:80px;">원
				</td>
			</c:if>
			<c:if test="${sessionScope.GRADE ne '브론즈'}">
				<td colspan = "2" align = "right">
					<input type="text" name="totalprice" id="totalprice" value="${LDATA.savemoney}" readonly style="border: 0px; color: red; text-align: right;width:80px;">원
				</td>
			</c:if>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td align="right" colspan="3">
					<input type="button" id="bBtn" value="구입하기" class="btn btn-success">
					<input type="button" id="rBtn" value="돌아가기" class="btn btn-success">
				</td>
			</tr>
		</tfoot>
	</table>
</div>
</form>

<%@ include file="../inc/Footer.jsp"%>