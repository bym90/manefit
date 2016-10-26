<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../inc/Header.jsp"%>
<script>
$(document).ready(function(){
	$("#allCheck").click(function(){
		//만약 전체 선택 체크박스가 체크된상태일경우
		if($("#allCheck").prop("checked")) {
			//해당화면에 전체 checkbox들을 체크해준다
			$("input[id=pscheck]").prop("checked",true);
		// 전체선택 체크박스가 해제된 경우
		} else {
			//해당화면에 모든 checkbox들의 체크를해제시킨다.
			$("input[id=pscheck]").prop("checked",false);
		}
	});
	
	$("#psDelete").click(function(){
		if($("input:checkbox[id='pscheck']").is(":checked")==false){
			sweetAlert("", "삭제 할 상품을 선택해 주세요", "warning");
// 			alert("삭제 할 상품을 선택해 주세요.");
		}
		else{
// 			if(window.confirm("선택한 후기를 삭제 하시겠습니까?")){
// 			$("#conf").val("1");
// 			$("#pfrm").attr("action","../Admin/AdminPSBoardDelete.com");
// 			$("#pfrm").submit();
// 			}
			if(sweetAlert({title: "",   
				text: "선택한 후기를 삭제 하시겠습니까?", 
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "삭제",
				cancelButtonText: "취소",
				closeOnConfirm: false }, 
				function(){
				$("#conf").val("1");
				$("#pfrm").attr("action","../Admin/AdminPSBoardDelete.com");
				$("#pfrm").submit(); })){
			}
			else{
				return;
			}
		}
	});
// 	function psDelete(check, id1, body1){
// 		alert("들어오냐?");
// 		var id1 = $("#"+id1).val();
// 		var body1 = $("#"+body1).val();
// 		var pscheck = $("#"+check).val();
// 		alert(id1);
// 		alert(body1);
// 		alert(pscheck);
// 	}
});	
</script>
<form method="POST" id="pfrm" action="">
<div class="container">
	<table class="table table-hover">
		<thead> 
			<tr>
				<td colspan="6"><h2><b>후기</b></h2></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="5%">No.</td>
				<td width="20%">해당상품</td>
				<td width="*">내용</td>
				<td width="10%">작성일</td>
				<td width="10%">작성자</td>
				<td width="5%"><input type="checkbox" id="allCheck"></td>
			</tr>
			<c:forEach var="ps" items="${PLIST}" varStatus="st">
			<tr>
				<td>${ps.no }</td>
				<td><a href="../Shopping/ShoppingGoodsView.com?cate=${ps.cate }">${ps.cname }</a></td>
				<td>${ps.body }</td>
				<td>${ps.pdate }</td>
				<td>${ps.name }
				</td> 
				<td><input type="checkbox" id="pscheck" name="pscheck" value="${ps.no}"></td>
			</tr>
			</c:forEach>
			<tr>	
				<td colspan="6" align="right"><a class="btn btn-default btn-sm" href="#" id="psDelete"><i class="fa fa-trash fa-1x" aria-hidden="true">삭제</i></a>
				<input type="hidden" id="conf" name="conf">
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
			<td align="center" colspan="6">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PPINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PPINFO.startPage ne 1}">
							<a href="../MyPage/AdminBoard.com?nowPage1=${PPINFO.startPage - 1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PPINFO.startPage}" end="${PPINFO.endPage}">
						<li>
								<a href="../MyPage/AdminBoard.com?nowPage1=${page}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PPINFO.endPage ne PPINFO.totalPage}">
							<a href="../MyPage/AdminBoard.com?nowPage1=${PPINFO.endPage + 1}" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
							<c:if test="${PPINFO.endPage eq PPINFO.totalPage}">
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

