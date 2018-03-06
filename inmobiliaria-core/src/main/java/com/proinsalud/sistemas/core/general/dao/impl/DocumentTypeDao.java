package com.proinsalud.sistemas.core.general.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.general.dao.IDocumentTypeDao;
import com.proinsalud.sistemas.core.general.model.DocumentType;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "documentTypeDao")
public class DocumentTypeDao extends GenericDao<Long, DocumentType> implements IDocumentTypeDao, Serializable {

	private static final long serialVersionUID = -7171699542714225698L;

	public DocumentType persistEntity(DocumentType entity) {
		return super.persist(entity);
	}

	public DocumentType mergeEntity(DocumentType entity) {
		return super.merge(entity);
	}

	public void deleteEntity(DocumentType entity) {
		super.delete(entity);
	}

	public List<DocumentType> findAllEntity() {
		return super.findAll();
	}

	public DocumentType findEntityById(Long id) {
		return super.findById(id);
	}

}
