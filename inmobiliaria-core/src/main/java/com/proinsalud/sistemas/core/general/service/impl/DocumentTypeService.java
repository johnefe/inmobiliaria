package com.proinsalud.sistemas.core.general.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.IDocumentTypeDao;
import com.proinsalud.sistemas.core.general.model.DocumentType;
import com.proinsalud.sistemas.core.general.service.IDocumentTypeService;

@Repository(value = "documentTypeService")
public class DocumentTypeService implements IDocumentTypeService, Serializable {

	private static final long serialVersionUID = -2516846155356750532L;
	@Autowired(required = true)
	@Qualifier(value = "documentTypeDao")
	private IDocumentTypeDao iDocumentTypeDao;

	@Transactional
	public DocumentType persistEntity(DocumentType entity) {
		return iDocumentTypeDao.persistEntity(entity);
	}

	@Transactional
	public DocumentType mergeEntity(DocumentType entity) {
		return iDocumentTypeDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(DocumentType entity) {
		iDocumentTypeDao.deleteEntity(entity);
	}

	@Transactional
	public List<DocumentType> findAllEntity() {
		return iDocumentTypeDao.findAllEntity();
	}

	@Transactional
	public DocumentType findEntityById(Long id) {
		return iDocumentTypeDao.findEntityById(id);
	}

}

