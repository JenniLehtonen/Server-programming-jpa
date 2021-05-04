package rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import data.*;
import dao.*;

@Path("/questionrest")
public class QuestionsRest {
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	@GET
	@Path("/getallquestions")
	@Produces(MediaType.APPLICATION_JSON)
	public void getAllQuestions() //throws ServletException, IOException
	{
		List<Kysymykset> questionlist = new ArrayList<Kysymykset>();
		Dao dao = new Dao();

		questionlist = dao.getAllQuestions();
		
		
		request.setAttribute("questionlist", questionlist);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestion.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	} 
	
	/**
	 * This method is used for directing to addNewQuestions.jsp
	 */
	@GET
	@Path("/addQuestionPage")
	public void addQuestionPage() throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/addNewQuestions.jsp");
		 dispatcher.forward(request, response); 
	}
	
	/**
	 * This method is for adding new questions to database
	 * @param kysymys, gotten from addNewQuestions.jsp
	 */
	@POST
	@Path("/addNewQuestions")
	public void addNewQuestions(@FormParam("kysymys") String kysymys) throws ServletException, IOException, SQLException{
		
		try {
		/**
		 * Send the new question to dao class's addQuestion method that will add it to the database
		 */
		Dao dao = new Dao();
		Kysymykset k = new Kysymykset();
		k.setKysymys(kysymys);
		dao.addQuestion(k);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/editOkQuestions.jsp");
		dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Something went wrong");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/adminPage.jsp");
			dispatcher.forward(request, response);
		}
	} 
	
	/**
	 * This method is for getting all the questions from the database and showing them in jsp where user can choose which of the questions they want to delete
	 */
	@GET
	@Path("/getallquestionstodelete")
	public void getAllQuestionsToDelete() throws ServletException, IOException //throws ServletException, IOException
	{
		
		try {
		List<Kysymykset> questionlist = new ArrayList<Kysymykset>();
		Dao dao = new Dao();
		/**
		 * Get all questions from the database
		 */
		questionlist = dao.getAllQuestions();
		
		/**
		 * Send all the questions to removeQuestions.jsp
		 */
		request.setAttribute("questionlist", questionlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/removeQuestions.jsp"); 
		dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Something went wrong");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/adminPage.jsp");
			dispatcher.forward(request, response);
		}
	} 
	
	/**
	 * This method is for deleting the selected question from the database
	 * @param id, gotten from removeQuestions.jsp
	 */
	@GET
	@Path("/deleteQuestions/{id}")
	public void deleteQuestions(@PathParam("id") int id) throws ServletException, IOException {
		Dao dao = new Dao();
		
		/**
		 * Send question id to dao class's removeQuestion method that will delete it from the database
		 */
		try {
		dao.removeQuestion(id);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/editOkQuestions.jsp");
		rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("Something went wrong");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/adminPage.jsp");
			dispatcher.forward(request, response);
		}
	}

}
