package com.proinsalud.sistemas.core.general.dao;

import java.util.List;

import com.proinsalud.sistemas.core.general.model.OptionFavorite;

public interface IOptionFavoriteDao {

	public OptionFavorite persistEntity(OptionFavorite entity);
	
	public OptionFavorite mergeEntity(OptionFavorite entity);
	
	public void deleteEntity(OptionFavorite entity);
	
	public List<OptionFavorite> findAllEntity();
	
	public OptionFavorite findEntityById(Long id);
	
	public List<OptionFavorite> findAllEntityByIdUser(Long idUser);
	
}
