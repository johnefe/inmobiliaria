package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IPublicityInmuebleDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.PublicityInmueble;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "publicityInmuebleDao")
public class PublicityInmuebleDao extends GenericDao<Long, PublicityInmueble> implements IPublicityInmuebleDao, Serializable {

	private static final long serialVersionUID = 6391727113162859242L;

	public PublicityInmueble persistEntity(PublicityInmueble entity) {
		return super.persist(entity);
	}

	public PublicityInmueble mergeEntity(PublicityInmueble entity) {
		return super.merge(entity);
	}

	public void deleteEntity(PublicityInmueble entity) {
		super.delete(entity);
	}

	public List<PublicityInmueble> findAllEntity() {
		return super.findAll();
	}

	public PublicityInmueble findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<PublicityInmueble> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<PublicityInmueble> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<PublicityInmueble> entities) {
		super.deleteAll(entities);
	}

}
