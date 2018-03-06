package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.UserHistorial;

public interface IUserHistorialService {

	public UserHistorial persistEntity(UserHistorial entity);

	public UserHistorial mergeEntity(UserHistorial entity);

	public void deleteEntity(UserHistorial entity);

	public List<UserHistorial> findAllEntity();

	public UserHistorial findEntityById(Long id);

	public List<UserHistorial> findUserHistorialByIdUser(Long idUser);
	
	public static final String NAME_SERVICE = "userHistorialService";

}
