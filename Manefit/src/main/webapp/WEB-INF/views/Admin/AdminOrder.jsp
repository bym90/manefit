<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>

<script>
   		// 새창 열기
   		function OpenParcel(ono){
   			var nowPage = ${PINFO.nowPage}
   			meg = window.open("../Admin/ParcelForm.com?ono="+ono+"&nowPage="+nowPage, "Parcel", "location=0, scrollbars=1, status=0, width=380, height=70, realzable=0, channelmode=1, left=950 top=350");
   			meg.focus();	
   		}
   		
   		function ShipUpdate(ono, oparcel){
   			var ono = ono;
   			var oparcel = oparcel;
   			var nowPage = ${PINFO.nowPage}
   			if(oparcel == "----"){
   				sweetAlert("", "배송대기중 입니다.", "warning");
//    			alert("배송대기중 입니다.");
   				return;
   			}
   			location.href="../Admin/ShipUpdate.com?ono="+ono+"&nowPage="+nowPage;
   		
   		}
</script>
<form method="POST" id="ofrm" action="">
<div class="container">
	<table class="table table-hover">
		<thead>
		<tr>
			<th colspan="8"><h2>주문 관리</h2></th>
		</tr>
		</thead>
		<tbody>
<!-- 			<tr> -->
<!-- 				<td>주문번호</td> -->
<!-- 				<td>보낸이(id)</td> -->
<!-- 				<td>보낸주소</td> -->
<!-- 				<td>상품</td> -->
<!-- 				<td>받는주소 (받는이)</td> -->
<!-- 				<td>주문<br>날짜</td> -->
<!-- 				<td>운송장번호</td> -->
<!-- 				<td>배송상태</td> -->
<!-- 			</tr> -->
			<tr>
				<td>주문번호</td>
				<td>보낸이(id)</td>
				<td>보낸주소</td>
				<td>상품</td>
				<td>받는주소 (받는이)</td>
				<td>주문<br>날짜</td>
				<td>운송장번호</td>
				<td>배송상태</td>
			</tr>
<%-- 			<c:forEach var="list" items="${LIST }">		 --%>
<!-- 			<tr> -->
<%-- 				<td>${list.ono }</td> --%>
<%-- 				<td>${list.oname } (${list.id })</td> --%>
<%-- 				<td>${list.oaddr }</td> --%>
<%-- 				<td>${list.name } (${list.color2 } / ${list.size2 } / ${list.quantity2 } )</td> --%>
<%-- 				<td>${list.raddr } (${list.rname })</td> --%>
<%-- 				<td>${list.odate }</td> --%>
<%-- 				<td>${list.oparcel } &nbsp;  --%>
<%-- 				<a class="btn btn-default btn-sm" id="parcelUpdate" onclick="OpenParcel('${list.ono }')"><i class="fa fa-ship fa-1x" aria-hidden="true"> 부여</i></a> --%>
<!-- 				</td> -->
<%-- 				<td>${list.oship2 } &nbsp; --%>
<%-- 				<a class="btn btn-default btn-sm" id="oshipComplete" onclick="ShipUpdate('${list.ono }', '${list.oparcel }')"><i class="fa fa-home fa-1x" aria-hidden="true"> 배송완료</i></a> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<%-- 			</c:forEach> --%>
			<c:forEach var="list" items="${LIST }">		
			<tr>
				<td>${list.ono }
				<input type = "hidden" id = "no${st.index}" name = "no${st.index}" value = "${list.no}">
				<input type = "hidden" id = "id${st.index}" name = "id${st.index}" value = "${list.id}">
				</td>
				<td>${list.oname } (${list.id })</td>
				<td>${list.oaddr }</td>
				<td>
				<c:if test ="${list.totalcount gt 1 }">
				 	${list.name } 외 ${list.totalcount-1}개 
				</c:if>
				<c:if test ="${list.totalcount lt 2 }">
				 	${list.name }
				</c:if>
				</td>
				<td>${list.raddr } (${list.rname })</td>
				<td>${list.odate }</td>
				<td>${list.oparcel } &nbsp; 
				<a class="btn btn-default btn-sm" id="parcelUpdate" onclick="OpenParcel('${list.ono }')"><i class="fa fa-ship fa-1x" aria-hidden="true"> 부여</i></a>
				</td>
				<td>${list.oship2 } &nbsp;
				<a class="btn btn-default btn-sm" id="oshipComplete" onclick="ShipUpdate('${list.ono }', '${list.oparcel }')"><i class="fa fa-home fa-1x" aria-hidden="true"> 배송완료</i></a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
			<td align="center" colspan="8">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO.startPage ne 1}">
							<a href="../Admin/AdminOrder.com?nowPage=${PINFO.startPage - 1}&cate=${INFO.cate}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li>
								<a href="../Admin/AdminOrder.com?nowPage=${page}&cate=${INFO.cate}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../Admin/AdminOrder.com?nowPage=${PINFO.endPage + 1}&cate=${INFO.cate}" aria-label="Next">
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
</form>
<%@ include file="../inc/Footer.jsp"%>