package com.proinsalud.sistemas.core.general.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.IDepartmentDao;
import com.proinsalud.sistemas.core.general.model.Department;
import com.proinsalud.sistemas.core.general.service.IDepartmentService;

@Repository(value = "departmentService")
public class DepartmentService implements IDepartmentService, Serializable {

	private static final long serialVersionUID = 5121428323731090234L;
	@Autowired(required = true)
	@Qualifier(value = "departmentDao")
	private IDepartmentDao iDepartmentDao;

	@Transactional
	public Department persistEntity(Department entity) {
		return iDepartmentDao.persistEntity(entity);
	}

	@Transactional
	public Department mergeEntity(Department entity) {
		return iDepartmentDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Department entity) {
		iDepartmentDao.deleteEntity(entity);
	}

	@Transactional
	public List<Department> findAllEntity() {
		return iDepartmentDao.findAllEntity();
	}

	@Transactional
	public Department findEntityById(Long id) {
		return iDepartmentDao.findEntityById(id);
	}

}

