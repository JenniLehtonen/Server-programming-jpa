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
import javax.ws.rs.core.Context;

import data.Admin;


@Path("/loginrest")
public class Login {
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;

	String adminUsername;
	String adminPassword;
	String MD5Password;
	
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
		 * Get admin's username and password from the database
		 */
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Admin> list=em.createQuery("SELECT a FROM Admin a").getResultList();
		em.getTransaction().commit();
		
		/**
		 * Save admin's username and password to variables
		 */
		for (Admin admin : list) {
			   adminUsername = admin.getUsername();
			   adminPassword = admin.getPassword();
			   System.out.println(adminUsername + ", " + adminPassword);
			}
		
		/**
		 * Send admin's real password and the password provided by the user to the method that creates crypts them
		 */
		try {
			MD5Password = data.LoginData.crypt(adminPassword);
			password = data.LoginData.crypt(password);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		/**
		 * Send variables to login.jsp that checks if the username and password that the user has provided are correct or not
		 */
		request.setAttribute("userProvidedUsername", username);
		request.setAttribute("password", password); 
		request.setAttribute("username", adminUsername);
		request.setAttribute("MD5Password", MD5Password);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response); 
	}
}