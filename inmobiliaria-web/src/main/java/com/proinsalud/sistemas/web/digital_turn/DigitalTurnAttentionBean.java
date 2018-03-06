package com.proinsalud.sistemas.web.digital_turn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurn;
import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurnTemp;
import com.proinsalud.sistemas.core.digital_turn.model.Module;
import com.proinsalud.sistemas.core.digital_turn.model.ModuleUser;
import com.proinsalud.sistemas.core.digital_turn.model.Point;
import com.proinsalud.sistemas.core.digital_turn.service.IDigitalTurnService;
import com.proinsalud.sistemas.core.digital_turn.service.IDigitalTurnTempService;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleUserService;
import com.proinsalud.sistemas.core.util.enums.StateTurnEnum;
import com.proinsalud.sistemas.core.util.enums.TypeModuleEnum;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Mauricio Pinchao
 * @datetime 13/02/2018 - 9:55:22 a. m.
 *
 */
@ManagedBean
@ViewScoped
public class DigitalTurnAttentionBean implements Serializable {

	private static final long serialVersionUID = -7037729354412412808L;
	
	private static final Log LOG = App.getLogger(DigitalTurnAttentionBean.class);
	
	@Autowired
	private IDigitalTurnService iDigitalTurnService;
	@Autowired
	private IDigitalTurnTempService iDigitalTurnTempService;
	@Autowired
	private IModuleUserService iModuleUserService;
	
	private List<DigitalTurn> digitalTurnList;
	private List<DigitalTurnTemp> digitalTurnTempList;
	private List<ModuleUser> moduleUserList;
	
	private List<DigitalTurnTemp> listAltaPrioridad;
	private List<DigitalTurnTemp> listBajaPrioridad;
	private List<DigitalTurnTemp> listNormal;
	
	private DigitalTurn digitalTurn;
	private DigitalTurnTemp digitalTurnTemp = null;
	private Point point;
	private ModuleUser moduleUser;
	private Module module = null;
	
	private Date hourInitAttention;
	private Date hourFinishAttention;
	private String observation = "";
	private boolean activate = false;
	private boolean activateButtons;
	private boolean showInfo = false;
	private Long lastUpdate;
	
