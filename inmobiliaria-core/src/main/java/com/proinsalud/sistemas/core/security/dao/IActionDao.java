package com.proinsalud.sistemas.core.security.dao;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.Action;

public interface IActionDao {

	public Action persistEntity(Action entity);

	public Action mergeEntity(Action entity);

	public void deleteEntity(Action entity);

	public List<Action> findAllEntity();

	public Action findEntityById(Long id);
	
}
