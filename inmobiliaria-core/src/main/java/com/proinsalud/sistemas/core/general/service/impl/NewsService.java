package com.proinsalud.sistemas.core.general.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.INewsDao;
import com.proinsalud.sistemas.core.general.model.News;
import com.proinsalud.sistemas.core.general.service.INewsService;

@Repository(value = "newsService")
public class NewsService implements INewsService, Serializable {

	private static final long serialVersionUID = 576764018396786898L;
	@Autowired(required = true)
	@Qualifier(value = "newsDao")
	private INewsDao iNewsDao;

	@Transactional
	public News persistEntity(News entity) {
		return iNewsDao.persistEntity(entity);
	}

	@Transactional
	public News mergeEntity(News entity) {
		return iNewsDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(News entity) {
		iNewsDao.deleteEntity(entity);
	}

	@Transactional
	public List<News> findAllEntity() {
		return iNewsDao.findAllEntity();
	}

	@Transactional
	public News findEntityById(Long id) {
		return iNewsDao.findEntityById(id);
	}

	@Transactional
	public List<News> findAllOrderBy() {
		return iNewsDao.findAllOrderBy();
	}
}

