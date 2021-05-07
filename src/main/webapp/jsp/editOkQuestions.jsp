<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:import url="../adminHeader.html" charEncoding="UTF-8"/>



<center>Tietojen muuttaminen onnistui! <br>
<a href="http://localhost:8080/jsp/adminPage.jsp" class="btn">Palaa etusivulle</a><br>
<a href="http://localhost:8080/rest/questionrest/editquestion" class="btn">Palaa muokkaamaan kysymyksiä</a><br>
<a href="http://localhost:8080/rest/questionrest/addQuestionPage" class="btn">Palaa lisäämään uusia kysymyksiä</a>
</center>
<%@ include file="../footer.html" %>