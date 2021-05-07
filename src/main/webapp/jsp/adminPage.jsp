<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>
<link rel="stylesheet" href="../center.css">
<div class="grid-container">
	<a class="adminButtons" class="grid-item" href="/rest/candidaterest/showshort">Muokkaa ehdokkaita</a> 
	<a class="adminButtons" class="grid-item" href="/jsp/addCandidate.jsp">Lisää uusi ehdokas</a>
	<a class="adminButtons" class="grid-item" href="/rest/questionrest/getquestionsforcandidate">Ehdokas lisää tai muokkaa vastauksia</a>
	<a class="adminButtons" class="grid-item" href="/rest/questionrest/addQuestionPage">Lisää kysymys</a>
	<a class="adminButtons" class="grid-item" href='/rest/questionrest/getallquestionstodelete'>Poista kysymyksiä</a> 
	<a class="adminButtons" class="grid-item" href="/rest/questionrest/editquestion">Muokkaa kysymyksiä</a>
</div>
<%@ include file="../footer.html" %>