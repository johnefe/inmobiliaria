package com.proinsalud.sistemas.core.human_talent.service;

import java.util.List;

import com.proinsalud.sistemas.core.human_talent.model.Employee;

public interface IEmployeeService {

	public Employee persistEntity(Employee entity);
	
	public Employee mergeEntity(Employee entity);
	
	public void deleteEntity(Employee entity);
	
	public List<Employee> findAllEntity();
	
	public Employee findEntityById(Long id);
	
	public Employee findEntityByIdPerson(Long idPerson);
	
}
