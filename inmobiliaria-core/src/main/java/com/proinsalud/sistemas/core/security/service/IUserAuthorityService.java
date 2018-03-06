package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.UserAuthority;

public interface IUserAuthorityService {

	public UserAuthority persistEntity(UserAuthority entity);
	
	public void persistEntity(List<UserAuthority> entities);

	public UserAuthority mergeEntity(UserAuthority entity);

	public void deleteEntity(UserAuthority entity);
	
	public void deleteEntity(List<UserAuthority> entities);

	public List<UserAuthority> findAllEntity();

	public UserAuthority findEntityById(Long id);

}
