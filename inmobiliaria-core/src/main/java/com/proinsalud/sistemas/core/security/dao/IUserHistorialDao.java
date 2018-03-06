package com.proinsalud.sistemas.core.security.dao;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.UserHistorial;

public interface IUserHistorialDao {

	public UserHistorial persistEntity(UserHistorial entity);
	
	public UserHistorial mergeEntity(UserHistorial entity);
	
	public void deleteEntity(UserHistorial entity);
	
	public List<UserHistorial> findAllEntity();
	
	public UserHistorial findEntityById(Long id);
	
	public List<UserHistorial> findUserHistorialByIdUser(Long IdUser);
	
	
	
}
