package br.edu.ifpb.tsi.gcd.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tsi.gcd.model.Clube;
import br.edu.ifpb.tsi.gcd.model.Requisito;

public class RequisitoDAO extends GenericDAOJPAImpl<Requisito, Long>{

	public RequisitoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public RequisitoDAO(EntityManager em) {
		super(em);
	}
	
	public List<Requisito> findAll() throws DAOException{
		List<Requisito> requisitos = new ArrayList<Requisito>();
		try {
			Query q = this.getEntityManager().createQuery("from Requisito r");
			requisitos = (List<Requisito>) q.getResultList();
		}catch (NoResultException nre){
			return null;
		}
		return requisitos;
	}
}
