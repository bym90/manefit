<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Laside.css">

<header role="banner">
  <nav class="nav" role="navigation">
    <ul class="nav__list">
      <li>
        <input id="group-1" type="checkbox" hidden />
        <label for="group-1"><span class="fa fa-angle-right"></span>Top</label>
        <ul class="group-list">
          <li><a href="#">Outer</a></li>
		  <li><a href="#">Shirts</a></li>
		  <li><a href="#">T-Shirts</a></li>
        </ul>
      </li>

      <li>
      <input id="group-2" type="checkbox" hidden />
      <label for="group-2"><span class="fa fa-angle-right"></span>Bottom</label>
      <ul class="group-list">
        <li><a href="#">Jean</a></li>
        <li><a href="#">Slacks</a></li>
		<li><a href="#">Shorts</a></li>
	  </ul>
	  </li>
	 </ul>
  </nav>
</header>
