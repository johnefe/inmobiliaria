package com.proinsalud.sistemas.core.security.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.security.dao.IOptionDao;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.util.comparators.GeneralComparator;
import com.proinsalud.sistemas.core.util.comparators.OptionComparator;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:48:08 a. m.
 *
 */
@Repository(value = "optionDao")
public class OptionDao extends GenericDao<Long, Option> implements IOptionDao, Serializable {

	private static final long serialVersionUID = -8463891397078835920L;

	public Option persistEntity(Option entity) {
		return super.persist(entity);
	}

	public Option mergeEntity(Option entity) {
		return super.merge(entity);
	}

	public void deleteEntity(Option entity) {
		super.delete(entity);
	}

	public List<Option> findAllEntity() {
		return super.findAll();
	}

	public Option findEntityById(Long id) {
		return super.findById(id);
	}

	@Override
	public List<Option> findByLevel(Integer level) {
		List<Option> lst = new ArrayList<Option>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("levelPos", level);
		lst = executeNamedQuery("Option.findByLevel", params);
		if (!lst.isEmpty()) {
			GeneralComparator.organized(OptionComparator.byName, lst, "getOptions");
		}
		return lst;
	}

}
