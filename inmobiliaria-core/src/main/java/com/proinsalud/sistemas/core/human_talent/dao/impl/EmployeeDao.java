package com.proinsalud.sistemas.core.human_talent.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.human_talent.dao.IEmployeeDao;
import com.proinsalud.sistemas.core.human_talent.model.Employee;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "employeeDao")
public class EmployeeDao extends GenericDao<Long, Employee> implements IEmployeeDao, Serializable {

	private static final long serialVersionUID = 3223886069079907374L;

	public Employee persistEntity(Employee entity) {
		return super.persist(entity);
	}

	public Employee mergeEntity(Employee entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Employee entity) {
		super.delete(entity);
	}

	public List<Employee> findAllEntity() {
		return super.findAll();
	}

	public Employee findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<Employee> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<Employee> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<Employee> entities) {
		super.deleteAll(entities);
	}

	public Employee findEntityByIdPerson(Long idPerson) {
		List<Employee> lst = new ArrayList<Employee>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPerson", idPerson);
		lst = executeNamedQuery("Employee.findEntityByIdPerson", parametros);
		
		if(!lst.isEmpty()) {
			Employee emplo = lst.get(0);
			Hibernate.initialize(emplo);
			return emplo;
		}
		return null;
	}

}
