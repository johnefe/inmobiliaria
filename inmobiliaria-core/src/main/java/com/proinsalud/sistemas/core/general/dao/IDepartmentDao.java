package com.proinsalud.sistemas.core.general.dao;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.Department;

public interface IDepartmentDao {

	public Department persistEntity(Department entity);
	
	public Department mergeEntity(Department entity);
	
	public void deleteEntity(Department entity);
	
	public List<Department> findAllEntity();
	
	public Department findEntityById(Long id);
	
}
