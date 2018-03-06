package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.OptionAction;

public interface IOptionActionService {

	public OptionAction persistEntity(OptionAction entity);
	
	public void persistEntity(List<OptionAction> entities);

	public OptionAction mergeEntity(OptionAction entity);

	public void deleteEntity(OptionAction entity);
	
	public void deleteEntity(List<OptionAction> entities);

	public List<OptionAction> findAllEntity();

	public OptionAction findEntityById(Long id);
	
	public List<OptionAction> findAllEntityByOption(Long idOption);
	
	public void updateOptionAction(List<OptionAction> newOA  , List<OptionAction> deleteOA);
}
