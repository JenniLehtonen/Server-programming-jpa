package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;

import data.Ehdokkaat;
import data.Kysymykset;

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
	/**
	 * @author Jenni Lehtonen
	 * Method for adding new questions to the database
	 * @param New question to be added
	 * @throws SQLException
	 */
	public void addQuestion(Kysymykset kysymys) throws SQLException {
		try {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(kysymys);
			em.getTransaction().commit();
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Jenni Lehtonen
	 * Method for removing questions from the database
	 * @param Question id for the question that needs to be removed from the database
	 */
	public void removeQuestion(int id) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Object kysymys = em.createQuery("SELECT a FROM Kysymykset a WHERE a.kysymysId=?1").setParameter(1, id).getSingleResult();

		try {
			em.remove(kysymys);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Jenni Lehtonen
	 * Get all the candidates' answers
	 * @return List of candidates and their answers
	 */
	public List<Ehdokkaat> readAllAnswers() {
		List<Ehdokkaat> list = new ArrayList<>();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		list = em.createQuery("select e from Ehdokkaat e").getResultList();
		em.getTransaction().commit();
		em.close();
		
		return list;
	}
	
	public void addCandidate(Ehdokkaat e) {

		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}

	public void deleteCandidate(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Object ehdokas = em.createQuery("SELECT a FROM Ehdokkaat a WHERE a.ehdokasId=?1").setParameter(1, id).getSingleResult();

		try {
			em.remove(ehdokas);
		} catch (Exception e) {

		}

		em.getTransaction().commit();

		em.close();

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


	/**
	 * @author Sanna Nieminen-Vuorio
	 * Edit candidate by candidate's id and return list of all candidates
	 * @param ehdokas
	 * @return list of candidates
	 */
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

	/**
	 * @author Sanna Nieminen-Vuorio
	 * @param vastaus
	 * @return done
	 */
	public String addCandidateAnswers(List<Vastaukset> list)
	{
		String done = "Jotain meni vikaan";
		EntityManager em=emf.createEntityManager();

		em.getTransaction().begin();
		for(Vastaukset v : list)
		{
			try
			{
				
				Vastaukset vastaus = em.find(Vastaukset.class, v.getId().getEhdokasId());

				if (vastaus != null) 
				{
					em.merge(v); //This line does the update
				}
				else if(vastaus == null)
				{
					em.persist(v);

				}
				
				System.out.println("Ehdokkaan vastaukset päivitetty");
				done = "Vastaukset päivitetty onnistuneesti";
			}
			catch(Exception e)
			{
				System.out.println(e);
				System.out.println("Jotain meni vikaan vastausten päivittämisessä");
				done = "Tietojen päivittäminen ei onnistunut";
			}

		}
			em.getTransaction().commit();
			em.close();

			return done;
	}
}
