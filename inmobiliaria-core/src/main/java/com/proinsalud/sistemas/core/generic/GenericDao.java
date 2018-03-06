package com.proinsalud.sistemas.core.generic;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
public abstract class GenericDao<K, E> {

	@Autowired
	@Qualifier("sessionFactoryGeneral")
	private SessionFactory sessionFactory;

	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
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
	 * persist Metodo que permite crear un registro en la base de datos
	 * 
	 * @param entity:
	 *            entidad a registrar
	 * @return E entidad: entidad registrada
	 */
	public E persist(E entity) {
		getSession().persist(entity);
		return entity;
	}

	/**
	 * delete Metodo que permite eliminar un registro de la base de datos
	 * 
	 * @param entity:
	 *            entidad a eliminar
	 */
	public void delete(E entity) {
		getSession().delete(entity);
	}

	/**
	 * merge Metodo que permite actualizar un registro en la base de datos
	 * 
	 * @param entity:
	 *            entidad a actualizar
	 * @return E entidad: entidad actualizada
	 */
	public E merge(E entity) {
		getSession().merge(entity);
		return entity;
	}

	/**
	 * persistAll Metodo que permite registrar un listado de entidades en la base de
	 * datos
	 * 
	 * @param listEntity:
	 *            listado de entidades para registrar
	 * @return List<E> listado: listado registrado
	 */
	public List<E> persistAll(List<E> listEntity) {
		for (E entity : listEntity) {
			getSession().persist(entity);
			getSession().flush();
		}
		return listEntity;
	}

	/**
	 * mergeAll Metodo que permite actualizar un listado de entidades en la base de
	 * datos
	 * 
	 * @param listEntity:
	 *            listado de entidades a actualizar
	 * @return List<E> listado: listado de entidades actualizadas
	 */
	public List<E> mergeAll(List<E> listEntity) {
		for (E entity : listEntity) {
			getSession().merge(entity);
			getSession().flush();
		}
		return listEntity;
	}

	/**
	 * deleteAll Metodo que permite eliminar un listado completo de entidades
	 * pasadas como parametro
	 * 
	 * @param listEntity:
	 *            listado de entidades a eliminar
	 */
	public void deleteAll(List<E> listEntity) {
		for (E entity : listEntity) {
			getSession().delete(entity);
			getSession().flush();
		}
	}

	/**
	 * flush Limpia las operaciones realizadas en session
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * findById Metodo que permite encontrar un registro en base a su llave primaria
	 * 
	 * @param id:
	 *            identificador del registo a buscar
	 * @return E entidad: entidad encontrada
	 */
	@SuppressWarnings("unchecked")
	public E findById(K id) {
		return (E) getSession().get(entityClass, (Long) id);
	}

	/**
	 * findAll Metodo que permite encontrar todos los registros de una tabla
	 * 
	 * @return List<E> listado: Listado de entidades de una tabla
	 */
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return getSession().createQuery("from " + this.entityClass.getCanonicalName()).list();
	}

	/**
	 * executeNamedQuery Metodo que permite ejecutar un named query de una entidad
	 * especifica
	 * 
	 * @param String
	 *            namedQuery: consulta que se va a ejecutar
	 * @param HashMap<String,
	 *            Object> parameters: parametros enviados a la consulta
	 * @return List<E> listado: listado de entidades que se obtienen a partir del
	 *         named query
	 */
	public List<E> executeNamedQuery(String namedQuery, HashMap<String, Object> parameters) {
		Query query = getSession().getNamedQuery(namedQuery);
		for (String parameter : parameters.keySet()) {
			query.setParameter(parameter, parameters.get(parameter));
		}
		@SuppressWarnings("unchecked")
		ArrayList<E> result = new ArrayList<E>((List<E>) query.list());
		return result;
	}

	/**
	 * executeNamedQuery Metodo que permite ejecutar un named query de una entidad
	 * especifica
	 * 
	 * @param String
	 *            namedQuery: consulta que se va a ejecutar
	 * @param HashMap<String,
	 *            Object> parameters: parametros enviados a la consulta
	 * @return int : cantidad de objetos actualizado o eliminados
	 */
	public int executeNamedQueryUpdate(String namedQuery, HashMap<String, Object> parameters) {
		Query query = getSession().getNamedQuery(namedQuery);
		for (String parameter : parameters.keySet()) {
			query.setParameter(parameter, parameters.get(parameter));
		}
		return query.executeUpdate();
	}
	
	
	/**
	 * Metodo que permite ejecutar una consulta con un query nativo
	 * Precaución con los parametros, en algunos casos es necesario tenerlos seteados en la query
	 * @author Andres Santacruz
	 * @datetime 26/02/2018 - 10:43:55 a. m.
	 *
	 * @param queryString
	 * @param parameters
	 * @return
	 */
	public List<E> executeQuery(String queryString, HashMap<String, Object> parameters) {
		SQLQuery query = getSession().createSQLQuery(queryString);
		for (String parameter : parameters.keySet()) {
			query.setParameter(parameter, parameters.get(parameter));
		}
		query.addEntity(entityClass);
		@SuppressWarnings("unchecked")
		ArrayList<E> result = new ArrayList<E>((List<E>) query.list());
		return result;
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

}
