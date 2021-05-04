package rest;

import data.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
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

}
