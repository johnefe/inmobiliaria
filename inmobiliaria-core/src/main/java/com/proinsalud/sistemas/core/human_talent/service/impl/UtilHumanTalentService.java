package com.proinsalud.sistemas.core.human_talent.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.general.dao.IPersonaDao;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.human_talent.dao.IEmployeeDao;
import com.proinsalud.sistemas.core.human_talent.model.Employee;
import com.proinsalud.sistemas.core.human_talent.service.IUtilHumanTalentService;

/**
 * @author Mauricio Pinchao
 * @datetime 3/01/2018 - 11:33:23 a. m.
 *
 */
@Repository(value = "UtilHumanTalentService")
public class UtilHumanTalentService implements IUtilHumanTalentService, Serializable {

	private static final long serialVersionUID = -2618044776309108391L;

	@Autowired(required = true)
	@Qualifier(value = "employeeDao")
	private IEmployeeDao iEmployeeDao;
	
	@Autowired(required = true)
	@Qualifier(value = "personaDao")
	private IPersonaDao iPersonaDao;
	
	@Transactional
	public void updateEmployee(Person person, Employee employee) throws Exception {
		if(person != null) {
			iPersonaDao.mergeEntity(person);
		}
		if(employee != null) {
			iEmployeeDao.mergeEntity(employee);
		}
	}

}
