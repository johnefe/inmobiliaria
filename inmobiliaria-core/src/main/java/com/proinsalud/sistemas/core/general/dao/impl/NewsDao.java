package com.proinsalud.sistemas.core.general.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.general.dao.INewsDao;
import com.proinsalud.sistemas.core.general.model.News;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "newsDao")
public class NewsDao extends GenericDao<Long, News> implements INewsDao, Serializable {

	private static final long serialVersionUID = -3371093501980711113L;

	public News persistEntity(News entity) {
		return super.persist(entity);
	}

	public News mergeEntity(News entity) {
		return super.merge(entity);
	}

	public void deleteEntity(News entity) {
		super.delete(entity);
	}

	public List<News> findAllEntity() {
		return super.findAll();
	}

	public News findEntityById(Long id) {
		return super.findById(id);
	}

	public List<News> findAllOrderBy() {
		List<News> listNews = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		listNews = executeNamedQuery("News.findAllOrderBy", params);
		if(!listNews.isEmpty()) {
			News news = listNews.get(0);
			Hibernate.initialize(news);
		}
		return listNews;
	}
}
