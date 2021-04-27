<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>

<h2>Muokkaa tai poista ehdokkaita</h2> <br>

<c:forEach var="candidate" items="${requestScope.candidateslist}" >
<b>${candidate.ehdokas_id}:</b> ${candidate.etunimi} ${candidate.sukunimi}  <a href="/readcandidate?ehdokas_id=${candidate.ehdokas_id}">Muokkaa</a> <a href='deleteCandidate?id=${candidate.ehdokas_id}'>Poista</a> <br><br>


</c:forEach>


<%@ include file="../footer.html" %>
