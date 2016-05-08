package com.epul.oeuvres.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class EntityManagerService {

	protected static final String BASEOEUVRE = "ProjetOeuvresSpring";
	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction transaction;

	public EntityManagerService () {
		connection();
	}

	public void connection () {
		emf=Persistence.createEntityManagerFactory(BASEOEUVRE);
		em=emf.createEntityManager();
		transaction = em.getTransaction();
	}
	
	public ArrayList<?> findAll(String sql){
		transaction.begin();
		ArrayList<?> listObject = (ArrayList<?>) em.createQuery(sql).getResultList();
		em.close();
		emf.close();
		return listObject;
	}

	public Object find(Class<?> classe, int id) {
		Object object = null;
		transaction.begin();
		object = em.find(classe, id);
		em.close();
		emf.close();
		return object;
	}

	public void inserer(Object object){

		if (!em.contains(object)) {
			transaction.begin();
			em.persist(object);
			em.flush();
			transaction.commit();
		}
		em.close();
	}

	public boolean supprimer(Class<?> classe, int id) {

		transaction.begin();

		Object object = em.find(classe, id);
		em.remove(object);
		em.getTransaction().commit();
		em.close();
		emf.close();

		return true;
	}

	public void mettreAJour(Object object) {

		transaction.begin();
		em.merge(object);
		transaction.commit();
		em.close();
		emf.close();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public EntityManager getEm() {
		return em;
	}

	public EntityTransaction getTransaction() {
		return transaction;
	}

}
