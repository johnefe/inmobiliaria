package com.proinsalud.sistemas.core.general.service;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.News;

public interface INewsService {

	public News persistEntity(News entity);
	
	public News mergeEntity(News entity);
	
	public void deleteEntity(News entity);
	
	public List<News> findAllEntity();
	
	public News findEntityById(Long id);
	
	public List<News> findAllOrderBy();
}
