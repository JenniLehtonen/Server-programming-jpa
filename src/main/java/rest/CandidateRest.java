package rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
	
	/**
	 * @author Riikka Siukola
	 * 
	 * @param etunimi
	 * @param sukunimi
	 * @param puolue
	 * @param kotipaikkakunta
	 * @param ika
	 * @param miksi_eduskuntaan
	 * @param mita_asioita_haluat_edistaa
	 * @param ammatti
	 * @param fileInputStream
	 * @param fileMetaData
	 * @param sc
	 */
	@POST
	@Path("/addcandidate")
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void addCandidate(@FormDataParam("etunimi") String etunimi, @FormDataParam("sukunimi") String sukunimi, @FormDataParam("puolue") String puolue, @FormDataParam("kotipaikkakunta") String kotipaikkakunta, @FormDataParam("ika") String ika, @FormDataParam("miksi_eduskuntaan") String miksi_eduskuntaan, @FormDataParam("mita_asioita_haluat_edistaa") String mita_asioita_haluat_edistaa, @FormDataParam("ammatti") String ammatti, @FormDataParam("kuva") InputStream fileInputStream, @FormDataParam("kuva") FormDataContentDisposition fileMetaData, @Context ServletContext sc) {
		Ehdokkaat e = new Ehdokkaat();
		e.setEtunimi(etunimi);
		e.setSukunimi(sukunimi);
		e.setPuolue(puolue);
		e.setKotipaikkakunta(kotipaikkakunta);
		e.setIka(Integer.valueOf(ika));
		e.setMiksiEduskuntaan(miksi_eduskuntaan);
		e.setMitaAsioitaHaluatEdistaa(mita_asioita_haluat_edistaa);
		e.setAmmatti(ammatti);
		e.setKuva(fileMetaData.getFileName());
		
		String UPLOAD_PATH="./img";
	    try{
	        int read = 0;
	        byte[] bytes = new byte[1024];
	 
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/"+fileMetaData.getFileName()));
	        while ((read = fileInputStream.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	        
	    } 
	    catch (IOException exception){
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
		
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
	
	/**
	 * @author Riikka Siukola
	 * 
	 * @param id
	 */
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
	
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 */
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
	
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 */
	@POST //have to be post, because the info comes from form
	@Path("/editcandidate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.MULTIPART_FORM_DATA}) 
	public void editCandidate(@FormDataParam("ehdokasId") int id, @FormDataParam("sukunimi") String sukunimi, @FormDataParam("etunimi") String etunimi, @FormDataParam("puolue") String puolue, @FormDataParam("kotipaikkakunta") String kotipaikkakunta, @FormDataParam("ika") int ika, @FormDataParam("ammatti") String ammatti, @FormDataParam("miksiEduskuntaan") String miksiEduskuntaan, @FormDataParam("mitaAsioitaHaluatEdistaa") String mitaAsioitaHaluatEdistaa, @FormDataParam("kuva") InputStream fileInputStream, @FormDataParam("kuva") FormDataContentDisposition fileMetaData, @Context ServletContext sc)
	{
	
		
		List<Ehdokkaat> candidateList = new ArrayList<Ehdokkaat>();
		Dao dao = new Dao();
		Ehdokkaat ehdokas = new Ehdokkaat(id, sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksiEduskuntaan, mitaAsioitaHaluatEdistaa, ammatti);
		try {
			
			if(fileMetaData != null && fileInputStream != null)
			{
				ehdokas.setKuva(fileMetaData.getFileName());
				
				String UPLOAD_PATH="./img";
			    try{
			        int read = 0;
			        byte[] bytes = new byte[1024];
			 
			        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/"+fileMetaData.getFileName()));
			        while ((read = fileInputStream.read(bytes)) != -1) 
			        {
			            out.write(bytes, 0, read);
			        }
			        out.flush();
			        out.close();
			    } 
			    catch (IOException exception){
			        throw new WebApplicationException("Error while uploading file. Please try again !!");
			    }
			}
			else
			{
				ehdokas.setKuva(dao.getCandidateById(id).getKuva());
			}
		}
		catch(Exception e)
		{
			System.out.println("Error on "+ e);
			ehdokas.setKuva(dao.getCandidateById(id).getKuva());
		}
		
		
		
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
	

	
} // class sulje
