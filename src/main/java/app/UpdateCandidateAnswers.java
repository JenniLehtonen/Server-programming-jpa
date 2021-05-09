package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import dao.Dao;
import data.Ehdokkaat;
import data.Kysymykset;
import data.Vastaukset;
import data.VastauksetPK;

/**
 * 
 * @author Sanna Nieminen-Vuorio
 * 
 *This is a servlet, that sends request to restful, to edit candidate's answers.
 *It gets back a string, which tells if the editing was done successfully or not.
 *Redirect to jsp, that shows the string.
 */

@WebServlet("/updatecandidateanswer")
public class UpdateCandidateAnswers extends HttpServlet{

private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		     throws IOException
		     {
				response.sendRedirect("index.html");
		     }
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {

		String ehdokasId = request.getParameter("ehdokasId");
		VastauksetPK vpk = new VastauksetPK();
		String done = "";
		
		try {
			
		
		if(ehdokasId != null) {
			
		vpk.setEhdokasId(ehdokasId);
		ArrayList<Vastaukset> candidateanswerlist = new ArrayList<>();
		String kohde = "http://127.0.0.1:8080/rest/answersrest/editcandidateanswers"; //This will be the target
		Dao dao = new Dao();

		String answer_string = null;
		int answer = 0;
		List<Kysymykset> questionslist = dao.getAllQuestions();

		for (Kysymykset k : questionslist) {
			
			Ehdokkaat ehdokas = new Ehdokkaat();
			Kysymykset kysymys = new Kysymykset();
			answer_string = request.getParameter("" + k.getKysymysId());
			if (answer_string == null) {
				answer_string = "0";
			}
			kysymys.setKysymysId(k.getKysymysId());
			answer = Integer.valueOf(answer_string);
			Vastaukset v = new Vastaukset(answer);
			v.setId(vpk);
			v.setKommentti("Ehdokkaan "+v.getId().getEhdokasId() + " vastaus");
			v.setEhdokkaat(ehdokas);
			v.setKysymykset(kysymys);
			k.setKysymysId(answer);
			candidateanswerlist.add(v); //Adds the whole object to the list
			ehdokas.setEhdokasId(ehdokasId);
			System.out.println(v.getVastaus() + " on vastaus. Ehdokas on numero " + v.getId().getEhdokasId());
		}

		Client c= ClientBuilder.newClient();
		WebTarget wt=c.target(kohde); 
		Builder b=wt.request();
		Entity<ArrayList<Vastaukset>> e = Entity.entity(candidateanswerlist, MediaType.APPLICATION_JSON); //Transform with entity to right form 

		 done = b.post(e, String.class); //This post the list to restful and gets back string. Defined as String.class
		}
		else
		{
			done = "Ehdokasta ei löytynyt, joten vastauksia ei voitu tallentaa";
		}
		}
		catch(Exception e)
		{
			System.out.println("Ehdokkaan vastausten tallennus ei onnistunut, koska virhe "+ e);
			done = "Ehdokasta ei löytynyt, joten vastauksia ei voitu tallentaa";
		}

		 //Redirect to success-page with string that tells if edit was successful or not
		request.setAttribute("success", done);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success2.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
