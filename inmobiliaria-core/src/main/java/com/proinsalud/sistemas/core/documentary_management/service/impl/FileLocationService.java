package com.proinsalud.sistemas.core.documentary_management.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.documentary_management.dao.IFileLocationDao;
import com.proinsalud.sistemas.core.documentary_management.model.FileLocation;
import com.proinsalud.sistemas.core.documentary_management.service.IFileLocationService;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 31/01/2018 - 9:53:16 a. m.
 */

@Repository(value = "fileLocationService")
public class FileLocationService implements IFileLocationService, Serializable {


	private static final long serialVersionUID = -3449369211764539392L;
	@Autowired(required = true)
	@Qualifier(value = "fileLocationDao")
	private IFileLocationDao iFileLocationDao;

	@Transactional
	public FileLocation persistEntity(FileLocation entity) {
		return iFileLocationDao.persistEntity(entity);
	}

	@Transactional
	public FileLocation mergeEntity(FileLocation entity) {
		return iFileLocationDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(FileLocation entity) {
		iFileLocationDao.deleteEntity(entity);
	}

	@Transactional
	public List<FileLocation> findAllEntity() {
		return iFileLocationDao.findAllEntity();
	}

	@Transactional
	public FileLocation findEntityById(Long id) {
		return iFileLocationDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<FileLocation> entities) {
		iFileLocationDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<FileLocation> entities) {
		iFileLocationDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<FileLocation> entities) {
		iFileLocationDao.deleteEntity(entities);
	}

	@Transactional
	public List<FileLocation> findFileLocationRoot() {
		return iFileLocationDao.findFileLocationRoot();
	}

}

