package com.proinsalud.sistemas.core.human_talent.dao;

import java.util.List;

import com.proinsalud.sistemas.core.human_talent.model.Employee;

public interface IEmployeeDao {

	public Employee persistEntity(Employee entity);
	
	public Employee mergeEntity(Employee entity);
	
	public void deleteEntity(Employee entity);
	
	public List<Employee> findAllEntity();
	
	public Employee findEntityById(Long id);
	
	public void persistEntity(List<Employee> entities);
	
	public void mergeEntity(List<Employee> entities);
	
	public void deleteEntity(List<Employee> entities);
	
	public Employee findEntityByIdPerson(Long idPerson);
	
}
