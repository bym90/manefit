<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP</title>
<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap-theme.css">
<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="../resources/css/common.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://use.fontawesome.com/a849bb573c.js"></script>
</head>
<script>
function goGoods(cate){
	var cate = cate;
	opener.location.href="../Shopping/ShoppingGoodsView.com?cate="+cate;
};
</script>
<body>
<form method="POST" id="mfrm" action="">
<input type="hidden" id="cate" name="cate" value="${INQVIEW.cate }">
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
			<tr>
				<td width="10%">${INQVIEW.no }</td>
				<td width="*">${INQVIEW.title }</td>
				<td width="20%">${INQVIEW.mdate }</td>
			</tr>
			<tr>
				<td><b>내용</b></td>
				<td colspan="2">${INQVIEW.body }</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td align="right" colspan="3">
				<a class="btn btn-default btn-sm" href="../MyPage/Message.com?nowPage=${NOWPAGE}" id="goList"><i class="fa fa-list fa-1x" aria-hidden="true"> 목록보기</i></a>
				<c:if test = "${INQVIEW.mtype eq '0'}">
				<a class="btn btn-default btn-sm" onclick="goGoods('${INQVIEW.cate }')" id="goGoods"><i class="fa fa-external-link fa-1x" aria-hidden="true"> 관련상품보기</i></a>
				</c:if>
				<c:if test = "${INQVIEW.mtype eq '1'}">
				<a class="btn btn-default btn-sm" onclick="goGoods('${INQVIEW.cate }')" id="goGoods"><i class="fa fa-external-link fa-1x" aria-hidden="true"> 관련상품보기</i></a>
				</c:if>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
</form>
 
</body>
</html>
