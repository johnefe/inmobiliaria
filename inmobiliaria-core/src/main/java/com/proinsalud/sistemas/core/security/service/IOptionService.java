package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.Option;

public interface IOptionService {

	public Option persistEntity(Option entity);

	public Option mergeEntity(Option entity);

	public void deleteEntity(Option entity);

	public List<Option> findAllEntity();

	public Option findEntityById(Long id);
	
	public List<Option> findByLevel(Integer position);
}
