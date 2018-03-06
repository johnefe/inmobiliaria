package com.proinsalud.sistemas.core.general.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.general.dao.IDepartmentDao;
import com.proinsalud.sistemas.core.general.model.Department;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "departmentDao")
public class DepartmentDao extends GenericDao<Long, Department> implements IDepartmentDao, Serializable {

	private static final long serialVersionUID = -5846972536090012044L;

	public Department persistEntity(Department entity) {
		return super.persist(entity);
	}

	public Department mergeEntity(Department entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Department entity) {
		super.delete(entity);
	}

	public List<Department> findAllEntity() {
		return super.findAll();
	}

	public Department findEntityById(Long id) {
		return super.findById(id);
	}

}
