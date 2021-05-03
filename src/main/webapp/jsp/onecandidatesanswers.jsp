<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../adminHeader.html" charEncoding="UTF-8" />

Candidate: ${requestScope.ehdokas.etunimi}
${requestScope.ehdokas.sukunimi}
<br>
<br>

Questions and answers:
<br>
<c:forEach var="answer" items="${requestScope.ehdokas.vastauksets}">
${answer.kysymykset.kysymysId}: ${answer.kysymykset.kysymys} -

	<c:choose>
		<c:when test="${answer.vastaus == 1}">
			<b>Täysin eri mieltä</b>
		</c:when>
		<c:when test="${answer.vastaus == 2}">
			<b>Jokseenkin eri mieltä</b>
		</c:when>
		<c:when test="${answer.vastaus == 3}">
			<b>En osaa sanoa</b>
		</c:when>
		<c:when test="${answer.vastaus == 4}">
			<b>Jokseenkin samaa mieltä</b>
		</c:when>
		<c:when test="${answer.vastaus == 5}">
			<b>Täysin samaa mieltä</b>
		</c:when>
		<c:otherwise>
			<b>Ei vastattu</b>
		</c:otherwise>
	</c:choose>
	<br>
</c:forEach>


<%@ include file="../footer.html"%>