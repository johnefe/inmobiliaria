package com.proinsalud.sistemas.core.documentary_management.service;

import java.util.List;

import com.proinsalud.sistemas.core.documentary_management.model.Trd;

public interface ITrdService {

	public Trd persistEntity(Trd entity);

	public Trd mergeEntity(Trd entity);

	public void deleteEntity(Trd entity);

	public List<Trd> findAllEntity();

	public Trd findEntityById(Long id);

	public void persistEntity(List<Trd> entities);

	public void mergeEntity(List<Trd> entities);

	public void deleteEntity(List<Trd> entities);

}
