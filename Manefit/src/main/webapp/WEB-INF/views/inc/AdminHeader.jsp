<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin-bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/side.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
<script src="../resources/js/admin.js"></script>
<style>
  html {
	background:#efefef;
  }

  body {
	margin:0;
	padding:0;
	color:#222;
	font:13px "Helvetica Neue", Helvetica, Arial, sans-serif;
  }

  ul {
	position:absolute;
	left:0;
	right:0;
	top:0;
	height:30px;
	background:#959DA5;
	border-bottom:1px solid #333;
	margin:0;
	padding:10px 16px 0;
	list-style:none;
  }

  ul li {
	float:left;
	margin:0 20px 0 0;
	position:relative;
	padding:0;
  }

  ul a {
	display:block;
	color:#fff;
	text-decoration:none;
	padding:0 15px;
	line-height:29px;
	height:29px;
	font-weight:bold;
	background:#464646;
	border:1px solid #333;
	border-bottom:none;
	-webkit-background-clip: padding-box;
	-webkit-font-smoothing:antialiased;
	-webkit-border-top-left-radius:8px;
	-webkit-border-top-right-radius:8px;
	-moz-border-radius:8px 8px 0 0;
	border-top-left-radius:8px;
	border-top-right-radius:8px;
	text-shadow:#000 0 -1px 0;
	position:relative;
  }

  ul li:before, ul li:after {
	content:'';
	width:9px;
	height:8px;
	position:absolute;
	z-index:2;
	bottom:0;
	background:#464646;
  }

  ul li:before {
	left:-8px;
  }

  ul li:after {
	right:-8px;
  }

  ul a:before, ul a:after {
	content:'';
	width:10px;
	height:8px;
	position:absolute;
	z-index:3;
	bottom:-1px;
	background:#959DA5;
	overflow:hidden;
	border-bottom:1px solid #333;
	-webkit-background-clip: padding-box;

  }

  ul a:before {
	left:-11px;
	border-bottom-right-radius:8px;
	-webkit-border-bottom-right-radius:8px;
	-moz-border-radius-bottomright:8px;
	border-right:1px solid #222;
  }

  ul a:after {
	right:-11px;
	border-bottom-left-radius:8px;
	-webkit-border-bottom-left-radius:8px;
	-moz-border-radius-bottomleft:8px;
	border-left:1px solid #222;
  }

  ul li.current a {
	background:#efefef;
	color:#222;
	height:30px;
	text-shadow:#fff 0 1px 0;
	-webkit-background-clip: padding-box;
  }

  ul li.current a:before, ul li.current a:after {
	bottom:0;
  }

  ul li.current:before, ul li.current:after {
	background:#efefef;
	bottom:0;
	-webkit-background-clip: padding-box;
  }



  /* This style is only for the article link, and not part of the demo. */
  div.article_link {
	position:absolute;
	top:40px;
	padding:30px;
  }

  div.article_link a {
	color:#693;
	font-weight:bold;
	-webkit-font-smoothing:antialiased;
  }
</style>
</head>
<body>
<ul>
  <li><a href="../Shopping/Shopping.com">Home</a></li>
  <li><a href="../Admin/AdminGoodsAdd.com">상품등록</a></li>
   <li><a href="../Admin/AdminStock.com">재고관리</a></li>
  <li><a href="../Admin/AdminBannerAdd.com">배너등록</a></li>
</ul>
