package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.inmobiliaria.dao.IInmuebleDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Inmueble;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "inmuebleDao")
public class InmuebleDao extends GenericDao<Long, Inmueble> implements IInmuebleDao, Serializable {

	private static final long serialVersionUID = 9031941596436829995L;

	public Inmueble persistEntity(Inmueble entity) {
		return super.persist(entity);
	}

	public Inmueble mergeEntity(Inmueble entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Inmueble entity) {
		super.delete(entity);
	}

	public List<Inmueble> findAllEntity() {
		return super.findAll();
	}

	public Inmueble findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Inmueble> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Inmueble> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Inmueble> entities) {
		super.deleteAll(entities);
	}

	public List<Inmueble> findArriendo() {
		String nArriendo= "ARRIENDO";
		String nAnticres= "ANTICRES";
		String nVenta= "VENTA";
		List<Inmueble> lst = new ArrayList<Inmueble>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nArriendo", nArriendo);
		parametros.put("nAnticres", nAnticres);
		parametros.put("nVenta", nVenta);
		lst = executeNamedQuery("Inmueble.findArriendo", parametros);
		return lst;
	}

}
