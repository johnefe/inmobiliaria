package com.proinsalud.sistemas.core.security.dao;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.OptionAction;

public interface IOptionActionDao {

	public OptionAction persistEntity(OptionAction entity);

	public void persistEntity(List<OptionAction> entities);

	public OptionAction mergeEntity(OptionAction entity);

	public void deleteEntity(OptionAction entity);

	public void deleteEntity(List<OptionAction> entities);

	public List<OptionAction> findAllEntity();

	public OptionAction findEntityById(Long id);
	
	public List<OptionAction> findAllEntityByOption(Long idOption);

}
