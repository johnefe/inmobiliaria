package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IInmuebleDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Inmueble;
import com.proinsalud.sistemas.core.inmobiliaria.service.IInmuebleService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "inmuebleService")
public class InmuebleService implements IInmuebleService, Serializable {

	private static final long serialVersionUID = -8728793348920341425L;
	@Autowired(required = true)
	@Qualifier(value = "inmuebleDao")
	private IInmuebleDao iInmuebleDao;

	@Transactional
	public Inmueble persistEntity(Inmueble entity) {
		return iInmuebleDao.persistEntity(entity);
	}

	@Transactional
	public Inmueble mergeEntity(Inmueble entity) {
		return iInmuebleDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Inmueble entity) {
		iInmuebleDao.deleteEntity(entity);
	}

	@Transactional
	public List<Inmueble> findAllEntity() {
		return iInmuebleDao.findAllEntity();
	}

	@Transactional
	public Inmueble findEntityById(Long id) {
		return iInmuebleDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Inmueble> entities) {
		iInmuebleDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Inmueble> entities) {
		iInmuebleDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Inmueble> entities) {
		iInmuebleDao.deleteEntity(entities);
	}

	@Transactional
	public List<Inmueble> findArriendo() {
		// TODO Auto-generated method stub
		return iInmuebleDao.findArriendo();
	}

	@Transactional
	public List<Inmueble> findInmuebleByTipo(Long idTipo) {
		// TODO Auto-generated method stub
		return iInmuebleDao.findInmuebleByTipo(idTipo);
	}

}

