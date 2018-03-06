package com.proinsalud.sistemas.core.generic;

import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * La clase GenericDaoGeneral es una clase abstracta que agrupa todos los
 * metodos utilizados en todos los DAOs que lo hereden
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:52:34 a. m.
 *
 * @param <K>
 * @param <E>
 */
public abstract class GenericSimpleDao {

	@Autowired
	@Qualifier("sessionFactoryGeneral")
	private SessionFactory sessionFactory;

	public GenericSimpleDao() {
	}

	/**
	 * getSession Metodo que permite obtener la sesion del session factory que se
	 * esta utilizando para realizar las operacion de base de datos
	 * 
	 * @return Session sesion: sesion actual
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * setSessionFactory Metodo que permite modifiar el session factory
	 * 
	 * @param sessionFactory:
	 *            session factory que se va a modificar
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public int executeNamedQueryUpdate(String query, HashMap<String, Object> parameters) {
		Query q = getSession().createSQLQuery(query);
		for (String parameter : parameters.keySet()) {
			q.setParameter(parameter, parameters.get(parameter));
		}
		return q.executeUpdate();
	}
}
