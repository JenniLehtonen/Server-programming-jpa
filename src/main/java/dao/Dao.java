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

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");
	public List<Ehdokkaat> getAllCandidates()
	{
		

		EntityManager em=emf.createEntityManager();
		

		List<Ehdokkaat> list = em.createQuery("select a from Ehdokkaat a").getResultList();
		
		em.close();
		
		return list;
	}
	
	public List<Kysymykset> getAllQuestions()
	{


		EntityManager em=emf.createEntityManager();
		

		
		List<Kysymykset> list = em.createQuery("select a from Kysymykset a").getResultList();
		
		return list;
	}
	
	public void addCandidate(Ehdokkaat e) {

		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 * 
	 * @return list List, where is all the data of one candidate based on party
	 */

	public List<Ehdokkaat> getCandidatesByParty(String party)
	{

		Ehdokkaat ehdokas = null;
		List<Ehdokkaat> list = new ArrayList<Ehdokkaat>();
		EntityManager em=emf.createEntityManager();
//		Query query = em.createQuery("SELECT a FROM Ehdokkaat a WHERE a.puolue=:puolue");
//	    query.setParameter("puolue", party);
		//Query query = null;
	    try {
//	    	ehdokas = (Ehdokkaat) query.getResultList();	
//	    	list.add(ehdokas);
	    	
	    	list = em.createQuery("SELECT a FROM Ehdokkaat a WHERE a.puolue=?1").setParameter(1, party).getResultList();
	    	//query.setParameter("puolue", party);
	    } catch (Exception e) {
	        // Handle exception
	    }

		//List<Ehdokkaat> list = em.createQuery("select a from Ehdokkaat a").getResultList();
	    em.close();
		return list;
	}
}
