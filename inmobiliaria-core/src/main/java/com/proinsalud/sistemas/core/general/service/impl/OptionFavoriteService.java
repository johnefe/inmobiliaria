package com.proinsalud.sistemas.core.general.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.IOptionFavoriteDao;
import com.proinsalud.sistemas.core.general.model.OptionFavorite;
import com.proinsalud.sistemas.core.general.service.IOptionFavoriteService;

@Repository(value = "optionFavoriteService")
public class OptionFavoriteService implements IOptionFavoriteService, Serializable {

	private static final long serialVersionUID = 1410737864883035571L;
	@Autowired(required = true)
	@Qualifier(value = "optionFavoriteDao")
	private IOptionFavoriteDao iOptionFavoriteDao;

	@Transactional
	public OptionFavorite persistEntity(OptionFavorite entity) {
		return iOptionFavoriteDao.persistEntity(entity);
	}

	@Transactional
	public OptionFavorite mergeEntity(OptionFavorite entity) {
		return iOptionFavoriteDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(OptionFavorite entity) {
		iOptionFavoriteDao.deleteEntity(entity);
	}

	@Transactional
	public List<OptionFavorite> findAllEntity() {
		return iOptionFavoriteDao.findAllEntity();
	}

	@Transactional
	public OptionFavorite findEntityById(Long id) {
		return iOptionFavoriteDao.findEntityById(id);
	}
	
	@Transactional
	public List<OptionFavorite> findAllEntityByIdUser(Long idUser){
		return iOptionFavoriteDao.findAllEntityByIdUser(idUser);
	}

}

