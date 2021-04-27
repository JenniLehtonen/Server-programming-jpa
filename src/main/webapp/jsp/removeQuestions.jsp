<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.*" %> 
<%@ page import="dao.Dao" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>
<link rel="stylesheet" href="../center.css">
	  <h2 style="text-align:center;">Poista vaalikoneen kysymyksiä</h2>
	  <br>
		<c:forEach var="question" items="${requestScope.questionlist}">
			<b>Kysymysid</b>
			${question.id}<br>
			<b>Kysymys: </b><br>
			${question.whatquestion}<br>
			<a id="removeButton" href="removeQuestions?id=${question.id}">Poista kysymys</a><br>
		</c:forEach>
		
<%@ include file="../footer.html" %>