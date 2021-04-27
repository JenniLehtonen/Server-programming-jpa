<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<link rel="stylesheet" href="center.css">
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>

<h2>Muokkaa kysymyksiä</h2>
<form action='/saveupdatedquestion' method='post'>
<label>Kysymysid:</label><br>
<input type='text' name='id' value='${requestScope.question.id}' readonly><br> 
<label>Kysymys:</label><br>
<textarea rows="5" cols="80" name='whatquestion'style="width:24rem; margin-top: 10px;">${requestScope.question.whatquestion}</textarea><br><br>
<input type='submit' name='ok' value='Muokkaa' class="button"> 
</form>
</body>
</html>

<c:import url="../footer.html" charEncoding="UTF-8"/>
