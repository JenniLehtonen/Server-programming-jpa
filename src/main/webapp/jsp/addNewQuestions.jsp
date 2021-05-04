<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="../header.html" charEncoding="UTF-8"/>
<link rel="stylesheet" href="/center.css">


<h2>Syötä vaalikoneeseen uusi kysymys</h2>
<form method="post" action="/rest/questionrest/addNewQuestions">
<label for="kysymys">Kysymys</label> <br>
<input type="text" id="kysymys" name="kysymys"> <br><br>
<button type="submit" value="Tallenna">Tallenna</button>
</form>

<%@ include file="../footer.html" %>
