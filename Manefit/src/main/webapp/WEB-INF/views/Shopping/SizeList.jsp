<%@ page language="java" contentType="text/plain; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
       
     
     	{"size" : [
     		<c:forEach var = "data" items = "${COLOR}" varStatus ="st">
     			<c:if test ="${not st.last}" >
     				{"code" : "${data.size}", "quantity" : "${data.quantity}"},
     			</c:if>	
     			<c:if test = "${st.last}" >
     				{"code" : "${data.size}", "quantity" : "${data.quantity}"}
     			</c:if>
     		</c:forEach>
        ]}