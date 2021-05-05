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
	 * This is for getting candidates' answers and showing them next to user's answers so the user can compare their answers to candidates' answers
	 */
	@GET
	@Path("/compareUserAnswersToCandidateAnswers")
	public void compareUserAnswersToCandidateAnswers() throws ServletException, IOException {
		Dao dao = new Dao();
		/**
		 * Get candidates' answers from the database
		 */
		List<Ehdokkaat> list = new ArrayList<Ehdokkaat>();
		list=dao.readAllAnswers();
		
		/**
		 * Send user's and candidates' answers to jsp
		 */
		request.setAttribute("candidatesAndAnswersList", list);
		//request.setAttribute("useranswers", useranswers);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/compareUserAnswersToCandidateAnswers.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 */
	@POST //have to be post, because the info comes from form
	@Path("/addcandidateanswers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded") //!!!!
	public void addCandidateAnswers(List<Vastaukset> list)
	{
		
		String done;
		Dao dao = new Dao();

		done = dao.addCandidateAnswers(list);

		request.setAttribute("success", done);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jps");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} //addCandidateanswers-sulje

}
