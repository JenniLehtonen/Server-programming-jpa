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

/**
 * 
 * @author Riikka Siukola, Sanna Nieminen-Vuorio, Jenni Lehtonen, Liisa Vuorenmaa
 * Restful for handling candidate's and user's answers
 *
 */
@Path("/answersrest")
public class AnswersRest {
	
	/**
	 * Request and response, can be used in every method
	 */
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	/**
	 * @author Riikka Siukola
	 * 
	 * Method gets a single candidate by id (given as parth parameter) and forwards the information to a page which
	 * displays all info of that candidate, including their answers to all the questions.
	 * 
	 * @param id
	 * @throws ServletException
	 * @throws IOException
	 */
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
	 * @author Jenni Lehtonen, Liisa Vuorenmaa, Riikka Siukola
	 * This is for getting candidates' answers and showing them next to user's answers so the user can compare their answers to candidates' answers
	 * @param useranswers_string This method gets user's answers
	 * @throws ServletException
	 * @throws IOException
	 */
	@GET
	@Path("/compareUserAnswersToCandidateAnswers/{useranswers_string}")
	public void compareUserAnswersToCandidateAnswers(@PathParam("useranswers_string") String useranswers_string) throws ServletException, IOException {
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
		request.setAttribute("useranswers_string", useranswers_string);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/compareUserAnswersToCandidateAnswers.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 * Gets request from UpdateCandidateAnswers-servlet.
	 * Gets list of candidate, including 
	 * @param list
	 * @return
	 */
	@POST //have to be post, because the info comes from form
	@Path("/editcandidateanswers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public String editCandidateAnswers(ArrayList<Vastaukset> list)
	{
		System.out.println("K??yty restill??"); //This is just for testing
		String done;
		Dao dao = new Dao();

		done = dao.editCandidateAnswers(list);
		
		return done;

	}

}
