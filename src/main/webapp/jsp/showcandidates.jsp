<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    

<c:import url="../header.html" charEncoding="UTF-8"/>


  <div class="dropdown">
  	<button class="dropbtn">Valitse puolue</button>
  	<div class="dropdown-content">
<!--  
    <a href="/readparty?party='Vasemmistoliitto'">Vasemmistoliitto</a>
    <a href="/readparty?party='Suomen Sosialidemokraattinen Puolue'">Suomen Sosialidemokraattinen Puolue</a>
    <a href="/readparty?party='Vihrea liitto'">Vihreä liitto</a>
    <a href="/readparty?party='Suomen Kommunistinen Puolue'">Suomen Kommunistinen Puolue</a>
    <a href="/readparty?party='Suomen Keskusta'">Suomen Keskusta</a>
    <a href="/readparty?party='Suomen ruotsalainen kansanpuolue'">Suomen ruotsalainen kansanpuolue</a>
    <a href="/readparty?party='Suomen Kristillisdemokraatit (KD)'">Suomen Kristillisdemokraatit (KD)</a>
    <a href="/readparty?party='Kansallinen Kokoomus'">Kansallinen Kokoomus</a>
    <a href="/readparty?party='Itsenaisyyspuolue'">Itsenäisyyspuolue</a>
    -->
  </div>
</div>
<br><br>


<c:forEach var="candidate" items="${requestScope.candidateList}" >
<h2><b>${Ehdokkaat.ehdokas_id}:</b> ${Ehdokkaat.etunimi} ${Ehdokkaat.sukunimi} </h2>
<b>Puolue: </b><br>
${Ehdokkaat.puolue} <br>
<b>Kotipaikkakunta:</b><br>
${Ehdokkaat.kotipaikkakunta}<br>
<b>Ikä: </b><br>
${Ehdokkaat.ika}<br>
<b>Ammatti:</b><br>
${Ehdokkaat.ammatti}<br>
<b>Miksi haluat eduskuntaan?</b><br>
${Ehdokkaat.miksi_eduskuntaan}<br>
<b>Mitä asioita haluat edistää?</b><br>
${Ehdokkaat.mita_asioita_haluat_edistaa}<br>
<br> <br>

</c:forEach>

<%@ include file="../footer.html" %>
