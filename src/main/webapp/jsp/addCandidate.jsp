<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.*" %> 
 <%@ page import="dao.Dao" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<link rel="stylesheet" href="center.css"> 
    

<c:import url="../adminHeader.html" charEncoding="UTF-8"/>

<h2>Syötä uusi ehdokas</h2>

<form method="post" action="/rest/candidaterest/addcandidate" enctype="multipart/form-data">
<label for="etunimi">Etunimi</label> <br>
<input type="text" id="etunimi" name="etunimi"> <br><br>
<label for="sukunimi">Sukunimi</label> <br>
<input type="text" id="sukunimi" name="sukunimi"> <br><br>
<label for="puolue">Puolue</label> <br>
<input type="text" id="puolue" name="puolue"> <br><br>
<label for="kotipaikkakunta">kotipaikkakunta</label> <br>
<input type="text" id="kotipaikkakunta" name="kotipaikkakunta"> <br><br>
<label for="Ika">Ikä</label> <br>
<input type="text" id="ika" name="ika"> <br><br>
<label for="miksi_eduskuntaan">Miksi eduskuntaan?</label> <br>
<textarea id="miksi_eduskuntaan" name="miksi_eduskuntaan" rows="4" cols="50">
</textarea> <br><br>
<label for="mita_asioita_haluat_edistaa">Mitä asioita haluat edistää?</label> <br>
<textarea id="mita_asioita_haluat_edistaa" name="mita_asioita_haluat_edistaa" rows="4" cols="50">
</textarea> <br><br>
<label for="ammatti">Ammatti</label> <br>
<input type="text" id="ammatti" name="ammatti"> <br>
<label for="kuva">Kuva</label> <br>
<input type="file" id="kuva" name="kuva" accept=".jpg" /> <br><br>
<input type="submit" value="Tallenna" class="button">
</form>

<%@ include file="../footer.html" %>
