package com.epicode.project.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epicode.project.model.Loan;
import com.epicode.project.util.JpaUtil;

public class LoanDAO {
	private static final Logger logger = LoggerFactory.getLogger(LoanDAO.class);

	public void save(Loan object) {
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

	public void refresh(Loan object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			em.refresh(object);

		} finally {
			em.close();
		}

	}

	public Loan getById(Long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			return em.find(Loan.class, id);

		} finally {
			em.close();
		}

	}

	public void delete(Loan object) {
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

	@SuppressWarnings("unchecked")
	public List<Loan> getLoanByNumber(String number) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("prestitoByTessera");

			query.setParameter("numeroTessera", number);
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Loan> getExpiredLoan() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			Query query = em.createNamedQuery("prestitoScaduto");

			return query.getResultList();

		} finally {
			em.close();
		}
	}

}
