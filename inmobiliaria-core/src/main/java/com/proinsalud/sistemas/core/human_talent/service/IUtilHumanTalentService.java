package com.proinsalud.sistemas.core.human_talent.service;

import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.human_talent.model.Employee;

/**
 * @author Mauricio Pinchao
 * @datetime 3/01/2018 - 11:30:03 a. m.
 *
 */
public interface IUtilHumanTalentService {

	public void updateEmployee(Person person, Employee employee) throws Exception;
}
