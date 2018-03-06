package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.UserOption;

public interface IUserOptionService {

	public UserOption persistEntity(UserOption entity);
	
	public void persistEntity(List<UserOption> entities);

	public UserOption mergeEntity(UserOption entity);

	public void deleteEntity(UserOption entity);
	
	public void deleteEntity(List<UserOption> entities);

	public List<UserOption> findAllEntity();

	public UserOption findEntityById(Long id);
	
	public List<UserOption> findByUser(Long id);

	public UserOption findEntityByOptionUser(Long idOption, Long idUser);

	public void deleteAllEntityByIdUser(Long idUser);
}
