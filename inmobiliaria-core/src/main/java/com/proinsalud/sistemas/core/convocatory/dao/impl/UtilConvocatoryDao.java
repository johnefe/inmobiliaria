package com.proinsalud.sistemas.core.convocatory.dao.impl;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.convocatory.dao.IUtilConvocatoryDao;
import com.proinsalud.sistemas.core.generic.GenericSimpleDao;

/**
 * 
 * @author Andres Santacruz
 * @datetime 19/01/2018 - 9:46:20 a. m.
 *
 */
@Repository(value = "utilConvocatoryDao")
public class UtilConvocatoryDao extends GenericSimpleDao implements IUtilConvocatoryDao, Serializable {

	private static final long serialVersionUID = 8720433118546257721L;

	public void persistStateTypeConvocatory(String type) throws Exception {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		String query = "ALTER TYPE convocatory.state_convocatory ADD VALUE '" + type + "'";
		executeNamedQueryUpdate(query, parametros);
	}
	
	public void getAllEnum() {
//		String query = "SELECT enumlabel AS \"name\" FROM pg_enum WHERE enumtypid = 'enum_category'::regtype ORDER BY name;";
//		query = "DELETE FROM pg_enum en WHERE en.enumtypid=134769 AND en.enumlabel='NUEVO VALOR';\r\n" + 
//				"SELECT enumsortorder, enumtypid , enumlabel AS \"name\" FROM pg_enum WHERE enumtypid = 'convocatory.state_convocatory'::regtype ORDER BY name;";
	}

}
