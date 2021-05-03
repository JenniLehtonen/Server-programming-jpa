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
	{ 
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
	
	@GET
	@Path("/getcandidatebyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getCandidateById(@PathParam("id") int id) //throws ServletException, IOException
	{ 
		Ehdokkaat candidate = new Ehdokkaat();
		Dao dao = new Dao();

		candidate = dao.getCandidateById(id);

		request.setAttribute("candidate", candidate);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/updateCandidate.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Path("/showshort")
	@Produces(MediaType.APPLICATION_JSON)
	public void showShort() {
		List<Ehdokkaat> candidateList = new ArrayList<Ehdokkaat>();
		Dao dao = new Dao();

		candidateList = dao.getAllCandidates();
		
		
		request.setAttribute("candidateList", candidateList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showshort.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
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
	
	@GET
	@Path("/deletecandidate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCandidate(@PathParam("id") int id) {
		Dao dao = new Dao();
		
		int idnum = Integer.valueOf(id);
		
		dao.deleteCandidate(idnum);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} 
	}
	
	@GET
	@Path("/getcandidatesbyparty/{party}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getCandidatesByParty(@PathParam("party") String party) //throws ServletException, IOException
	{ 
		List<Ehdokkaat> candidateList = new ArrayList<Ehdokkaat>();
		Dao dao = new Dao();

		candidateList = dao.getCandidatesByParty(party);

		request.setAttribute("candidateList", candidateList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showcandidatesbyparty.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	} //getCandidatesByParty-sulje
	
	@POST //have to be post, because the info comes from form
	@Path("/editcandidate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded") //!!!!
	public void editCandidate(@FormParam("ehdokasId") int id, @FormParam("sukunimi") String sukunimi, @FormParam("etunimi") String etunimi, @FormParam("puolue") String puolue, @FormParam("kotipaikkakunta") String kotipaikkakunta, @FormParam("ika") int ika, @FormParam("ammatti") String ammatti, @FormParam("miksiEduskuntaan") String miksiEduskuntaan, @FormParam("mitaAsioitaHaluatEdistaa") String mitaAsioitaHaluatEdistaa)
	{
		
		List<Ehdokkaat> candidateList = new ArrayList<Ehdokkaat>();
		Dao dao = new Dao();
		Ehdokkaat ehdokas = new Ehdokkaat(id, sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksiEduskuntaan, mitaAsioitaHaluatEdistaa, ammatti);

		candidateList = dao.editCandidate(ehdokas);

		request.setAttribute("candidateList", candidateList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showshort.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} //EditCandidate-sulje
	
	@POST //have to be post, because the info comes from form
	@Path("/addcandidateanswers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded") //!!!!
	public void addCandidateAnswers()
	{
		
		String done;
		Dao dao = new Dao();
		VastauksetPK vastaus = new VastauksetPK();

		done = dao.addCandidateAnswers(vastaus);

		request.setAttribute("success", done);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/success.jps");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} //addCandidateanswers-sulje

} // class sulje
