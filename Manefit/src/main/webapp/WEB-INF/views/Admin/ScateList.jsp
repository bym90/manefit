<%@ page language="java" contentType="text/plain; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

     
     	{"scate" : [
     		<c:forEach var = "data" items = "${SLIST}" varStatus ="st">
     			<c:if test ="${not st.last}" >
     				{"code" : "${data.cate}" , "title" : "${data.name}"},
     			</c:if>	
     			<c:if test = "${st.last}" >
     				{"code" : "${data.cate}" , "title" : "${data.name}"}
     			</c:if>
     		</c:forEach>
        ]}