	public DigitalTurnAttentionBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		//digitalTurnTempList = iDigitalTurnTempService.findAllEntityByOrder();
		moduleUserList = iModuleUserService.findAllEntityByUserTypeModule(App.getUser().getId(), TypeModuleEnum.ModuloAtencion.toString());
		digitalTurnList = iDigitalTurnService.findAllEntity();
		digitalTurnTemp = null;
		moduleUser = null;
	}
	
	/**
	 * Este metodo carga los turnos temporales por punto.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:18:34 a. m.
	 *
	 * @param mUser - ModuleUser seleccionado
	 */
	public void loadTempsByPoint(ModuleUser mUser) {
		try {
			moduleUser = mUser;
			if(moduleUser != null) {
				point = moduleUser.getModule().getPoint();
				digitalTurnTempList = iDigitalTurnTempService.findAllEntityByPoint(point.getId(), StateTurnEnum.Atendido.toString());
				sortTempsByState();
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	public void sortTempsByState() {
		if(digitalTurnTempList != null || !digitalTurnTempList.isEmpty()) {
			listAltaPrioridad = new ArrayList<>();
			listBajaPrioridad = new ArrayList<>();
			listNormal = new ArrayList<>();
			for(DigitalTurnTemp d : digitalTurnTempList) {
				switch (d.getPrefixOrder()) {
				case 1:
					listAltaPrioridad.add(d);
					break;
				case 2:
					listBajaPrioridad.add(d);
					break;
				case 3:
					listNormal.add(d);
					break;
				}
			}
		}
			
	}
	
	/**
	 * Este método se encarga de enviar el nuevo valor cuando
	 * se modifica un digiturno.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:19:30 a. m.
	 *
	 */
	public void actualizarDate() {
		Refresh.setValue(point.getId(), System.currentTimeMillis());
	}

	/**
	 * Este método se encarga de actualizar la fecha cuando haya un nuevo registro.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:19:10 a. m.
	 *
	 */
	public void updateDate() {
		Long val = Refresh.getValue(point.getId());
		//System.out.println(point.getId() + " : " + lastUpdate + " = " + val);
		if(val == null) {
			actualizarDate();
			val = Refresh.getValue(point.getId());
		}
		if (lastUpdate != val) {
			lastUpdate = val;
			digitalTurnTempList = iDigitalTurnTempService.findAllEntityByPoint(point.getId(), StateTurnEnum.Atendido.toString());
			RequestContext.getCurrentInstance().update("pnlListTurn");
		}
	}
	
	/**
	 * Este método se encarga de mostrar la información del turno seleccionado. 
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:20:20 a. m.
	 *
	 * @param idTurn - Recibe el id del turno seleccionado.
	 */
	public void loadInfoTurn(DigitalTurnTemp dTurnTemp) {
		try {
			digitalTurnTemp = dTurnTemp;
			if(digitalTurnTemp != null) {
				showInfo = true;
				if(digitalTurnTemp.getState().equals(StateTurnEnum.En_Espera) || digitalTurnTemp.getState().equals(StateTurnEnum.Atendido)) {
					activate = false;
				} else {
					activate = true;
				}
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este método cambia el estado del turno cuando el usuario lo cancelada.
	 * El estado vuelve a ser "En_Espera"
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:21:23 a. m.
	 *
	 */
	public void cancelTurn() {
		try {
			if(digitalTurnTemp != null) {
				digitalTurnTemp = iDigitalTurnTempService.findEntityById(digitalTurnTemp.getId());
				if(!digitalTurnTemp.getState().equals(StateTurnEnum.En_Espera)) {
					digitalTurnTemp.setState(StateTurnEnum.En_Espera);	
					digitalTurn = iDigitalTurnService.findEntityById(digitalTurnTemp.getDigitalTurn().getId());
					digitalTurn.setModuleUser(null);
					iDigitalTurnTempService.mergeEntity(digitalTurnTemp);
					iDigitalTurnService.mergeEntity(digitalTurn);
					digitalTurnTempList = iDigitalTurnTempService.findAllEntityByPoint(point.getId(), StateTurnEnum.Atendido.toString());
				}
			}
			digitalTurnTemp = null;
			activate = false;
			module = null;
			showInfo = false;
			actualizarDate();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este método se encarga de cambiar el estado del turno una vez esté en su respectivo
	 * modulo para su atención, además le asigna la hora que empieza la atención. El estado de éste
	 * pasa a ser "En_Atención".
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:21:56 a. m.
	 *
	 */
	public void startAttentionTurn() {
		try {
			if(digitalTurnTemp != null) {
				digitalTurnTemp = iDigitalTurnTempService.findEntityById(digitalTurnTemp.getId());
				if(digitalTurnTemp.getState().equals(StateTurnEnum.Llamando)) {
					hourInitAttention = new Date();
					digitalTurnTemp.setState(StateTurnEnum.En_Atencion);
					digitalTurnTemp.setHourAttention(hourInitAttention);
				}
				iDigitalTurnTempService.mergeEntity(digitalTurnTemp);
				digitalTurnTempList = iDigitalTurnTempService.findAllEntityByPoint(point.getId(), StateTurnEnum.Atendido.toString());
				actualizarDate();
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Este método se encarga de llamar al usuario del turno seleccionado. El estado de éste pasa
	 * a ser "Llamando".
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:22:56 a. m.
	 *
	 */
	public void callingTurn() {
		try {
			if(digitalTurnTemp != null) {
				digitalTurnTemp = iDigitalTurnTempService.findEntityById(digitalTurnTemp.getId());
				if(digitalTurnTemp.getState().equals(StateTurnEnum.En_Espera)) {
					digitalTurnTemp.setState(StateTurnEnum.Llamando);
					digitalTurn = iDigitalTurnService.findEntityById(digitalTurnTemp.getDigitalTurn().getId());
					digitalTurn.setModuleUser(moduleUser);
					iDigitalTurnTempService.mergeEntity(digitalTurnTemp);
					iDigitalTurnService.mergeEntity(digitalTurn);
					activate = true;
					module = moduleUser.getModule();
					digitalTurnTempList = iDigitalTurnTempService.findAllEntityByPoint(point.getId(), StateTurnEnum.Atendido.toString());
					actualizarDate();
				}
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este método se encarga de cambiar el estado al turno. Este pasa a ser "Atendido" y ocurre cuando
	 * finaliza la anteción. Además se asigna la hora de finalización de la atención.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:23:50 a. m.
	 *
	 */
	public void finishAttentionTurn() {
		try {
			if(digitalTurnTemp != null) {
				digitalTurnTemp = iDigitalTurnTempService.findEntityById(digitalTurnTemp.getId());
				if(digitalTurnTemp.getState().equals(StateTurnEnum.En_Atencion)) {
					hourFinishAttention = new Date();
					digitalTurnTemp.setHourFinishedAttention(hourFinishAttention);
					digitalTurnTemp.setObservation(observation);
					digitalTurnTemp.setState(StateTurnEnum.Atendido);
					digitalTurn = iDigitalTurnService.findEntityById(digitalTurnTemp.getDigitalTurn().getId());
					if(digitalTurn != null) {
						digitalTurn.setHourAttention(hourInitAttention);
						digitalTurn.setHourFinishedAttention(hourFinishAttention);
						digitalTurn.setObservation(observation);
						digitalTurn.setState(StateTurnEnum.Atendido);
						iDigitalTurnService.mergeEntity(digitalTurn);
					}
					iDigitalTurnTempService.mergeEntity(digitalTurnTemp);
				}
			}
			digitalTurnTemp = null;
			showInfo = false;
			digitalTurnTempList = iDigitalTurnTempService.findAllEntityByPoint(point.getId(), StateTurnEnum.Atendido.toString());
			module = null;
			actualizarDate();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este método se encarga de eliminar de la lista temporal, el turno que ya fue atendido y 
	 * cuyo estado sea "Atendido".
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 10:24:51 a. m.
	 *
	 * @param tempDelete - DigiturnoTemp que va a ser eliminado.
	 * @return
	 */
	public List<DigitalTurnTemp> deleteTempFromList(DigitalTurnTemp tempDelete) {
		boolean eliminado = false;
		List<DigitalTurnTemp> lst = new ArrayList<>();
		while (eliminado == false) {
			for (DigitalTurnTemp d : digitalTurnTempList) {
				if (d.getId().equals(tempDelete.getId())) {
					digitalTurnTempList.remove(d);
					eliminado = true;
				}
			}
			lst = digitalTurnTempList;
		}
		return lst;
	}

	public List<DigitalTurn> getDigitalTurnList() {
		return digitalTurnList;
	}

	public List<DigitalTurnTemp> getDigitalTurnTempList() {
		return digitalTurnTempList;
	}

	public List<ModuleUser> getModuleUserList() {
		return moduleUserList;
	}

	public List<DigitalTurnTemp> getListAltaPrioridad() {
		return listAltaPrioridad;
	}

	public List<DigitalTurnTemp> getListBajaPrioridad() {
		return listBajaPrioridad;
	}

	public List<DigitalTurnTemp> getListNormal() {
		return listNormal;
	}

	public DigitalTurn getDigitalTurn() {
		return digitalTurn;
	}

	public DigitalTurnTemp getDigitalTurnTemp() {
		return digitalTurnTemp;
	}

	public Point getPoint() {
		return point;
	}

	public ModuleUser getModuleUser() {
		return moduleUser;
	}

	public Module getModule() {
		return module;
	}

	public void setDigitalTurnList(List<DigitalTurn> digitalTurnList) {
		this.digitalTurnList = digitalTurnList;
	}

	public void setDigitalTurnTempList(List<DigitalTurnTemp> digitalTurnTempList) {
		this.digitalTurnTempList = digitalTurnTempList;
	}

	public void setModuleUserList(List<ModuleUser> moduleUserList) {
		this.moduleUserList = moduleUserList;
	}

	public void setDigitalTurn(DigitalTurn digitalTurn) {
		this.digitalTurn = digitalTurn;
	}

	public void setDigitalTurnTemp(DigitalTurnTemp digitalTurnTemp) {
		this.digitalTurnTemp = digitalTurnTemp;
	}

	public Date getHourInitAttention() {
		return hourInitAttention;
	}

	public Date getHourFinishAttention() {
		return hourFinishAttention;
	}

	public String getObservation() {
		return observation;
	}

	public boolean isActivate() {
		return activate;
	}

	public boolean isActivateButtons() {
		return activateButtons;
	}

	public boolean isShowInfo() {
		return showInfo;
	}

	public void setHourInitAttention(Date hourInitAttention) {
		this.hourInitAttention = hourInitAttention;
	}

	public void setHourFinishAttention(Date hourFinishAttention) {
		this.hourFinishAttention = hourFinishAttention;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}
