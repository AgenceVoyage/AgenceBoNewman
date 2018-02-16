package fr.adaming.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.adaming.model.Test;

public class LeVraiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityTransaction tx = null;
		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {

			// 1) Création de l'entityManagerFactory

			emf = Persistence.createEntityManagerFactory("pu");
			System.out.println("voici le emf :"+emf);
			// 2) Création de l'entityManager (le bus)

			em = emf.createEntityManager();

			// 3) Creer une transaction

			tx = em.getTransaction();

			// 4) Ouvrir (commencer) la transaction

			tx.begin();

			// Instanciation des étudiants

			Test e1 = new Test("toto");

			// 5) Relier ces objets avec le context de l'entityManager (rendre
			// ces
			// objets persistants)

			em.persist(e1);
			
			System.out.println("voici le test :"+e1);

			// 6) Commiter la transaction

			tx.commit();

		} catch (Exception e) {
			if (tx != null)

				// Revenir a l'état initial de tx

				tx.rollback();

		} finally {
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}
		
		
	}


