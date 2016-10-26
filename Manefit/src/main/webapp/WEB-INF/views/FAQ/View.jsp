<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp" %>
<script>
	$(document).ready(function() {
		$("#lBtn").click(function() {
			$(location).attr("href", "../FAQ/List.com?nowPage=${NOWPAGE}");
		});
	});
</script>

<div class="Container">
	<form method="POST" action="" id="wfrm">
	<table class="table">
		<thead>
			<tr>
				<th colspan="2">공지사항 보기</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td width="10%">제목</td>
			<td width="*">${DATA.ftitle }</td>
			<td width="10%">날짜</td>
			<td width="10%">${DATA.fdate }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3">${DATA.fbody }</td>
		</tr>
		<tr>
			<td align="right" colspan="4">
				<input type="button" class="btn btn-info" id="lBtn" value="목록보기">
			</td>
		</tr>
		</tbody>
	</table>
	</form>
</div>



<%@ include file="../inc/Footer.jsp" %>