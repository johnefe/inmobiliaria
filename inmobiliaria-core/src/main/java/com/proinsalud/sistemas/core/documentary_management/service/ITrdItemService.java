package com.proinsalud.sistemas.core.documentary_management.service;

import java.util.List;

import com.proinsalud.sistemas.core.documentary_management.model.TrdItem;

public interface ITrdItemService {

	public TrdItem persistEntity(TrdItem entity);

	public TrdItem mergeEntity(TrdItem entity);

	public void deleteEntity(TrdItem entity);

	public List<TrdItem> findAllEntity();

	public TrdItem findEntityById(Long id);

	public void persistEntity(List<TrdItem> entities);

	public void mergeEntity(List<TrdItem> entities);

	public void deleteEntity(List<TrdItem> entities);

	public List<TrdItem> findAllEntityByLevelTrdItem(Integer level);

}
