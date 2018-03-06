package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IQuotationDao;
import com.proinsalud.sistemas.core.convocatory.model.Quotation;
import com.proinsalud.sistemas.core.convocatory.service.IQuotationService;
/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:04:44 a. m.
 *
 */
@Repository(value = "quotationService")
public class QuotationService implements IQuotationService, Serializable {

	private static final long serialVersionUID = 3164003983422801075L;
	
	@Autowired(required = true)
	@Qualifier(value = "quotationDao")
	private IQuotationDao iQuotationDao;

	@Transactional
	public Quotation persistEntity(Quotation entity) {
		return iQuotationDao.persistEntity(entity);
	}

	@Transactional
	public Quotation mergeEntity(Quotation entity) {
		return iQuotationDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Quotation entity) {
		iQuotationDao.deleteEntity(entity);
	}

	@Transactional
	public List<Quotation> findAllEntity() {
		return iQuotationDao.findAllEntity();
	}

	@Transactional
	public Quotation findEntityById(Long id) {
		return iQuotationDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Quotation> entities) {
		iQuotationDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Quotation> entities) {
		iQuotationDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Quotation> entities) {
		iQuotationDao.deleteEntity(entities);
	}

	@Transactional
	public List<Quotation> findEntityByIdProvider(Long idProvider) {
		return iQuotationDao.findEntityByIdProvider(idProvider);
	}

}

