<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../inc/Header.jsp"%>

<script>
	function goGoodsView(cate){
		var cate = cate;
		location.href="../Shopping/ShoppingGoodsView.com?cate="+cate;
	}
</script>
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

<form method="POST" id="pfrm" action="">
<div class="container">
	<table class="table table-hover">
		<thead>
		<tr>
			<th colspan="6"><h2><b>내가 쓴 후기</b></h2></th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td width="5%">No.</td>
				<td width="20%">해당상품</td>
				<td width="*">내용</td>
				<td width="10%">작성일</td>
				<td width="10%">평점</td>
			</tr>
			<c:forEach var="ps" items="${PLIST }">
			<tr>
				<td>${ps.no }</td>
				<td><a href="../Shopping/ShoppingGoodsView.com?cate=${ps.cate }">${ps.cname }</a></td>
				<td>${ps.body }</td>
				<td>${ps.pdate }</td>
				<td>${ps.score }</td>
			</tr>
			</c:forEach>
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
							<a href="../MyPage/MyBoard.com?nowPage1=${PPINFO.startPage - 1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PPINFO.startPage}" end="${PPINFO.endPage}">
						<li>
								<a href="../MyPage/MyBoard.com?nowPage1=${page}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PPINFO.endPage ne PPINFO.totalPage}">
							<a href="../MyPage/MyBoard.com?nowPage1=${PPINFO.endPage + 1}" aria-label="Next">
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

<form method="POST" id="ifrm" action="">
<div class="container">
	<table class="table table-hover">
		<thead>
		<tr>
			<th colspan="6"><h2><b>내가 쓴 상품문의</b></h2></th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td width="5%">No.</td>
				<td width="15%">해당상품</td>
				<td width="17%">제목</td>
				<td width="*">내용</td>
				<td width="25%">답변내용</td>
				<td width="10%">작성일</td>
			
			</tr>
			<c:forEach var="inq" items="${ILIST }">
			<tr>
				<td>${inq.no }</td>
				<td><a href="../Shopping/ShoppingGoodsView.com?cate=${inq.cate }">${inq.cname }</a></td>
				<td>${inq.title }</td>
				<td>${inq.body }</td>
				<td>${inq.rbody }</td>
				<td>${inq.idate }</td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
			<td align="center" colspan="6">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${IPINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${IPINFO.startPage ne 1}">
							<a href="../MyPage/MyBoard.com?nowPage2=${IPINFO.startPage - 1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${IPINFO.startPage}" end="${IPINFO.endPage}">
						<li>
								<a href="../MyPage/MyBoard.com?nowPage2=${page}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${IPINFO.endPage ne IPINFO.totalPage}">
							<a href="../MyPage/MyBoard.com?nowPage2=${IPINFO.endPage + 1}" aria-label="Next">
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