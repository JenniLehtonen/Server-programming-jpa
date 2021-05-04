package rest;

import java.io.IOException;
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
import javax.ws.rs.PUT;
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
	//testaus
	@GET
	@Path("/editquestion")
	@Produces(MediaType.APPLICATION_JSON)
	public void getAllQuestions2() //throws ServletException, IOException
	{
		List<Kysymykset> questionlist2 = new ArrayList<Kysymykset>();
		Dao dao = new Dao();

		questionlist2 = dao.getAllQuestions();
		
		
		request.setAttribute("questionlist2", questionlist2);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestion2foredit.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	} 
	
	@GET
	@Path("/getquestionbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getQuestionById(@PathParam("id") int id) //throws ServletException, IOException
	{ 
		Kysymykset kysymys = new Kysymykset();
		Dao dao = new Dao();

		kysymys = dao.getQuestionById(id);

		request.setAttribute("kysymys", kysymys);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestiontoedit.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@POST 
	@Path("/editquestion2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded") //Method can receive POSTed data from a html form
	public void editQuestion(@FormParam("kysymysId") int id, @FormParam("kysymys") String kysymys)
	{
		
		List<Kysymykset> questionlist = new ArrayList<Kysymykset>();
		Dao dao = new Dao();
		Kysymykset kysymykset = new Kysymykset(id, kysymys);

		questionlist = dao.editQuestion(kysymykset);

		request.setAttribute("questionlist", questionlist);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestiontoedit.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
