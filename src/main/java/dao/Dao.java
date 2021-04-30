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

		List<Ehdokkaat> list = new ArrayList<Ehdokkaat>();
		EntityManager em=emf.createEntityManager();

	    try {

	    	
	    	list = em.createQuery("SELECT a FROM Ehdokkaat a WHERE a.puolue=?1").setParameter(1, party).getResultList();

	    } catch (Exception e) {
	        // Handle exception
	    }

	    em.close();
		return list;
	} //getCandidateByParty-sulje
	
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 * Method gets one candidate based on id
	 * @param id
	 * @return Ehdokkaat-object
	 */
	public Ehdokkaat getCandidateById(int id)
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokkaat ehdokas = em.find(Ehdokkaat.class, id);
		em.getTransaction().commit();
		
		em.close();
		return ehdokas;
		
	}//getCandidateById-sulje
	
	public List<Ehdokkaat> editCandidate(Ehdokkaat ehdokas)
	{
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		Ehdokkaat e = em.find(Ehdokkaat.class, ehdokas.getEhdokasId());
		if (e!=null) {
			em.merge(ehdokas); //This line does the update
		}
		em.getTransaction().commit();

		List<Ehdokkaat> list=getAllCandidates();
		
		em.close();
		
		return list;
		
	}
}
