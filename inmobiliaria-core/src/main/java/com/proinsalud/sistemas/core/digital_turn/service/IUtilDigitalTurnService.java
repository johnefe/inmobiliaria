package com.proinsalud.sistemas.core.digital_turn.service;

import java.util.List;

import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurn;
import com.proinsalud.sistemas.core.digital_turn.model.PointService;
import com.proinsalud.sistemas.core.digital_turn.model.PrefixPoint;

/**
 * @author Mauricio Pinchao
 * @datetime 21/02/2018 - 8:02:32 a. m.
 *
 */
public interface IUtilDigitalTurnService {

	public void saveDigitalTurn(DigitalTurn digitalTurn, PointService pointService, PrefixPoint prefixPoint) throws Exception;
	
	public void savePointService(List<PointService> lstPointService, List<PointService> lstTarget) throws Exception;
}
