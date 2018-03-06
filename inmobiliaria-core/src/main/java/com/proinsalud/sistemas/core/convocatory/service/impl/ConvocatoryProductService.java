package com.proinsalud.sistemas.core.convocatory.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.convocatory.dao.IConvocatoryProductDao;
import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.service.IConvocatoryProductService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:03:33 a. m.
 *
 */
@Repository(value = "convocatoryProductService")
public class ConvocatoryProductService implements IConvocatoryProductService, Serializable {

	private static final long serialVersionUID = 7673528864646272137L;
	@Autowired(required = true)
	@Qualifier(value = "convocatoryProductDao")
	private IConvocatoryProductDao iConvocatoryProductDao;

	@Transactional
	public ConvocatoryProduct persistEntity(ConvocatoryProduct entity) {
		return iConvocatoryProductDao.persistEntity(entity);
	}

	@Transactional
	public ConvocatoryProduct mergeEntity(ConvocatoryProduct entity) {
		return iConvocatoryProductDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(ConvocatoryProduct entity) {
		iConvocatoryProductDao.deleteEntity(entity);
	}

	@Transactional
	public List<ConvocatoryProduct> findAllEntity() {
		return iConvocatoryProductDao.findAllEntity();
	}

	@Transactional
	public ConvocatoryProduct findEntityById(Long id) {
		return iConvocatoryProductDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<ConvocatoryProduct> entities) {
		iConvocatoryProductDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<ConvocatoryProduct> entities) {
		iConvocatoryProductDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<ConvocatoryProduct> entities) {
		iConvocatoryProductDao.deleteEntity(entities);
	}

}

