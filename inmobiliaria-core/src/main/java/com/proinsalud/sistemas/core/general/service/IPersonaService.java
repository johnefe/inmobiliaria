package com.proinsalud.sistemas.core.general.service;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.Person;

public interface IPersonaService {

	public Person persistEntity(Person entity);

	public Person mergeEntity(Person entity);

	public void deleteEntity(Person entity);

	public List<Person> findAllEntity();

	public Person findEntityById(Long id);

	public void persistEntity(List<Person> entities);

	public void mergeEntity(List<Person> entities);

	public void deleteEntity(List<Person> entities);

	public List<Person> findEntityByName(String nombre);

	public List<Person> findAllEntityWithUsers();
	
	public Person findEntityByIdentification(String identification);
}
