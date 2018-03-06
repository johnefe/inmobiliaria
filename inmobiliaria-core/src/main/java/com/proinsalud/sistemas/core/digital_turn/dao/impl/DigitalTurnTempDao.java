package com.proinsalud.sistemas.core.digital_turn.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IDigitalTurnTempDao;
import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurnTemp;
import com.proinsalud.sistemas.core.generic.GenericDao;
import com.proinsalud.sistemas.core.util.enums.StateTurnEnum;

@Repository(value = "digitalTurnTempDao")
public class DigitalTurnTempDao extends GenericDao<Long, DigitalTurnTemp> implements IDigitalTurnTempDao, Serializable {

	private static final long serialVersionUID = -26505130457175238L;

	public DigitalTurnTemp persistEntity(DigitalTurnTemp entity) {
		return super.persist(entity);
	}

	public DigitalTurnTemp mergeEntity(DigitalTurnTemp entity) {
		return super.merge(entity);
	}

	public void deleteEntity(DigitalTurnTemp entity) {
		super.delete(entity);
	}

	public List<DigitalTurnTemp> findAllEntity() {
		return super.findAll();
	}

	public DigitalTurnTemp findEntityById(Long id) {
		return super.findById(id);
	}

	public void persistEntity(List<DigitalTurnTemp> entities) {
		super.persistAll(entities);
	}

	public void mergeEntity(List<DigitalTurnTemp> entities) {
		super.mergeAll(entities);
	}

	public void deleteEntity(List<DigitalTurnTemp> entities) {
		super.deleteAll(entities);
	}

	public List<DigitalTurnTemp> findEndsTwentyRegisters(Long idPoint, String enAtencion, String llamando) {

		List<DigitalTurnTemp> digitalTurnTemps = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idPoint", idPoint);

		digitalTurnTemps = executeNamedQuery("DigitalTurnTemp.findEndsTwentyRegisters", params);
		return digitalTurnTemps;
	}

	public List<DigitalTurnTemp> findTurnByStateAttention(Long idPoint, String atendido) {

		List<DigitalTurnTemp> digitalTurn = new ArrayList<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("idPoint", idPoint);

		digitalTurn = executeNamedQuery("DigitalTurnTemp.findTurnByStateAttention", params);
		return digitalTurn;
	}

	public List<DigitalTurnTemp> findAllEntityByOrder() {
		List<DigitalTurnTemp> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		lst = executeNamedQuery("DigitalTurnTemp.findAllEntityByOrder", parametros);
		return lst;
	}

	public List<DigitalTurnTemp> finAllEntitybyPoint(Long idPoint, String state) {
		List<DigitalTurnTemp> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPoint", idPoint);
		parametros.put("state", state);
		lst = executeNamedQuery("DigitalTurnTemp.findAllEntityByPoint", parametros);
		return lst;
	}

	public List<List<DigitalTurnTemp>> findEntityCustomViewTV(Long idPoint) {
		List<List<DigitalTurnTemp>> lst = new ArrayList<>();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPoint", idPoint);
		parametros.put("limitsize", 6);
		String state = StateTurnEnum.Llamando.toString();
		String query = "SELECT p.* FROM digital_turn.digital_turn_temp p " + 
		"INNER JOIN digital_turn.digital_turn dt ON p.id_digital_turn = dt.id_digital_turn " + 
		"INNER JOIN digital_turn.point_service ps ON dt.id_point_service = ps.id_point_service "+ 
		"WHERE ps.id_point=:idPoint AND p.state='"+state+"' LIMIT :limitsize ";
		List<DigitalTurnTemp> lstLlamando = executeQuery(query, parametros);

		parametros.put("limitsize", 10);
		state = StateTurnEnum.En_Atencion.toString();
		query = "SELECT p.* FROM digital_turn.digital_turn_temp p " + 
				"INNER JOIN digital_turn.digital_turn dt ON p.id_digital_turn = dt.id_digital_turn " + 
				"INNER JOIN digital_turn.point_service ps ON dt.id_point_service = ps.id_point_service "+ 
				"WHERE ps.id_point=:idPoint AND p.state='"+state+"' ORDER BY p.hour_attention DESC LIMIT :limitsize ";
		List<DigitalTurnTemp> lstAtencion = executeQuery(query, parametros);

		parametros.put("limitsize", 6);
		state = StateTurnEnum.Atendido.toString();
		query = "SELECT p.* FROM digital_turn.digital_turn_temp p " + 
				"INNER JOIN digital_turn.digital_turn dt ON p.id_digital_turn = dt.id_digital_turn " + 
				"INNER JOIN digital_turn.point_service ps ON dt.id_point_service = ps.id_point_service "+ 
				"WHERE ps.id_point=:idPoint AND p.state='"+state+"' ORDER BY p.hour_finished_attention DESC LIMIT :limitsize ";
		List<DigitalTurnTemp> lstAtendido = executeQuery(query, parametros);

		lst.add(lstLlamando);
		lst.add(lstAtencion);
		lst.add(lstAtendido);
		return lst;
	}
}
