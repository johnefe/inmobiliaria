package com.proinsalud.sistemas.web.digital_turn;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurnTemp;
import com.proinsalud.sistemas.core.digital_turn.model.ModuleUser;
import com.proinsalud.sistemas.core.digital_turn.model.Point;
import com.proinsalud.sistemas.core.digital_turn.service.IDigitalTurnTempService;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleUserService;
import com.proinsalud.sistemas.core.util.enums.TypeModuleEnum;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Ing. Jhon Frey Diaz
 * @datetime 15/02/2018 - 8:19:01 a. m.
 */
@ManagedBean
@ViewScoped
public class DigitalTurnTvBean implements Serializable {

	private static final long serialVersionUID = -668293949765394444L;
	private static final Log LOG = App.getLogger(DigitalTurnBean.class);

	@Autowired
	private IDigitalTurnTempService iDigitalTurnTempService;

	@Autowired
	private IModuleUserService iModuleUserService;

	private List<DigitalTurnTemp> turnosEnAtencion;
	private List<DigitalTurnTemp> turnosLlamando;
	private List<DigitalTurnTemp> turnosAtendidos;
	private List<ModuleUser> modulesUser;
	private ModuleUser moduleUser;
	private Point point;
	private Long lastUpdate;
	private int sizeDivLlamando;
	private int sizeDivAtendidos;
	// private int tamaño;

	public DigitalTurnTvBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		modulesUser = iModuleUserService.findAllEntityByUserTypeModule(App.getUser().getId(), TypeModuleEnum.ModuloTv.toString());
		moduleUser = modulesUser.size() == 1 ? modulesUser.get(0) : null;
		
	}

	public void loadForm(ModuleUser moduloPunto) {
		try {
			this.moduleUser = moduloPunto;
			point = moduleUser.getModule().getPoint();
			loadTurnsTemporal();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadTurnsTemporal() {
		try {
			List<List<DigitalTurnTemp>> lstComplete = iDigitalTurnTempService.findEntityCustomViewTV(point.getId());
			turnosLlamando = lstComplete.get(0);
			turnosEnAtencion = lstComplete.get(1);
			turnosAtendidos = lstComplete.get(2);
			sizeDivLlamando = sizeDive(turnosLlamando.size());
			sizeDivAtendidos = sizeDive(turnosAtendidos.size());
			System.out.println("turnos"+turnosAtendidos);
			
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void actualizarDate() {
		Refresh.setValue(point.getId(), System.currentTimeMillis());
	}

	public void updateDate() {
		Long val = Refresh.getValue(point.getId());
		if (val == null) {
			actualizarDate();
			val = Refresh.getValue(point.getId());
			
		}
		if (lastUpdate != val) {
			lastUpdate = val;
			loadTurnsTemporal();
			RequestContext.getCurrentInstance().update("pnlTvTurnosLlamando");
			RequestContext.getCurrentInstance().update("pnlTvTurnosAtencion");
			RequestContext.getCurrentInstance().update("pnlTvTurnosAtendidos");
		}
	}

	public int sizeDive(int elements) {
		int sizeTotal = 12;
		if (elements == 0) {
			return sizeTotal;
		}
		int mod = sizeTotal % elements;
		if (mod != 0) {
			sizeTotal = sizeTotal - mod;
		}
		return sizeTotal / elements;
	}

	public List<ModuleUser> getModule() {
		return modulesUser;
	}

	public List<DigitalTurnTemp> getTurnosEnAtencion() {
		return turnosEnAtencion;
	}

	public List<DigitalTurnTemp> getTurnosLlamando() {
		return turnosLlamando;
	}

	public List<DigitalTurnTemp> getTurnosAtendidos() {
		return turnosAtendidos;
	}

	public ModuleUser getModuleUser() {
		return moduleUser;
	}

	public void setModuleUser(ModuleUser moduleUser) {
		this.moduleUser = moduleUser;
	}

	public List<ModuleUser> getModulesUser() {
		return modulesUser;
	}

	public int getSizeDivLlamando() {
		return sizeDivLlamando;
	}

	public int getSizeDivAtendidos() {
		return sizeDivAtendidos;
	}
}
