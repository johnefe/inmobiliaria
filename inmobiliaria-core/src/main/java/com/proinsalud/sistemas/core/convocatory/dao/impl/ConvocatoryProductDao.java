package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IConvocatoryProductDao;
import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:02:45 a. m.
 *
 */
@Repository(value = "convocatoryProductDao")
public class ConvocatoryProductDao extends GenericDao<Long, ConvocatoryProduct> implements IConvocatoryProductDao, Serializable {

	private static final long serialVersionUID = 9118723926064733748L;

	public ConvocatoryProduct persistEntity(ConvocatoryProduct entity) {
		return super.persist(entity);
	}

	public ConvocatoryProduct mergeEntity(ConvocatoryProduct entity) {
		return super.merge(entity);
	}

	public void deleteEntity(ConvocatoryProduct entity) {
		super.delete(entity);
	}

	public List<ConvocatoryProduct> findAllEntity() {
		return super.findAll();
	}

	public ConvocatoryProduct findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<ConvocatoryProduct> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<ConvocatoryProduct> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<ConvocatoryProduct> entities) {
		super.deleteAll(entities);
	}

}
