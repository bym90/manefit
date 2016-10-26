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
<form method="POST" id="dfrm" action="">
<div class="container">
	<table class="table">
		<thead>
		<tr>
			<th colspan="3"><h3>주문 상세보기</h3></th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td>주문 번호</td>
				<td>이미지</td>
				<td>상품 이름</td>
				<td>사이즈</td>
				<td>색상</td>
				<td>수량</td>
				<td>가격</td>
				
			</tr>
			<c:forEach var="list" items="${LIST}">
			<tr>
				<td>${list.ono}</td>
				<td><img src ="../resources/img/Goods/${list.savename }" width = "70px" height = "70px"></td>
				<td>${list.name }</td>
				<td>${list.size2 }</td> 
 				<td>${list.color2 }</td>
 				<td>${list.quantity2 }</td> 
				<td>${list.price2 }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan = "7">
				
			</td>
		</tr>

	</table>
</div>
</form>
 
</body>
</html>
