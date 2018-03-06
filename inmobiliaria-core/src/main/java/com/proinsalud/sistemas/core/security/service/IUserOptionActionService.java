package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.UserOptionAction;

public interface IUserOptionActionService {

	public UserOptionAction persistEntity(UserOptionAction entity);
	
	public void persistEntity(List<UserOptionAction> entities);

	public UserOptionAction mergeEntity(UserOptionAction entity);

	public void deleteEntity(UserOptionAction entity);
	
	public void deleteEntity(List<UserOptionAction> entities);

	public List<UserOptionAction> findAllEntity();
	
	public UserOptionAction findEntityById(Long id);
	
	public void updateUserOptionAction(List<UserOptionAction> newUOA , List<UserOptionAction> deleteUOA);
}
