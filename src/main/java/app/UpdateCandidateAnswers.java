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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import dao.Dao;
import data.Book;
import data.Question;
import data.Vastaukset;

@WebServlet("/updatecandidateanswer")
public class UpdateCandidateAnswers extends HttpServlet{

private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		     throws IOException
		     {
				response.sendRedirect("index.html");
		     }
	
	
	/**
	 * Tämä kesken, miten toimii?
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
	
		String kohde = "http://127.0.0.1:8080/rest/questionrest/test";
		
		String name=request.getParameter("name");
		ArrayList<Integer> candidateanswerlist = new ArrayList<>();
		
		String answer_string = null;
		int answer = 0;

		for (int i = 0; i < candidateanswerlist.size(); i++) {
			answer_string = request.getParameter("" + (i + 1));
			answer = Integer.valueOf(answer_string);
			candidateanswerlist.add(answer);
		}
		
		GenericType<List<Vastaukset>> genericList = new GenericType<List<Vastaukset>>() {}; //Lista pitää ottaa genericinä vastaan
		Client c= ClientBuilder.newClient();
		WebTarget wt=c.target(kohde); // haetaan metodi, joka lisää kirjan ja tulostaa kaikki kirjat
		Builder b=wt.request();
		Entity<Integer> e = Entity.entity(candidateanswerlist, MediaType.APPLICATION_JSON); //Muutetaan Entityllä kirja oikeaan muotoon, 

		List<Vastaukset> returned=b.post(e, genericList); //Kirjat listana. Tässä tehdään haku
		
		return returned;

	}

}
