package com.proinsalud.sistemas.core.documentary_management.dao;

import java.util.List;

import com.proinsalud.sistemas.core.documentary_management.model.FileDG;

public interface IFileDGDao {

	public FileDG persistEntity(FileDG entity);

	public FileDG mergeEntity(FileDG entity);

	public void deleteEntity(FileDG entity);

	public List<FileDG> findAllEntity();

	public FileDG findEntityById(Long id);

	public void persistEntity(List<FileDG> entities);

	public void mergeEntity(List<FileDG> entities);

	public void deleteEntity(List<FileDG> entities);

}
