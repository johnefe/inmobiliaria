package com.proinsalud.sistemas.core.inmobiliaria.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IClienteDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Cliente;
import com.proinsalud.sistemas.core.generic.GenericDao;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "clienteDao")
public class ClienteDao extends GenericDao<Long, Cliente> implements IClienteDao, Serializable {

	private static final long serialVersionUID = 1130254815232878921L;

	public Cliente persistEntity(Cliente entity) {
		return super.persist(entity);
	}

	public Cliente mergeEntity(Cliente entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Cliente entity) {
		super.delete(entity);
	}

	public List<Cliente> findAllEntity() {
		return super.findAll();
	}

	public Cliente findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Cliente> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Cliente> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Cliente> entities) {
		super.deleteAll(entities);
	}

}
