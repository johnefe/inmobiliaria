package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.Authority;

public interface IAuthorityService {

	public Authority persistEntity(Authority entity);

	public Authority mergeEntity(Authority entity);

	public void deleteEntity(Authority entity);

	public List<Authority> findAllEntity();

	public Authority findEntityById(Long id);

}
