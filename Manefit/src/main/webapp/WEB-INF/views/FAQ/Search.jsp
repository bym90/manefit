<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/Header.jsp" %>

<script>
	$(document).ready(function() {
		$("#wBtn").click(function() {
			$("#lfrm").attr("action", "../FAQ/WriteForm.com");
			$("#lfrm").submit();
		});
		$("#sBtn").click(function() {
			$("#lfrm").attr("action", "../FAQ/Search.com");
			$("#lfrm").submit();
		})
	});
</script>

<form method="POST" action="" id="lfrm">
<div class="container ">
	<table class="table table-hover ">
		<thead>
			<tr><td colspan="4"><h2><b>FAQ</b></h2></td></tr>
			<tr>
				<td colspan="4">
					<select id="kind" name="kind" class="SELECTSTYLE2">
						<option value="ftitle">제목</option>
						<option value="fbody">본문</option>
						<option value="fboth">제목+본문</option>
					</select>
					<input type="text" id="word" name="word" class="SEARCHBAR1">
					<input type="button" id="sBtn" value="검색하기" class="btn btn-info">
				</td>	
			</tr>
			<tr>
				<c:if test="${sessionScope.GRADE ne '관리자' }">
				<th width="10%">글번호</th>
				<th width="80%" colspan="2">제목</th>
				<th width="*">날짜</th>
				</c:if>
				<c:if test="${sessionScope.GRADE eq '관리자' }">
				<th width="10%">글번호</th>
				<th width="70%">제목</th>
				<th width="*">날짜</th>
				<th width="7%">체크</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="list" items="${LIST}">
			<tr>
				<c:if test="${sessionScope.GRADE ne '관리자' }">
				<td>${list.fno }</td>
				<td colspan="2"><a href="../FAQ/View.com?nowPage=${PINFO.nowPage}&no=${list.fno}">${list.ftitle}</a></td>
				<td>${list.fdate }</td>
				</c:if>
				<c:if test="${sessionScope.GRADE eq '관리자' }">
				<td>${list.fno }</td>
				<td><a href="../FAQ/View.com?nowPage=${PINFO.nowPage}&no=${list.fno}">${list.ftitle}</a></td>
				<td>${list.fdate }</td>
				<td><input type="checkbox" id="check"></td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
		<tfoot>
		<tr>
			<td align="center" colspan="4">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO.startPage ne 1}">
							<a href="../FAQ/Search.com?nowPage=${PINFO.startPage - 1}&kind=${kind}&word=${word}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li>
								<a href="../FAQ/Search.com?nowPage=${page}&kind=${kind}&word=${word}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../FAQ/Search.com?nowPage=${PINFO.endPage + 1}&kind=${kind}&word=${word}" aria-label="Next">
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

<div class="Container ">
	<table class="table">
		<tr>
			<td align="right">
				<c:if test="${sessionScope.GRADE ne '관리자' }">
				<input type="button" id="lBtn" value="목록보기" class="btn btn-danger">
				</c:if>
				<c:if test="${sessionScope.GRADE eq '관리자' }">
				<input type="button" id="wBtn" value="글쓰기" class="btn btn-danger">
				<input type="button" id="mBtn" value="수정" class="btn btn-danger">
				<input type="button" id="dBtn" value="삭제" class="btn btn-danger">
				</c:if>
			</td>
		</tr>
	</table>
</div>

</form>
<%@ include file="../inc/Footer.jsp" %>