<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    


<c:import url="../adminHeader.html" charEncoding="UTF-8"/>

<link rel="stylesheet" href="center.css">

<h2>Muokkaa ehdokkaan tietoja</h2>
<br>
<form action='/rest/candidaterest/editcandidate' method='post'>
<b> Ehdokasnumero </b> <br>
<input type="number" name='ehdokasId' value='${requestScope.candidate.ehdokasId}' readonly class='input'> <br>
<b> Sukunimi </b> <br>
<input type="text" name='sukunimi' value='${requestScope.candidate.sukunimi}' class='input'> <br>
<b> Etunimi </b> <br>
<input type="text" name='etunimi' value='${requestScope.candidate.etunimi}' class='input'> <br>
<b>Puolue </b> <br>
<input type="text" name='puolue' value='${requestScope.candidate.puolue}' class='input'> <br>
<b>Kotipaikkakunta </b> <br>
<input type="text" name='kotipaikkakunta' value='${requestScope.candidate.kotipaikkakunta}' class='input'> <br>
<b>Ikä</b> <br>
<input type="number" name='ika' value='${requestScope.candidate.ika}' class='input'> <br>
<b> Ammatti </b> <br>
<input type="text" name='ammatti' value='${requestScope.candidate.ammatti}' class='input'> <br> 
<b>Miksi sinut tulisi valita eduskuntaan? </b><br>
<textarea rows="5" cols="80" name='miksiEduskuntaan' class='input' style="width:24rem;">${requestScope.candidate.miksiEduskuntaan}</textarea><br>
<b>Mitä asioita haluaisit edistää?</b> <br>
<textarea rows="5" cols="80" name='mitaAsioitaHaluatEdistaa' class='input' style="width:24rem;">${requestScope.candidate.mitaAsioitaHaluatEdistaa}</textarea><br>
<input type='Submit' name='ok' value='Tallenna' class="btn"> 
</form>

<%@ include file="../footer.html" %>
