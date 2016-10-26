<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP</title>
</head>
<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap-theme.css">
<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="../resources/css/common.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<body>
<form method="POST" id="mfrm" action="">
<div class="container">
	<table class="table">
		<thead>
		<tr>
			<th colspan="3"><h3>쪽지함</h3></th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td width="10%">No.</td>
				<td width="*">제목</td>
				<td width="20%">날짜</td>
			</tr>
			<c:forEach var="list" items="${LIST}">
			<tr>
				<td>${list.rownum }</td>
				<td>
				<!-- 새글이면서 재고증가 -->
				<c:if test = "${list.newletter eq '1'}"><c:if test = "${list.mtype eq '1'}"><a href="../MyPage/InqMeg.com?nowPage=${PINFO.nowPage}&no=${list.no}"><img src = "../resources/img/new.gif" style="width:45px;height:20px">${list.title }</a></c:if></c:if>
				<!-- 새글이면서 상품문의 -->
				<c:if test = "${list.newletter eq '1'}"><c:if test = "${list.mtype eq '0'}"><a href="../MyPage/InqMeg.com?nowPage=${PINFO.nowPage}&no=${list.no}"><img src = "../resources/img/new.gif" style="width:45px;height:20px">${list.title }</a></c:if></c:if>
				<!-- 새글이면서 등업 -->
				<c:if test = "${list.newletter eq '1'}"><c:if test = "${list.mtype eq '2'}"><a href="../MyPage/InqMeg.com?nowPage=${PINFO.nowPage}&no=${list.no}"><img src = "../resources/img/new.gif" style="width:45px;height:20px">${list.title }</a></c:if></c:if>
				<!-- 읽은글이면서 재고증가 -->
				<c:if test = "${list.newletter eq '0'}"><c:if test = "${list.mtype eq '1'}"><a href="../MyPage/InqMeg.com?nowPage=${PINFO.nowPage}&no=${list.no}">${list.title }</a></c:if></c:if>
				<!-- 읽은글이면서 상품문의 -->
				<c:if test = "${list.newletter eq '0'}"><c:if test = "${list.mtype eq '0'}"><a href="../MyPage/InqMeg.com?nowPage=${PINFO.nowPage}&no=${list.no}">${list.title }</a></c:if></c:if>
				<!-- 읽은글이면서 등업 -->
				<c:if test = "${list.newletter eq '0'}"><c:if test = "${list.mtype eq '2'}"><a href="../MyPage/InqMeg.com?nowPage=${PINFO.nowPage}&no=${list.no}">${list.title }</a></c:if></c:if>
				</td>
				<td>${list.mdate }</td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
			<td align="center" colspan="5">
				<nav>
					<ul class="pagination">
						<li>
							<c:if test="${PINFO.startPage eq 1}">
							<a href="#" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
					     	<c:if test="${PINFO.startPage ne 1}">
							<a href="../MyPage/Message.com?nowPage=${PINFO.startPage - 1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					     	</a>
					     	</c:if>
						</li>
						<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<li>
								<a href="../MyPage/Message.com?nowPage=${page}">${page}</a>
						</li>			
						</c:forEach>
						<li>
							<c:if test="${PINFO.endPage ne PINFO.totalPage}">
							<a href="../MyPage/Message.com?nowPage=${PINFO.endPage + 1}" aria-label="Next">
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
 
</body>
</html>
