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

@Path("/candidaterest")
public class CandidateRest {
	
	/**
	 * Request and response, can be used in every method
	 */
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	/**
	 * @Sanna Nieminen-Vuorio
	 * Method gets all the candidates from database, using Dao-class method
	 * @return candidateList, list of all candidates
	 */
	@GET
	@Path("/getallcandidates")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ehdokkaat> getAllCandidates() //throws ServletException, IOException
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");
		EntityManager em=emf.createEntityManager();
		
		List<Ehdokkaat> list=em.createQuery("select a from Ehdokkaat a").getResultList();
		
		//List<Ehdokkaat> candidateList = new ArrayList<Ehdokkaat>();
		//Dao dao = new Dao();

		//candidateList = dao.getAllCandidates();
		
		/*
		request.setAttribute("candidateList", candidateList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showcandidates.jsp");
		rd.forward(request, response); */
		
		return list;
		
	} //getAllCandidates-sulje


} // class sulje
