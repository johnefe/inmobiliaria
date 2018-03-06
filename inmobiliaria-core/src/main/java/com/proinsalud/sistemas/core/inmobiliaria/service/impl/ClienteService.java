package com.proinsalud.sistemas.core.inmobiliaria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.inmobiliaria.dao.IClienteDao;
import com.proinsalud.sistemas.core.inmobiliaria.model.Cliente;
import com.proinsalud.sistemas.core.inmobiliaria.service.IClienteService;
/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 11:45:15 a. m.
 */
@Repository(value = "clienteService")
public class ClienteService implements IClienteService, Serializable {

	private static final long serialVersionUID = 6140980878155506631L;
	@Autowired(required = true)
	@Qualifier(value = "clienteDao")
	private IClienteDao iClienteDao;

	@Transactional
	public Cliente persistEntity(Cliente entity) {
		return iClienteDao.persistEntity(entity);
	}

	@Transactional
	public Cliente mergeEntity(Cliente entity) {
		return iClienteDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Cliente entity) {
		iClienteDao.deleteEntity(entity);
	}

	@Transactional
	public List<Cliente> findAllEntity() {
		return iClienteDao.findAllEntity();
	}

	@Transactional
	public Cliente findEntityById(Long id) {
		return iClienteDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Cliente> entities) {
		iClienteDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Cliente> entities) {
		iClienteDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Cliente> entities) {
		iClienteDao.deleteEntity(entities);
	}

}

