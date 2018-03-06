package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IPublicityInmuebleDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.PublicityInmueble;
import com.proinsalud.sistemas.core.inmobiliaria.service.IPublicityInmuebleService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "publicityInmuebleService")
public class PublicityInmuebleService implements IPublicityInmuebleService, Serializable {

	private static final long serialVersionUID = -3685659217701707406L;
	@Autowired(required = true)
	@Qualifier(value = "publicityInmuebleDao")
	private IPublicityInmuebleDao iPublicityInmuebleDao;

	@Transactional
	public PublicityInmueble persistEntity(PublicityInmueble entity) {
		return iPublicityInmuebleDao.persistEntity(entity);
	}

	@Transactional
	public PublicityInmueble mergeEntity(PublicityInmueble entity) {
		return iPublicityInmuebleDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(PublicityInmueble entity) {
		iPublicityInmuebleDao.deleteEntity(entity);
	}

	@Transactional
	public List<PublicityInmueble> findAllEntity() {
		return iPublicityInmuebleDao.findAllEntity();
	}

	@Transactional
	public PublicityInmueble findEntityById(Long id) {
		return iPublicityInmuebleDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<PublicityInmueble> entities) {
		iPublicityInmuebleDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<PublicityInmueble> entities) {
		iPublicityInmuebleDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<PublicityInmueble> entities) {
		iPublicityInmuebleDao.deleteEntity(entities);
	}

}

