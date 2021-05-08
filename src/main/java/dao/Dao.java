package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;

import data.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * 
 * @author Jenni Lehtonen, Liisa Vuorenmaa, Riikka Siukola, Sanna Nieminen-Vuorio
 * All JPA-methods
 *
 */
public class Dao {

	/**
	 * Entity manager factory, that been used in all methods
	 */
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");
	
	
	/**
	 * @author Sanna Nieminen-Vuorio
	 * Reads all candidates from database
	 * @return list List of all candidates
	 */
	public List<Ehdokkaat> getAllCandidates()
	{
		EntityManager em=emf.createEntityManager();

		List<Ehdokkaat> list = em.createQuery("select a from Ehdokkaat a").getResultList();

		em.close();

		return list;
	}

	
	/**
	 * Reads all questions from database
	 * @return list List of questions
	 */
	public List<Kysymykset> getAllQuestions()
	{
		EntityManager em=emf.createEntityManager();
		List<Kysymykset> list = em.createQuery("select a from Kysymykset a").getResultList();
		em.close();

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
			em.close();
			
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
	
	/**
	 * @author Riikka Siukola
	 * Adds a new candidate to the database
	 * @param e for Ehdokkaat
	 */
	
	public void addCandidate(Ehdokkaat e) {

		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @author Riikka Siukola
	 * Removes a candidate and their answers from the database
	 * @param id
	 */
	public void deleteCandidate(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
				
		em.createNativeQuery("delete from vastaukset where ehdokas_id="+id).executeUpdate();
		
		Object ehdokas = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=?1").setParameter(1, id).getSingleResult();

		try {
			em.remove(ehdokas);
		} catch (Exception e) {

		}

		em.getTransaction().commit();
		em.close();

	}

	/**
	 * @author Sanna Nieminen-Vuorio
	 * Reads candidates from database based on party
	 * @param party
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
	 * @return ehdokas, Ehdokkaat-object
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

	} // editCandidate-sulje

	/**
	 * @author Sanna Nieminen-Vuorio
	 * Edit candidate answers to database. Can be used also if there is no answers, but candidate is created.
	 * Gets a list of candidate's answers as a parameter.
	 * @param list
	 * @return done
	 * Returns string that tells if the method was success or not
	 */
	public String editCandidateAnswers(ArrayList<Vastaukset> list)
	{
		String done = "Jotain meni vikaan";
		EntityManager em=emf.createEntityManager();

		em.getTransaction().begin();
		
		for(Vastaukset v : list)
		{
			try
			{
				Vastaukset vastaus = em.find(Vastaukset.class, v.getId());
				
					em.merge(v); //This line does the update
					System.out.println("Merge tehty, eli ei ollut tyhjä");
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
	} //editCandidateAnswers-sulje
	
	
/**
 * edit questions alkaa tästä
 * @param kysymys
 * @return done
 */
	public Kysymykset getQuestionById(int id)
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Kysymykset kysymys = em.find(Kysymykset.class, id);
		em.getTransaction().commit();
		
		em.close();
		return kysymys;
		
	}

	/**
	 * edit questions alkaa tästä
	 * @param kysymys
	 * @return list
	 */

public List<Kysymykset> editQuestion(Kysymykset kysymys)
	{
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		Kysymykset e = em.find(Kysymykset.class, kysymys.getKysymysId());
		try {
			em.merge(kysymys); 
			
			
		}
		catch(Exception exception) {
			
			System.out.println("ei toimi");
		}
		em.getTransaction().commit();
		em.close();

		List<Kysymykset> list=getAllQuestions();		
		return list;
		
	}
}
