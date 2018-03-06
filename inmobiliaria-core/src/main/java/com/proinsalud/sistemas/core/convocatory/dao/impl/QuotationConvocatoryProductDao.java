package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IQuotationConvocatoryProductDao;
import com.proinsalud.sistemas.core.convocatory.model.QuotationConvocatoryProduct;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:02:05 a. m.
 *
 */
@Repository(value = "quotationConvocatoryProductDao")
public class QuotationConvocatoryProductDao extends GenericDao<Long, QuotationConvocatoryProduct> implements IQuotationConvocatoryProductDao, Serializable {

	private static final long serialVersionUID = -1556353967008067908L;

	public QuotationConvocatoryProduct persistEntity(QuotationConvocatoryProduct entity) {
		return super.persist(entity);
	}

	public QuotationConvocatoryProduct mergeEntity(QuotationConvocatoryProduct entity) {
		return super.merge(entity);
	}

	public void deleteEntity(QuotationConvocatoryProduct entity) {
		super.delete(entity);
	}

	public List<QuotationConvocatoryProduct> findAllEntity() {
		return super.findAll();
	}

	public QuotationConvocatoryProduct findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<QuotationConvocatoryProduct> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<QuotationConvocatoryProduct> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<QuotationConvocatoryProduct> entities) {
		super.deleteAll(entities);
	}

}
