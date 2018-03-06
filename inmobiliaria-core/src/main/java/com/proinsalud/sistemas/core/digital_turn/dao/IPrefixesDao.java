package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.Prefixes;

public interface IPrefixesDao {

	public Prefixes persistEntity(Prefixes entity);
	
	public Prefixes mergeEntity(Prefixes entity);
	
	public void deleteEntity(Prefixes entity);
	
	public List<Prefixes> findAllEntity();
	
	public Prefixes findEntityById(Long id);
	
	public void persistEntity(List<Prefixes> entities);
	
	public void mergeEntity(List<Prefixes> entities);
	
	public void deleteEntity(List<Prefixes> entities);
	
}
