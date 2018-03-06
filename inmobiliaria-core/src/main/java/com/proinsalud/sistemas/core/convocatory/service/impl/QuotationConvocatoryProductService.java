package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IQuotationConvocatoryProductDao;
import com.proinsalud.sistemas.core.convocatory.model.QuotationConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.service.IQuotationConvocatoryProductService;
/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:04:35 a. m.
 *
 */
@Repository(value = "quotationConvocatoryProductService")
public class QuotationConvocatoryProductService implements IQuotationConvocatoryProductService, Serializable {

	private static final long serialVersionUID = 7172433854250364611L;
	
	@Autowired(required = true)
	@Qualifier(value = "quotationConvocatoryProductDao")
	private IQuotationConvocatoryProductDao iQuotationConvocatoryProductDao;

	@Transactional
	public QuotationConvocatoryProduct persistEntity(QuotationConvocatoryProduct entity) {
		return iQuotationConvocatoryProductDao.persistEntity(entity);
	}

	@Transactional
	public QuotationConvocatoryProduct mergeEntity(QuotationConvocatoryProduct entity) {
		return iQuotationConvocatoryProductDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(QuotationConvocatoryProduct entity) {
		iQuotationConvocatoryProductDao.deleteEntity(entity);
	}

	@Transactional
	public List<QuotationConvocatoryProduct> findAllEntity() {
		return iQuotationConvocatoryProductDao.findAllEntity();
	}

	@Transactional
	public QuotationConvocatoryProduct findEntityById(Long id) {
		return iQuotationConvocatoryProductDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<QuotationConvocatoryProduct> entities) {
		iQuotationConvocatoryProductDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<QuotationConvocatoryProduct> entities) {
		iQuotationConvocatoryProductDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<QuotationConvocatoryProduct> entities) {
		iQuotationConvocatoryProductDao.deleteEntity(entities);
	}

}

