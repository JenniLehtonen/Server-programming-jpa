package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;

import data.*;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Dao {


	public List<Ehdokkaat> getAllCandidates()
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Server-programming-jpa");

		EntityManager em=emf.createEntityManager();
		

		List<Ehdokkaat> list = em.createQuery("select a from Ehdokkaat a").getResultList();
		
		return list;
	}
}
