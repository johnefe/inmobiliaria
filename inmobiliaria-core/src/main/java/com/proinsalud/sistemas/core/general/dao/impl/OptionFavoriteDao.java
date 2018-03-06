package com.proinsalud.sistemas.core.general.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.general.dao.IOptionFavoriteDao;
import com.proinsalud.sistemas.core.general.model.OptionFavorite;
import com.proinsalud.sistemas.core.generic.GenericDao;

@Repository(value = "optionFavoriteDao")
public class OptionFavoriteDao extends GenericDao<Long, OptionFavorite> implements IOptionFavoriteDao, Serializable {

	private static final long serialVersionUID = -5822167291816024181L;

	public OptionFavorite persistEntity(OptionFavorite entity) {
		return super.persist(entity);
	}

	public OptionFavorite mergeEntity(OptionFavorite entity) {
		return super.merge(entity);
	}

	public void deleteEntity(OptionFavorite entity) {
		super.delete(entity);
	}

	public List<OptionFavorite> findAllEntity() {
		return super.findAll();
	}

	public OptionFavorite findEntityById(Long id) {
		return super.findById(id);
	}

	public List<OptionFavorite> findAllEntityByIdUser(Long idUser) {
		List<OptionFavorite> lst = new ArrayList<OptionFavorite>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idUser", idUser);
		lst = executeNamedQuery("OptionFavorite.findAllEntityByIdUser", parametros);
		return lst;
	}

}
