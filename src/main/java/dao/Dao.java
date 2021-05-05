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
	
	/*public Ehdokkaat getCandidateById(int id)
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokkaat ehdokas = em.find(Ehdokkaat.class, id);
		em.getTransaction().commit();

		em.close();
		return ehdokas;

	}*/

	
	/*public List<VastauksetPK> readAllAnswers() {
		List<VastauksetPK> list = new ArrayList<>();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//list = em.createQuery("select a from Ehdokkaat a").getResultList();
		list = em.createNativeQuery("select vastaukset.kysymys_id, vastaukset.vastaus, ehdokkaat.ehdokas_id, ehdokkaat.etunimi, ehdokkaat.sukunimi from vastaukset inner join ehdokkaat on vastaukset.ehdokas_id=ehdokkaat.ehdokas_id;").getResultList();
		em.getTransaction().commit();
		em.close();
		
		return list;
	}*/

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
	public String addCandidateAnswers(VastauksetPK vastaus)
	{
		String done = "Jotain meni vikaan";
		EntityManager em=emf.createEntityManager();

		try
		{
			em.getTransaction().begin();
			Vastaukset v = em.find(Vastaukset.class, vastaus.getEhdokasId());
			if (v!=null) {
				em.merge(vastaus); //This line does the update
			}
			em.getTransaction().commit();
			em.close();
			done = "Vastaukset päivitetty onnistuneesti";
		}
		catch(Exception e)
		{
			System.out.println(e);
			done = "Tietojen päivittäminen ei onnistunut";
		}


		return done;
	}
}
