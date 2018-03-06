package com.proinsalud.sistemas.core.warehouse.dao;

import java.util.List;

import com.proinsalud.sistemas.core.warehouse.model.Medicine;

public interface IMedicineDao {

	public Medicine persistEntity(Medicine entity);
	
	public Medicine mergeEntity(Medicine entity);
	
	public void deleteEntity(Medicine entity);
	
	public List<Medicine> findAllEntity();
	
	public Medicine findEntityById(Long id);
	
	public void persistEntity(List<Medicine> entities);
	
	public void mergeEntity(List<Medicine> entities);
	
	public void deleteEntity(List<Medicine> entities);
	
}
