package com.proinsalud.sistemas.core.general.service;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.DocumentType;

public interface IDocumentTypeService {

	public DocumentType persistEntity(DocumentType entity);
	
	public DocumentType mergeEntity(DocumentType entity);
	
	public void deleteEntity(DocumentType entity);
	
	public List<DocumentType> findAllEntity();
	
	public DocumentType findEntityById(Long id);
	
}
