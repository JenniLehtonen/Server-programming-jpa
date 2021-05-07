<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>

<%@ page import="dao.Dao"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>


<h2>Vastaa kysymyksiin:</h2>
<br>
<br>

<form method="post" action="/updatecandidateanswer">
	
	<div class="dropdown">
	<button class="dropbtn" type="button" disabled>Valitse ehdokas:</button>
	<div class="dropdown-content">
	<select name="ehdokasId">
	<option>Valitse </option>
	<c:forEach var="candidate" items="${requestScope.candidatelist}">
	
		<option value='${candidate.ehdokasId}'>${candidate.etunimi} ${candidate.sukunimi}</option>
		</c:forEach> 
		</select>

	</div>
</div>
<br><br>
	<c:forEach var="question" items="${requestScope.questionlist}">
		<b>${question.kysymysId}: </b>${question.kysymys} <br> <br>
			<input type="radio" id="${question.kysymysId}1" name="${question.kysymysId}"
			value="1"><label for="${question.kysymysId}1"> 1. Täysin
				eri mieltä</label><br> <input type="radio" id="${question.kysymysId}2"
			name="${question.kysymysId}" value="2"><label for="${question.kysymysId}2"> 2. Jokseenkin eri mieltä</label>
			<br> <input type="radio" id="${question.kysymysId}3"
			name="${question.kysymysId}" value="3"> <label for="${question.kysymysId}3">3. En osaa sanoa</label>
			<br> <input type="radio" id="${question.kysymysId}4"
			name="${question.kysymysId}" value="4"> <label for="${question.kysymysId}4">4. Jokseenkin samaa mieltä</label>
			<br> <input type="radio" id="${question.kysymysId}5"
			name="${question.kysymysId}" value="5"><label for="${question.kysymysId}5"> 5.
				Täysin samaa mieltä</label> <br> <br>
	</c:forEach>
	<input type="submit" value="Tallenna vastaukset" class='button'>

</form>


<%@ include file="../footer.html"%>