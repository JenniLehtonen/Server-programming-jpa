package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;

import data.*;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Dao {

	
	/**
	 * @author Sanna Nieminen-Vuorio
	 * 
	 * @return list List, where is all the data of candidates
	 */
	public List<Ehdokkaat> getAllCandidates()
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");

		EntityManager em=emf.createEntityManager();
		

		List<Ehdokkaat> list = em.createQuery("select a from Ehdokkaat a").getResultList();
		
		return list;
	}
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 * 
	 * @return list List, where is all the data of one candidate based on party
	 */
	public List<Ehdokkaat> getCandidatesByParty(String party)
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");

		EntityManager em=emf.createEntityManager();
//		Query query = em.createQuery("SELECT u FROM User u WHERE u.email=:email");
//	    query.setParameter("email", party);
//	    try {
//	    	List<Ehdokkaat> list = (Ehdokkaat) query.getSingleResult();
//	    } catch (Exception e) {
//	        // Handle exception
//	    }

		List<Ehdokkaat> list = em.createQuery("select a from Ehdokkaat a").getResultList();
		
		return list;
	}
}
