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
		vpk.setEhdokasId(ehdokasId);
		ArrayList<Vastaukset> candidateanswerlist = new ArrayList<>();
		String kohde = null;
		String done = "Ei onnistunut";
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
			candidateanswerlist.add(v);
			ehdokas.setEhdokasId(ehdokasId);
			System.out.println(v.getVastaus() + " on vastaus. Ehdokas on numero " + v.getId().getEhdokasId());
		}

//		Client c= ClientBuilder.newClient();
//		WebTarget wt=c.target(kohde); 
//		Builder b=wt.request();
//		Entity<ArrayList<Vastaukset>> e = Entity.entity(candidateanswerlist, MediaType.APPLICATION_JSON); //Muutetaan Entityllä oikeaan muotoon, 
//
//		 done = b.post(e, String.class); 
		
		done = dao.editCandidateAnswers(candidateanswerlist);

		 //Ohjataan kertomaan menikö ok 
		request.setAttribute("success", done);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