<script>
$(document).ready(function(){
	$("#report tr:odd").addClass("odd");
    $("#report tr:not(.odd)").hide(); 
    $("#report tr:first-child").show(); //열머리글 보여주기
    
    $("#report tr.odd").click(function(){      
        $(this).next(" tr").toggle();
        $(this).find(".arrow").toggleClass("up");
    });
});
function AnswerWrite(no , rtitle, rbody, cate){
	var no = no;
	var rbody = $("#"+rbody).val();
	var cate = $("#"+cate).val();
	var temp1 = $("#"+rtitle).val();
	var temp2 = temp1.indexOf("[답변완료]");
	if(temp2 != -1){
		sweetAlert("", "이미 답변했습니다.", "warning");
// 		alert("이미 답변했습니다.");
		return;
	}
	var rtitle = "<font color='red'>[답변완료]</font>" + temp1;
	
	if(rbody == ""){
		sweetAlert("", "내용을 입력하세요", "warning");
// 		alert("내용을 입력하세요");
		return;
	}
	
	$("#ifrm").attr("action", "../Board/IAnswerProc.com?no="+no+"&rtitle="+rtitle+"&rbody="+rbody+"&cate="+cate);
	$("#ifrm").submit();
}
</script>
<form method="POST" id="ifrm" action="">
<div class="container"><h2><b>상품문의</b></h2></div>
<div class="container">
	<table id="report" class="table table-hover">
			<tr>
				<td width="1%"></td>
				<td width="5%">No.</td>
				<td width="15%">해당상품</td>
				<td width="*">제목</td>
				<td width="15%">작성일</td>
				<td width="15%">작성자</td>
				<td width="5%"><input type="checkbox"></td>
			</tr>	
			<c:forEach var="inq" items="${ILIST }" varStatus ="st">
			<tr>
				<td><div class="arrow"></div></td>
				<td>${inq.no }</td>
				<td><a href="../Shopping/ShoppingGoodsView.com?cate=${inq.cate }">${inq.cname }</a></td>
				<td>${inq.title }</td>
				<td>${inq.idate }</td>
				<td>${inq.name }</td>
				<td><input type="checkbox"></td>
			</tr>
			<tr style="background-color: #f2f2f2;">
				<td></td>
				<td></td>
				<td colspan="5">
					<h5><font color="green"><b>사용자내용 : </b></font></h5> ${inq.body }
					<hr>
					<h5><font color="green"><b>답변내용 : </b></font></h5> ${inq.rbody }
					<hr>
					<input type="hidden" id="cate${st.index }" name="cate${st.index }" class="W45" value="${inq.cate }">
					<input type="hidden" id="rtitle${st.index }" name="rtitle${st.index }" class="W45" value="${inq.title }">
		         	
		           	<textarea id="rbody${st.index }" name="rbody${st.index }" rows="3" style="resize:none; width: 100%;"></textarea>
		           	<input type="button" id="rBtn" name="rBtn" value="답변달기" class="btn btn-success" onclick="AnswerWrite('${inq.no }', 'rtitle${st.index }', 'rbody${st.index }', 'cate${st.index }')">
				</td>	
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan="7" align="right"><a class="btn btn-default btn-sm" href="#" id="psDelete"><i class="fa fa-trash fa-1x" aria-hidden="true">삭제</i></a></td>
			</tr>
		<tfoot>
			<tr>
			<td align="center" colspan="9">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${IPINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${IPINFO.startPage ne 1}">
							<a href="../MyPage/AdminBoard.com?nowPage2=${IPINFO.startPage - 1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${IPINFO.startPage}" end="${IPINFO.endPage}">
						<li>
								<a href="../MyPage/AdminBoard.com?nowPage2=${page}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${IPINFO.endPage ne IPINFO.totalPage}">
							<a href="../MyPage/AdminBoard.com?nowPage2=${IPINFO.endPage + 1}" aria-label="Next">
						       <span aria-hidden="true">&raquo;</span>
						    </a>
						    </c:if>
							<c:if test="${IPINFO.endPage eq IPINFO.totalPage}">
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