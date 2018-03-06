package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ITipoInmuebleDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.TipoInmueble;
import com.proinsalud.sistemas.core.inmobiliaria.service.ITipoInmuebleService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "tipoInmuebleService")
public class TipoInmuebleService implements ITipoInmuebleService, Serializable {

	private static final long serialVersionUID = 1114969264991652329L;
	@Autowired(required = true)
	@Qualifier(value = "tipoInmuebleDao")
	private ITipoInmuebleDao iTipoInmuebleDao;

	@Transactional
	public TipoInmueble persistEntity(TipoInmueble entity) {
		return iTipoInmuebleDao.persistEntity(entity);
	}

	@Transactional
	public TipoInmueble mergeEntity(TipoInmueble entity) {
		return iTipoInmuebleDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(TipoInmueble entity) {
		iTipoInmuebleDao.deleteEntity(entity);
	}

	@Transactional
	public List<TipoInmueble> findAllEntity() {
		return iTipoInmuebleDao.findAllEntity();
	}

	@Transactional
	public TipoInmueble findEntityById(Long id) {
		return iTipoInmuebleDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<TipoInmueble> entities) {
		iTipoInmuebleDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<TipoInmueble> entities) {
		iTipoInmuebleDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<TipoInmueble> entities) {
		iTipoInmuebleDao.deleteEntity(entities);
	}

}

