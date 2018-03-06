package com.proinsalud.sistemas.core.documentary_management.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.documentary_management.dao.IFileLocationDao;
import com.proinsalud.sistemas.core.documentary_management.model.FileLocation;
import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.util.comparators.GeneralComparator;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 31/01/2018 - 9:52:30 a. m.
 */

@Repository(value = "fileLocationDao")
public class FileLocationDao extends GenericDao<Long, FileLocation> implements IFileLocationDao, Serializable {

	private static final long serialVersionUID = -423230203336300227L;

	public FileLocation persistEntity(FileLocation entity) {
		return super.persist(entity);
	}

	public FileLocation mergeEntity(FileLocation entity) {
		return super.merge(entity);
	}

	public void deleteEntity(FileLocation entity) {
		super.delete(entity);
	}

	public List<FileLocation> findAllEntity() {
		return super.findAll();
	}

	public FileLocation findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<FileLocation> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<FileLocation> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<FileLocation> entities) {
		super.deleteAll(entities);
	}

	@Override
	public List<FileLocation> findFileLocationRoot() {
		List<FileLocation> fileLocations = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		fileLocations = executeNamedQuery("FileLocation.findFileLocationRoot", params);
		if (!fileLocations.isEmpty()) {
			GeneralComparator.organized(GeneralComparator.FileLocationCompareByName, fileLocations, "getFileLocations");
		}
		return fileLocations;
	}

}
