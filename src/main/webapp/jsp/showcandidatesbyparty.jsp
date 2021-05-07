<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.*"%>
<%@ page import="dao.Dao"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="../header.html" charEncoding="UTF-8" />

<div class="dropdown">
	<button class="dropbtn">Valitse puolue</button>
	<div class="dropdown-content">
		<a href='/rest/candidaterest/getallcandidates'>Kaikki ehdokkaat</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Vasemmistoliitto">Vasemmistoliitto</a>
		<a
			href="/rest/candidaterest/getcandidatesbyparty/Suomen Sosialidemokraattinen Puolue">Suomen
			Sosialidemokraattinen Puolue</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Vihrea liitto">Vihreä
			liitto</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Suomen Kommunistinen Puolue">Suomen
			Kommunistinen Puolue</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Suomen Keskusta">Suomen
			Keskusta</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Suomen ruotsalainen kansanpuolue">Suomen
			ruotsalainen kansanpuolue</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Suomen Kristillisdemokraatit (KD)">Suomen
			Kristillisdemokraatit (KD)</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Kansallinen Kokoomus">Kansallinen
			Kokoomus</a> <a
			href="/rest/candidaterest/getcandidatesbyparty/Itsenaisyyspuolue">Itsenäisyyspuolue</a>

	</div>
</div>
<br>
<br>

<c:forEach var="candidate" items="${requestScope.candidateList}">
	<div class="flex-container">
		<div class="flex-child">
			<h2>
				<b>${candidate.ehdokasId}:</b> ${candidate.etunimi}
				${candidate.sukunimi}
			</h2>
			<b>Puolue: </b><br> ${candidate.puolue} <br> <b>Kotipaikkakunta:</b><br>
			${candidate.kotipaikkakunta}<br> <b>Ikä: </b><br>
			${candidate.ika}<br> <b>Ammatti:</b><br>
			${candidate.ammatti}<br> <b>Miksi haluat eduskuntaan?</b><br>
			${candidate.miksiEduskuntaan}<br> <b>Mitä asioita haluat
				edistää?</b><br> ${candidate.mitaAsioitaHaluatEdistaa}<br>
		</div>
		<div class="flex-child">
			<c:choose>
				<c:when test="${not empty Ehdokkaat.kuva}">
					<img src="http://127.0.0.1:8080/img/${Ehdokkaat.kuva}">
				</c:when>
				<c:otherwise>
					<img src="http://127.0.0.1:8080/img/default.jpg">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<br>
	<br>

</c:forEach>


<%@ include file="../footer.html"%>