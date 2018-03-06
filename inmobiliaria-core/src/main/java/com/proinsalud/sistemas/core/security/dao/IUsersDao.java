package com.proinsalud.sistemas.core.security.dao;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.Users;

public interface IUsersDao {

	public Users persistEntity(Users entity);

	public Users mergeEntity(Users entity);

	public void deleteEntity(Users entity);

	public List<Users> findAllEntity();

	public Users findEntityById(Long id);
	
	public Users findUserCompletely(String username);
	
	public List<Users> findUsersProfileTemplate();
	
	public Users findEntityByUsername(String username);

}
