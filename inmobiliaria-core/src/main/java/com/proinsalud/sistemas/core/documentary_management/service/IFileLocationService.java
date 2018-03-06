package com.proinsalud.sistemas.core.documentary_management.service;

import java.util.List;

import com.proinsalud.sistemas.core.documentary_management.model.FileLocation;

public interface IFileLocationService {

	public FileLocation persistEntity(FileLocation entity);
	
	public FileLocation mergeEntity(FileLocation entity);
	
	public void deleteEntity(FileLocation entity);
	
	public List<FileLocation> findAllEntity();
	
	public List<FileLocation> findFileLocationRoot();
	
	public FileLocation findEntityById(Long id);
	
	public void persistEntity(List<FileLocation> entities);
	
	public void mergeEntity(List<FileLocation> entities);
	
	public void deleteEntity(List<FileLocation> entities);
	
}
