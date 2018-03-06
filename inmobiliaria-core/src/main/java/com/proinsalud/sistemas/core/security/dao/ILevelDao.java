package com.proinsalud.sistemas.core.security.dao;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.Level;

public interface ILevelDao {

	public Level persistEntity(Level entity);

	public Level mergeEntity(Level entity);

	public void deleteEntity(Level entity);

	public List<Level> findAllEntity();

	public Level findEntityById(Long id);

}
