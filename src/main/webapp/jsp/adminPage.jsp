<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>
<link rel="stylesheet" href="../center.css">
	
<a class="adminButtons" href="/showshort">Muokkaa ehdokkaita</a> 
<a class="adminButtons" href="/addCandidate">Lisää uusi ehdokas</a><br>
<a class="adminButtons" href="/rest/questionrest/addQuestionPage">Lisää kysymys</a>
<a class="adminButtons" href='/removeQuestions'>Poista kysymyksiä</a> 
<a class="adminButtons" href="/updateQuestion">Muokkaa kysymyksiä</a> <br>
<a class="adminButtons" href="/candidateaddanswer">Ehdokas vastaa kysymyksiin</a>
<a class="adminButtons" href="/candidateupdateanswer">Ehdokas muokkaa vastauksia</a>
 	
<%@ include file="../footer.html" %>