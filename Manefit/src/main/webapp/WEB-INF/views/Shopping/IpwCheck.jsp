<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${CONFIRM}">
{"RESULT" :"SUCCESS" , "BODY": "${BODY.body }", "RBODY": "${BODY.rbody}"}

</c:if>
<c:if test="${not CONFIRM}">
{"RESULT" :"FAIL", "BODY": "", "RBODY": ""}
</c:if>

