package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IComentaryDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Comentary;
import com.proinsalud.sistemas.core.inmobiliaria.service.IComentaryService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "comentaryService")
public class ComentaryService implements IComentaryService, Serializable {

	private static final long serialVersionUID = 2149731503547990913L;
	@Autowired(required = true)
	@Qualifier(value = "comentaryDao")
	private IComentaryDao iComentaryDao;

	@Transactional
	public Comentary persistEntity(Comentary entity) {
		return iComentaryDao.persistEntity(entity);
	}

	@Transactional
	public Comentary mergeEntity(Comentary entity) {
		return iComentaryDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Comentary entity) {
		iComentaryDao.deleteEntity(entity);
	}

	@Transactional
	public List<Comentary> findAllEntity() {
		return iComentaryDao.findAllEntity();
	}

	@Transactional
	public Comentary findEntityById(Long id) {
		return iComentaryDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Comentary> entities) {
		iComentaryDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Comentary> entities) {
		iComentaryDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Comentary> entities) {
		iComentaryDao.deleteEntity(entities);
	}

}

