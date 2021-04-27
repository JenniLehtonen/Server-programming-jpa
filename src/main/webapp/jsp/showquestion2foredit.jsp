<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Question" %>   
    <%@ page import="data.Question"%>
<%@ page import="dao.Dao"%>
<%@ page import="app.Update"%>
<%@ page import="app.ReadQuestionToUpdate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>

<h2>Muokkaa kysymyksiä</h2>

<ol>
<c:forEach var="question" items="${requestScope.questionlist}" >
<li>${question.whatquestion} <a href='/readquestiontoupdate?id=${question.id}'>Muokkaa</a>

</c:forEach>
</ol>


<c:import url="../footer.html" charEncoding="UTF-8"/>






</body>
</html>