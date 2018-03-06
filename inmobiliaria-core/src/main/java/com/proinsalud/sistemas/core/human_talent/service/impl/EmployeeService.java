package com.proinsalud.sistemas.core.human_talent.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.human_talent.dao.IEmployeeDao;
import com.proinsalud.sistemas.core.human_talent.model.Employee;
import com.proinsalud.sistemas.core.human_talent.service.IEmployeeService;

@Repository(value = "employeeService")
public class EmployeeService implements IEmployeeService, Serializable {

	private static final long serialVersionUID = 5495809678423556403L;
	@Autowired(required = true)
	@Qualifier(value = "employeeDao")
	private IEmployeeDao iEmployeeDao;

	@Transactional
	public Employee persistEntity(Employee entity) {
		return iEmployeeDao.persistEntity(entity);
	}

	@Transactional
	public Employee mergeEntity(Employee entity) {
		return iEmployeeDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Employee entity) {
		iEmployeeDao.deleteEntity(entity);
	}

	@Transactional
	public List<Employee> findAllEntity() {
		return iEmployeeDao.findAllEntity();
	}

	@Transactional
	public Employee findEntityById(Long id) {
		return iEmployeeDao.findEntityById(id);
	}

	@Transactional
	public Employee findEntityByIdPerson(Long idPerson) {
		return iEmployeeDao.findEntityByIdPerson(idPerson);
	}

}

