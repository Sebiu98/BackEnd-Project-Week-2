package com.epicode.project.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epicode.project.model.Readable;
import com.epicode.project.util.JpaUtil;

public class ReadableDAO {
	private static final Logger logger = LoggerFactory.getLogger(Readable.class);

	public void save(Readable object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.persist(object);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();

			logger.error("Error saving object: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	public void refresh(Readable object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(object);

		} finally {
			em.close();
		}

	}

	public Readable getById(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			return em.find(Readable.class, id);

		} finally {
			em.close();
		}

	}

	public void delete(Readable object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.remove(object);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}
	
	public void deleteByIsbn(String isbn) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			Query query = em.createQuery("DELETE FROM Readable el WHERE el.isbn = :isbn");

			query.setParameter("isbn", isbn);
			
			query.executeUpdate();

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting object: " + isbn, ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Readable> getElementsByIsbn(String isbn) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("elementByIsbn");

			query.setParameter("isbn", isbn);
			return query.getResultList();

		} finally {
			em.close();
		}
	}



}
