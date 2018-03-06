package com.proinsalud.sistemas.core.warehouse.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.warehouse.dao.IMedicineDao;
import com.proinsalud.sistemas.core.warehouse.model.Medicine;
import com.proinsalud.sistemas.core.generic.GenericDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 2:47:10 p. m.
 *
 */
@Repository(value = "medicineDao")
public class MedicineDao extends GenericDao<Long, Medicine> implements IMedicineDao, Serializable {

	private static final long serialVersionUID = 3367651623324266652L;

	public Medicine persistEntity(Medicine entity) {
		return super.persist(entity);
	}

	public Medicine mergeEntity(Medicine entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Medicine entity) {
		super.delete(entity);
	}

	public List<Medicine> findAllEntity() {
		return super.findAll();
	}

	public Medicine findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Medicine> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Medicine> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Medicine> entities) {
		super.deleteAll(entities);
	}

}
