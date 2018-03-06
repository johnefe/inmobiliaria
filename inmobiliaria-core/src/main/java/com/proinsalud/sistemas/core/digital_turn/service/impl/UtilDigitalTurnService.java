package com.proinsalud.sistemas.core.digital_turn.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.proinsalud.sistemas.core.digital_turn.dao.IDigitalTurnDao;
import com.proinsalud.sistemas.core.digital_turn.dao.IDigitalTurnTempDao;
import com.proinsalud.sistemas.core.digital_turn.dao.IPointServiceDao;
import com.proinsalud.sistemas.core.digital_turn.dao.IPrefixPointDao;
import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurn;
import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurnTemp;
import com.proinsalud.sistemas.core.digital_turn.model.PointService;
import com.proinsalud.sistemas.core.digital_turn.model.PrefixPoint;
import com.proinsalud.sistemas.core.digital_turn.service.IUtilDigitalTurnService;
import com.proinsalud.sistemas.core.util.enums.StateTurnEnum;

/**
 * @author Mauricio Pinchao
 * @datetime 21/02/2018 - 8:05:14 a. m.
 *
 */
@Repository(value = "utilDigitalTurnService")
public class UtilDigitalTurnService implements IUtilDigitalTurnService, Serializable {

	private static final long serialVersionUID = 410088279857925836L;

	@Autowired(required = true)
	@Qualifier(value = "digitalTurnDao")
	private IDigitalTurnDao iDigitalTurnDao;
	
	@Autowired(required = true)
	@Qualifier(value = "prefixPointDao")
	private IPrefixPointDao iPrefixPointDao;
	
	@Autowired(required = true)
	@Qualifier(value = "digitalTurnTempDao")
	private IDigitalTurnTempDao iDigitalTurnTempDao;
	
	@Autowired(required = true)
	@Qualifier(value = "pointServiceDao")
	private IPointServiceDao iPointServiceDao;

	@Transactional
	public void saveDigitalTurn(DigitalTurn digitalTurn, PointService pointService, PrefixPoint prefixPoint) throws Exception {
		if(digitalTurn != null) {
			if(digitalTurn.getId() == null) {
				digitalTurn.setState(StateTurnEnum.En_Espera);
				digitalTurn.setHourTurn(new Date());
				digitalTurn.setPointService(pointService);
				int secuencia = prefixPoint.getSecuence() + 1;
				String codeTurn = prefixPoint.getPrefix().getPrefix() + secuencia;
				digitalTurn.setCodeTurn(codeTurn);
				digitalTurn.setIdService(pointService.getService().getId());
				
				//Se crea un digiturno temporal
				DigitalTurnTemp temp = new DigitalTurnTemp();
				temp.setCode(digitalTurn.getCodeTurn());
				temp.setHourRegistered(digitalTurn.getHourTurn());
				temp.setIdentification(digitalTurn.getIdentificationPerson());
				if(digitalTurn.getPerson() != null) {
					temp.setNamePerson(digitalTurn.getPerson().getNameCompleted());
				}
				temp.setService(digitalTurn.getPointService().getService().getNameService());
				temp.setState(digitalTurn.getState());
				temp.setDigitalTurn(digitalTurn);
				temp.setColor(prefixPoint.getPrefix().getColor());
				temp.setPrefixOrder(prefixPoint.getPrefix().getOrderPrefix());
				
				prefixPoint.setSecuence(secuencia);
				iDigitalTurnDao.persistEntity(digitalTurn);
				iDigitalTurnTempDao.persistEntity(temp);
				iPrefixPointDao.mergeEntity(prefixPoint);
			}
		}
	}

	@Transactional
	public void savePointService(List<PointService> lstPointService, List<PointService> lstTarget) throws Exception {
		List<PointService> newPSList = new ArrayList<>();
		for(PointService ps1 : lstTarget) {
			boolean isNew = true;
			for(PointService ps2: lstPointService) {
				if(ps1.getService().getId().equals(ps2.getService().getId())) {
					lstPointService.remove(ps2);
					isNew = false;
					break;
				}
			}
			if(isNew) {
				newPSList.add(ps1);
			}
		}
		if(!lstPointService.isEmpty()) {
			iPointServiceDao.deleteEntity(lstPointService);
		}
		if(!newPSList.isEmpty()) {
			iPointServiceDao.persistEntity(newPSList);
		}
	}
}
