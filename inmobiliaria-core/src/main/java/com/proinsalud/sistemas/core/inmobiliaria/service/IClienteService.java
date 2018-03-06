package com.proinsalud.sistemas.core.inmobiliaria.service;

import java.util.List;

import com.proinsalud.sistemas.core.inmobiliaria.model.Cliente;

public interface IClienteService {

	public Cliente persistEntity(Cliente entity);
	
	public Cliente mergeEntity(Cliente entity);
	
	public void deleteEntity(Cliente entity);
	
	public List<Cliente> findAllEntity();
	
	public Cliente findEntityById(Long id);
	
	public void persistEntity(List<Cliente> entities);
	
	public void mergeEntity(List<Cliente> entities);
	
	public void deleteEntity(List<Cliente> entities);
	
}
