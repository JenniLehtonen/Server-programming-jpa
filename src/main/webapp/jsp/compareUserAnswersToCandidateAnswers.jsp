<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="../header.html" charEncoding="UTF-8" />
<link rel="stylesheet" href="center.css">

<h2>Vertaa vastauksiasi kandidaattien vastauksiin</h2>
<br>

<div style="display: flex;">
	<div style="flex: 50%;">
		<c:forEach var="answer"
			items="${requestScope.candidatesAndAnswersList}">
			<c:choose>
				<%-- If the same candidate's name has been shown already, the program won't show it another time --%>
				<c:when test="${answer.etunimi eq firstname}">
					<b> </b>
				</c:when>
				<c:when test="${answer.etunimi ne firstname}">
					<b>Ehdokas: </b>
					<b>${answer.sukunimi}</b>
					<b>${answer.etunimi}</b>
					<br>
					<br>
				</c:when>
			</c:choose>
			<c:forEach var="answers" items="${answer.vastauksets}">
				Kysymys: <span>${answers.kysymykset.kysymysId}</span>,
				Vastaus: <span><b>${answers.vastaus}</b></span>
				<br>
				<br>
			</c:forEach>

			<br>
			<br>
			<c:set var="firstname" value="${answer.etunimi}" />
			<c:set var="lastname" value="${answer.sukunimi}" />
		</c:forEach>

	</div>

	<%
	int i = 1;
	%>




	<div style="flex: 50%;">

		<%
		for (int j = 0; j < 21; j++) {
			i = 1;
		%>

		<c:set var="answers" value="${requestScope.useranswers_string}" />

		<b>Sinä:</b><br>
		<br>

		<c:forEach var="i" begin="1" end="${fn:length(answers)}" step="1">
Kysymys: ${i}, Vastaus: ${answers.charAt(i-1)}  <br>
			<br>
		</c:forEach>
		<br><br>

		<%
		}
		%>


	</div>
</div>

<%@ include file="../footer.html"%>
