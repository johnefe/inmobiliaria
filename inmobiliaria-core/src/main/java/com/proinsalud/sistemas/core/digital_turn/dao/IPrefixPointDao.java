package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.PrefixPoint;

public interface IPrefixPointDao {

	public PrefixPoint persistEntity(PrefixPoint entity);
	
	public PrefixPoint mergeEntity(PrefixPoint entity);
	
	public void deleteEntity(PrefixPoint entity);
	
	public List<PrefixPoint> findAllEntity();
	
	public PrefixPoint findEntityById(Long id);
	
	public void persistEntity(List<PrefixPoint> entities);
	
	public void mergeEntity(List<PrefixPoint> entities);
	
	public void deleteEntity(List<PrefixPoint> entities);
	
	public PrefixPoint findEntityByPrefixPoint(Long idPrefix, Long idPoint);
	
	public List<PrefixPoint> findEntityByIdPoint(Long idPoint);
	
}
