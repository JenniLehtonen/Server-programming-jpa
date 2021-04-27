package rest;

import java.util.ArrayList;

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
	
	
	@POST
	@Path("/getallcandidates")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	public ArrayList<Ehdokkaat> getAllCandidates()
	{
		ArrayList<Ehdokkaat> candidateList = new ArrayList<Ehdokkaat>();
		Dao dao = new Dao();
		
		candidateList = dao.getAllCandidates();
		
		return candidateList;
		
	}
	{
		
	}

}
