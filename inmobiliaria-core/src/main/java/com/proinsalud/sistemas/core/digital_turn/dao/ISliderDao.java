package com.proinsalud.sistemas.core.digital_turn.dao;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.Slider;

public interface ISliderDao {

	public Slider persistEntity(Slider entity);
	
	public Slider mergeEntity(Slider entity);
	
	public void deleteEntity(Slider entity);
	
	public List<Slider> findAllEntity();
	
	public Slider findEntityById(Long id);
	
	public void persistEntity(List<Slider> entities);
	
	public void mergeEntity(List<Slider> entities);
	
	public void deleteEntity(List<Slider> entities);
	
}
