package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.proinsalud.sistemas.core.security.model.Users;

public interface IUsersService {

	public Users persistEntity(Users entity);

	public Users mergeEntity(Users entity);

	public void deleteEntity(Users entity);

	public List<Users> findAllEntity();

	public Users findEntityById(Long id);

	public UserDetails loadUserByUsername(String nombreUsuario);

	public Users findUserCompletely(String username);

	public List<Users> findUsersProfileTemplate();

	public Users findEntityByUsername(String username);
}
