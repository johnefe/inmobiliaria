package com.proinsalud.sistemas.core.warehouse.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.warehouse.dao.IMedicineDao;
import com.proinsalud.sistemas.core.warehouse.model.Medicine;
import com.proinsalud.sistemas.core.warehouse.service.IMedicineService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 2:47:10 p. m.
 *
 */
@Repository(value = "medicineService")
public class MedicineService implements IMedicineService, Serializable {

	private static final long serialVersionUID = 8712195307398914561L;
	@Autowired(required = true)
	@Qualifier(value = "medicineDao")
	private IMedicineDao iMedicineDao;

	@Transactional
	public Medicine persistEntity(Medicine entity) {
		return iMedicineDao.persistEntity(entity);
	}

	@Transactional
	public Medicine mergeEntity(Medicine entity) {
		return iMedicineDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Medicine entity) {
		iMedicineDao.deleteEntity(entity);
	}

	@Transactional
	public List<Medicine> findAllEntity() {
		return iMedicineDao.findAllEntity();
	}

	@Transactional
	public Medicine findEntityById(Long id) {
		return iMedicineDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<Medicine> entities) {
		iMedicineDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<Medicine> entities) {
		iMedicineDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<Medicine> entities) {
		iMedicineDao.deleteEntity(entities);
	}

}

