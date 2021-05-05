package rest;

import data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.*;

@Path("/answersrest")
public class AnswersRest {
	
	/**
	 * Request and response, can be used in every method
	 */
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	@GET
	@Path("/getanswersbycandidate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getAnswersByCandidate(@PathParam("id") int id) throws ServletException, IOException {
		Dao dao = new Dao();
		
		Ehdokkaat ehdokas = dao.getCandidateById(id);
		
		request.setAttribute("ehdokas", ehdokas);
	    RequestDispatcher rd=request.getRequestDispatcher("/jsp/onecandidatesanswers.jsp");
		rd.forward(request, response);
	}
	
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 */
	@POST //have to be post, because the info comes from form
	@Path("/addcandidateanswers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded") //!!!!
	public void addCandidateAnswers()
	{
		
		String done;
		Dao dao = new Dao();
		Ehdokkaat ehdokas = new Ehdokkaat();
		List<Vastaukset> list = new ArrayList<>();
		ehdokas.setVastauksets(list);

		done = dao.addCandidateAnswers(list, ehdokas);

		request.setAttribute("success", done);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jps");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} //addCandidateanswers-sulje
	
	
	/**
	 * Testausta varten
	 * @param list
	 * @return
	 */
	@POST
	@Path("/test")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String testMethodForList(@FormParam("${question.kysymysId}") List<String> list){

	    return "The list has " + list.size() + " entries: " 
	           + list.get(0) + ", " + list.get(1) + ", " + list.get(2) +".";
	}

}
