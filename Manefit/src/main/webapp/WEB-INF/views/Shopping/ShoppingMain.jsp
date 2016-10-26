<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
	<%@ include file="../inc/Header.jsp"%>

<script>
$(document).ready(function() {
    $("#s5").owlCarousel({
      autoPlay: 3000,
      items : 5,
      itemsDesktop : [1199,3],
      itemsDesktopSmall : [979,3]
    });

  });
  
  function newAndBest(kind){
	   
	  $("#kind").val(kind);
	  $("#newAndBest").submit();
  }
</script>
 	
    <div id="title">
      <div class="container">
        <div class="row">
          <div class="span12">
            <h2>Banner</h2>
          </div>
        </div>
      </div>
    </div>
    <form id = "newAndBest" action = "../Shopping/NewAndBest.com" method ="POST">
	<input type = "hidden" id = kind name = kind>
	</form>
	   <div id="demo">
        <div class="container">
          <div class="row">
            <div class="span12">
              <div id="owl-demo1" class="owl-carousel">
				
 				<c:forEach var = "data" items ="${LIST}"> 
 				<div class="item"><img src="../resources/img/${data.savename}"></div> 
 				</c:forEach>
              </div>
            </div>
          </div>
        
        </div>

      </div><br>
    
<c:if test="${!empty sessionScope.ID}">	
    <div id="title">
      <div class="container">
        <div class="row">
          <div class="span12">
           		<table class = "t">
           			<tr>
           				<td>
           					<h2>취향 저격</h2>			
           				</td>
           				<td align = "right">
<!--            					<a href=""><h4>More</h4></a> -->
           				</td>
           				<hr>	
           			<tr>
           		</table>
             </div>
        </div>
      </div>
    </div>

      <div id="show2">
        <div class="container">
          <div class="row">
            <div class="span12">

              <div id="s2" class="owl-carousel">
               	<c:forEach var = "list" items="${STYLE}">
              	  <div class="item"><a href="../Shopping/ShoppingGoodsView.com?cate=${list.cate}"><img src="../resources/img/Goods/${list.savename}" alt="New" style="width:225px;height:320px;"></a></div>
                </c:forEach>
              </div>
             </div>
          </div>
        </div>
    </div><br><br><br>
    </c:if>
    
	<div id="title">
      <div class="container">
        <div class="row">
          <div class="span12">
           		<table class = "t">
           			<tr>
           				<td>
           					<h2>New</h2>			
           				</td>
           				<td align = "right">
           					<a href="#"  onclick="newAndBest('New')"><h4>More</h4></a>
           				</td>
           				<hr>	
           			<tr>
           		</table>
             </div>
        </div>
      </div>
    </div>

      <div id="show2">
        <div class="container">
          <div class="row">
            <div class="span12">

              <div id="s3" class="owl-carousel">
               	<c:forEach var = "list" items="${NEW}">
              	  <div class="item"><a href="../Shopping/ShoppingGoodsView.com?cate=${list.cate}"><img src="../resources/img/Goods/${list.savename}" alt="New" style="width:225px;height:320px;"></a></div>
                </c:forEach>
              </div>
             </div>
          </div>
        </div>
    </div><br><br><br>
    
    
   <div id="title">
      <div class="container">
        <div class="row">
          <div class="span12">
           		<table class = "t">
           			<tr><hr>
           				<td>
           					<h2>BEST</h2>			
           				</td>
           				<td align = "right">
           					<a href="#" onclick="newAndBest('Best')"><h4>More</h4></a>
           				</td>	
           			<tr>
           		</table>
             </div>
        </div>
      </div>
    </div>
      <div id="show3">
        <div class="container">
          <div class="row">
            <div class="span12">

              <div id="s5" class="owl-carousel">
        		<c:forEach var = "list" items="${BEST}" varStatus = "st">
        			<div class="item">
        			<a href="../Shopping/ShoppingGoodsView.com?cate=${list.cate}">
        				<img src="../resources/img/Goods/${list.savename}" alt="New" style="width:225px;height:320px;"></a>
        			<c:if test = "${st.index <= 2 }">
        				<div style="width:60px;height:20px;position:relative;top:-320px;left:0px;">
        					<img src="../resources/img/best.gif">
        				</div>
        			</c:if>
        			</div>
                 </c:forEach>
              </div>
             </div>
          </div>
        </div>
    </div>


<%@ include file="../inc/Footer.jsp"%>