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
		Ehdokkaat ehdokas = null;
		List<Ehdokkaat> list = new ArrayList<Ehdokkaat>();
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery("SELECT a FROM Ehdokkaat a WHERE a.puolue=:puolue");
	    query.setParameter("puolue", party);
	    try {
	    	ehdokas = (Ehdokkaat) query.getSingleResult();	
	    	list.add(ehdokas);
	    } catch (Exception e) {
	        // Handle exception
	    }

		//List<Ehdokkaat> list = em.createQuery("select a from Ehdokkaat a").getResultList();
		
		return list;
	}
}
