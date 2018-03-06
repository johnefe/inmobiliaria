package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IQuotationDao;
import com.proinsalud.sistemas.core.convocatory.model.Quotation;
import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.util.enums.StateConvocatoryEnum;

/**
 * 
 * @author Andres Santacruz
 * @datetime 21/12/2017 - 10:03:18 a. m.
 *
 */
@Repository(value = "quotationDao")
public class QuotationDao extends GenericDao<Long, Quotation> implements IQuotationDao, Serializable {

	private static final long serialVersionUID = 3212628620521202341L;

	public Quotation persistEntity(Quotation entity) {
		return super.persist(entity);
	}

	public Quotation mergeEntity(Quotation entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Quotation entity) {
		super.delete(entity);
	}

	public List<Quotation> findAllEntity() {
		return super.findAll();
	}

	public Quotation findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Quotation> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Quotation> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Quotation> entities) {
		super.deleteAll(entities);
	}

	public List<Quotation> findEntityByIdProvider(Long idProvider) {
		List<Quotation> lst = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idProvider", idProvider);
		params.put("state",StateConvocatoryEnum.Publicada);
		lst = executeNamedQuery("Quotation.findEntityByIdProvider", params);
		return lst.isEmpty() ? null : lst;
	}

}
