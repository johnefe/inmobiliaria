package com.proinsalud.sistemas.core.documentary_management.service;

import java.io.InputStream;
import java.util.List;

import com.proinsalud.sistemas.core.documentary_management.model.FileDG;
import com.proinsalud.sistemas.core.documentary_management.model.Trd;

public interface IFileDGService {

	public FileDG persistEntity(FileDG entity);

	public FileDG mergeEntity(FileDG entity);

	public void deleteEntity(FileDG entity);

	public List<FileDG> findAllEntity();

	public FileDG findEntityById(Long id);

	public void persistEntity(List<FileDG> entities);

	public void mergeEntity(List<FileDG> entities);

	public void deleteEntity(List<FileDG> entities);

	public FileDG persistEntity(InputStream inputStream, Trd trd) throws Exception;

	public FileDG persistEntity(InputStream inputStream, String nameFile, String rutaF, Trd trd) throws Exception;

}
