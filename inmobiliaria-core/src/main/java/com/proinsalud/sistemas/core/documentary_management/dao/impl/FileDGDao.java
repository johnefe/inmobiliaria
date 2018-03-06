package com.proinsalud.sistemas.core.documentary_management.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.documentary_management.dao.IFileDGDao;
import com.proinsalud.sistemas.core.documentary_management.model.FileDG;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 10:58:14 a. m.
 */
@Repository(value = "fileDGDao")
public class FileDGDao extends GenericDao<Long, FileDG> implements IFileDGDao, Serializable {

	private static final long serialVersionUID = -2006712169228331118L;

	@Override
	public FileDG persistEntity(FileDG entity) {
		return super.persist(entity);
	}

	@Override
	public FileDG mergeEntity(FileDG entity) {
		return super.merge(entity);
	}

	@Override
	public void deleteEntity(FileDG entity) {
		super.delete(entity);
	}

	@Override
	public List<FileDG> findAllEntity() {
		return super.findAll();
	}

	@Override
	public FileDG findEntityById(Long id) {
		return super.findById(id);
	}

	@Override
	public void persistEntity(List<FileDG> entities) {
		super.persistAll(entities);
	}

	@Override
	public void mergeEntity(List<FileDG> entities) {
		super.mergeAll(entities);
	}

	@Override
	public void deleteEntity(List<FileDG> entities) {
		super.deleteAll(entities);
	}

}
