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
	public void getAllCandidates() //throws ServletException, IOException
	{ /*
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");
		EntityManager em=emf.createEntityManager();
		
		List<Ehdokkaat> list=em.createQuery("select a from Ehdokkaat a").getResultList();
		*/
		List<Ehdokkaat> candidateList = new ArrayList<Ehdokkaat>();
		Dao dao = new Dao();

		candidateList = dao.getAllCandidates();
		
		
		request.setAttribute("candidateList", candidateList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showcandidates.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//return candidateList;
		
	} //getAllCandidates-sulje

	@POST
	@Path("/addcandidate")
	@Produces(MediaType.APPLICATION_JSON)
	public void addCandidate(@FormParam("etunimi") String etunimi, @FormParam("sukunimi") String sukunimi, @FormParam("puolue") String puolue, @FormParam("kotipaikkakunta") String kotipaikkakunta, @FormParam("ika") String ika, @FormParam("miksi_eduskuntaan") String miksi_eduskuntaan, @FormParam("mita_asioita_haluat_edistaa") String mita_asioita_haluat_edistaa, @FormParam("ammatti") String ammatti) {
		Ehdokkaat e = new Ehdokkaat();
		e.setEtunimi(etunimi);
		e.setSukunimi(sukunimi);
		e.setPuolue(puolue);
		e.setKotipaikkakunta(kotipaikkakunta);
		e.setIka(Integer.valueOf(ika));
		e.setMiksiEduskuntaan(miksi_eduskuntaan);
		e.setMitaAsioitaHaluatEdistaa(mita_asioita_haluat_edistaa);
		e.setAmmatti(ammatti);
		
		Dao dao = new Dao();
		
		dao.addCandidate(e);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} 
	}

} // class sulje
