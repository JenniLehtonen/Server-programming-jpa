package rest;

import java.io.IOException;
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
import javax.ws.rs.core.Context;

import data.Admin;


@Path("/loginrest")
public class Login {
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	String username;
	String password;
	
	@GET
	@Path("/loginpage")
	public void goToLoginPage() throws ServletException, IOException {
		//request.setAttribute("testi", "testi");
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		 dispatcher.forward(request, response); 
	}
	
	@POST
	@Path("/login")
	@Consumes("application/x-www-form-urlencoded")
	public void login(@FormParam("username") String username, @FormParam("password") String password) throws ServletException, IOException{
		
		/**
	     * Username and password that the user has provided. The information comes from login.jsp
	     */
		this.username=username;
		this.password=password;
		System.out.println(username + password);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Admin> list=em.createQuery("SELECT a FROM Admin a").getResultList();
		em.getTransaction().commit();
		System.out.println(list);
		
		//request.setAttribute("booklist", list); //Lähetetään arraylist bookform.jsp-tiedostolle näytettäväksi
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response); 
	}

}
