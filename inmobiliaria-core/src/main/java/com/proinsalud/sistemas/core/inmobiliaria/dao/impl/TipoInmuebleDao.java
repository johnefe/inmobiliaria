package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.ITipoInmuebleDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.TipoInmueble;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "tipoInmuebleDao")
public class TipoInmuebleDao extends GenericDao<Long, TipoInmueble> implements ITipoInmuebleDao, Serializable {

	private static final long serialVersionUID = 6613743588114629492L;

	public TipoInmueble persistEntity(TipoInmueble entity) {
		return super.persist(entity);
	}

	public TipoInmueble mergeEntity(TipoInmueble entity) {
		return super.merge(entity);
	}

	public void deleteEntity(TipoInmueble entity) {
		super.delete(entity);
	}

	public List<TipoInmueble> findAllEntity() {
		return super.findAll();
	}

	public TipoInmueble findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<TipoInmueble> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<TipoInmueble> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<TipoInmueble> entities) {
		super.deleteAll(entities);
	}

}
