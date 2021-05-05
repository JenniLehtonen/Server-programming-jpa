<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../header.html" charEncoding="UTF-8" />

<h2>${requestScope.ehdokas.etunimi}
${requestScope.ehdokas.sukunimi}</h2><br>
<div class="flex-container">
<div class="flex-child">
<b>Puolue:</b> ${requestScope.ehdokas.puolue} <br>
<b>Kotipaikkakunta:</b> ${requestScope.ehdokas.kotipaikkakunta} <br>
<b>Ikä:</b> ${requestScope.ehdokas.ika} <br>
<b>Ammatti:</b> ${requestScope.ehdokas.ammatti} <br>
<b>Miksi haluat eduskuntaan?</b><br>
${requestScope.ehdokas.miksiEduskuntaan} <br>
<b>Mitä asioita haluat edistää?</b> <br>
${requestScope.ehdokas.mitaAsioitaHaluatEdistaa}

</div>

<div class="flex-child">

<c:choose>
	<c:when test="${requestScope.ehdokas.kuva}.!equals(null)">
	<img src="file:///C:/Vaalikoneimages/${requestScope.ehdokas.kuva}">
	</c:when>
	<c:otherwise>
	<img src="file:///C:/Vaalikoneimages/default.jpg">
	</c:otherwise>
</c:choose>
</div></div>
<br>
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