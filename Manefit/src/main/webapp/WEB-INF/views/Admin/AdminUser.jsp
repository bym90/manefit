<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/Header.jsp"%>
<script>
	function withdrawProc(name, id){
		var name = name;
		var id = id
		var nowPage = ${PINFO.nowPage};
// 		if (confirm("이름 : "+name+"  아이디 : "+id+" 를 탈퇴시키시겠습니까?") == true){
		if(sweetAlert({title: "",   
			text: "이름 : "+name+"  아이디 : "+id+" 를 탈퇴시키시겠습니까?",   
			type: "warning",   
			showCancelButton: true,   
			confirmButtonColor: "#DD6B55",   
			confirmButtonText: "탈퇴",
			cancelButtonText: "취소",
			closeOnConfirm: false }, 
			function(){
			location.href="../Admin/UserWithdraw.com?id="+id+"&nowPage="+nowPage; })){
		}
		else{
		    return;
		}
	}
</script>

<div class="container">
	<table class="table table-hover">
		<thead>
		<tr>
			<th colspan="8"><h2>회원 관리</h2></th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td>이름 (id)</td>
				<td>이메일</td>
				<td>생년월일</td>
				<td>전화번호</td>
				<td>주소</td>
				<td>가입날짜</td>
				<td>등급</td>
				<td>강퇴</td>
			</tr>
			<c:forEach var="list" items="${LIST }">		
			<tr>
				<td>${list.name } (${list.id })</td>
				<td>${list.email }</td>
				<td>${list.birth }</td>
				<td>${list.tel }</td>
				<td>(${list.pc }) ${list.addr1 } ${list.addr2 } </td>
				<td>${list.jdate }</td>
				<td>${list.resrank }</td>
				<td><a class="btn btn-danger btn-sm" id="withdraw" onclick="withdrawProc('${list.name }', '${list.id }')"><i class="fa fa-ban fa-1x" aria-hidden="true"> 탈퇴</i></a></td>
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
							<a href="../Admin/AdminUser.com?nowPage=${PINFO.startPage - 1}&cate=${INFO.cate}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li>
								<a href="../Admin/AdminUser.com?nowPage=${page}&cate=${INFO.cate}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../Admin/AdminUser.com?nowPage=${PINFO.endPage + 1}&cate=${INFO.cate}" aria-label="Next">
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

<%@ include file="../inc/Footer.jsp"%>
