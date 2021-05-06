<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.*"%>
<%@ page import="dao.Dao"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="../header.html" charEncoding="UTF-8"/>


<h2> Sinulle sopivimmat ehdokkaat:</h2> <br><br>

<!-- Loop through the best candidates and show them -->
<c:forEach var="pointsAndCandidates"
	items="${requestScope.pointsAndCandidates}">

<h3>${pointsAndCandidates.candidate_id}  ${pointsAndCandidates.candidateFirstName} ${pointsAndCandidates.candidateLastName} </h3> <a href="/rest/answersrest/getanswersbycandidate/${pointsAndCandidates.candidate_id}">Lisätietoja</a> <br>
	<br>

</c:forEach>

<!-- Turn the user's answers into a string of numbers -->
<c:forEach var="useranswers" items="${requestScope.useranswers}">
	<c:set var="useranswers_string" value="${useranswers_string}${useranswers}" />

</c:forEach>

<a href="rest/answersrest/compareUserAnswersToCandidateAnswers/${useranswers_string}" class="btn">Vertaa vastauksiasi</a> <br>


<%@ include file="../footer.html"%>
