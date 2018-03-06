package com.proinsalud.sistemas.web.digital_turn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.digital_turn.model.Module;
import com.proinsalud.sistemas.core.digital_turn.model.Point;
import com.proinsalud.sistemas.core.digital_turn.model.PointService;
import com.proinsalud.sistemas.core.digital_turn.model.PrefixPoint;
import com.proinsalud.sistemas.core.digital_turn.model.Sede;
import com.proinsalud.sistemas.core.digital_turn.model.Service;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleService;
import com.proinsalud.sistemas.core.digital_turn.service.IPointService;
import com.proinsalud.sistemas.core.digital_turn.service.IPointServiceService;
import com.proinsalud.sistemas.core.digital_turn.service.IPrefixPointService;
import com.proinsalud.sistemas.core.digital_turn.service.ISedeService;
import com.proinsalud.sistemas.core.digital_turn.service.IServiceService;
import com.proinsalud.sistemas.core.digital_turn.service.IUtilDigitalTurnService;
import com.proinsalud.sistemas.core.util.enums.TypeModuleEnum;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Mauricio Pinchao
 * @datetime 22/02/2018 - 8:31:48 a. m.
 *
 */
@ManagedBean
@ViewScoped
public class PointBean implements Serializable{

	private static final long serialVersionUID = -3555322110972888305L;
	private static final Log LOG = App.getLogger(PointBean.class);
	
	@Autowired
	private IPointService iPointService;
	@Autowired
	private ISedeService iSedeService;
	@Autowired
	private IServiceService iServiceService; 
	@Autowired
	private IPointServiceService iPointServiceService;
	@Autowired
	private IUtilDigitalTurnService iUtilDigitalTurnService;
	@Autowired
	private IModuleService iModuleService;
	
	@Autowired
	private IPrefixPointService iPrefixPointService;
	
	private Point point = null;
	private Module module = null;
	
	private PrefixPoint prefixPoint;
	
	private List<Point> pointList;
	private List<Service> servicesList;
	private List<Sede> sedeList = new ArrayList<>(); 
	private List<PointService> pointServiceList;
	private DualListModel<PointService> dualListPointService;
	private List<Module> modulesByPointList = new ArrayList<>();
	
	private List<PrefixPoint> prefixPoints = new ArrayList<>();
	private DualListModel<Service> dualListService;
	
	
	private boolean showFormServices = false;
	private boolean showInfo = false;
	private boolean showInfoModules = false;
	
	public PointBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		pointList = iPointService.findAllEntity();
		sedeList = iSedeService.findAllEntity();
		servicesList = iServiceService.findAllEntity(); 
		pointServiceList = new ArrayList<>();
		prefixPoint = null;
	}

	/**
	 * Este método tiene como función activar el formulario para crear un nuevo punto.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:47:15 p. m.
	 *
	 */
	public void nuevo() {
		point = new Point();
	}
	
	/**
	 * Este método carga la información básica de un punto que se pasa como parámetro.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:46:15 p. m.
	 *
	 * @param point - Punto que se desea cargar 
	 */
	public void loadInfoPoint(Point point) {
		try {
			showInfo = true;
			this.point = point;
			this.point.setSede((Sede) UtilWeb.loadObject(sedeList, this.point.getSede())); 
			prefixPoints = iPrefixPointService.findEntityByIdPoint(point.getId());
			prefixPoint = null;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null);
		}
	}
	
	/**
	 * Este método se encarga de registrar o actualizar un punto en la base 
	 * de datos.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:45:42 p. m.
	 *
	 */
	public void savePoint() {
		try {
			if(point != null) {
				if(point.getId() == null) {
					iPointService.persistEntity(point);
				} else {
					iPointService.mergeEntity(point);
				}
				pointList = iPointService.findAllEntity();
			}
			point = null;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null);
		}
	}
	
	/**
	 * Método que se encarga de eliminar un punto de la base de datos.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:45:16 p. m.
	 *
	 */
	public void deletePoint() {
		try {
			if(point != null) {
				iPointService.deleteEntity(point);
				pointList = iPointService.findAllEntity();
				point = null;
				Messages.showMsg(Messages.MSG_INFO, "form1", "El registro se elimino correctamente");
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	/**
	 * Este método tiene como función comparar los servicios que se pasan como parámetro y los servicios que estan almacenados
	 * en la base de datos y los separa en una lista dual, con los servicios disponibles para asignar y los servicios que 
	 * ya fueron asignados.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:42:21 p. m.
	 *
	 * @param lstSelected - Lista de los servicios que tiene asignado el punto actual.
	 */
	private void loadPointService(List<PointService> lstSelected) {
		servicesList = iServiceService.findAllEntity();
		if(lstSelected != null && !lstSelected.isEmpty()) {
			for(PointService psSelected : lstSelected) {
				for(Service service : servicesList) {
					if(psSelected.getService().getId().equals(service.getId())) {
						servicesList.remove(service);
						break;
					}
				}
			}
		}
		List<PointService> pointServicesSelected = new ArrayList<>();
		servicesList.forEach(s -> pointServicesSelected.add(new PointService(this.point, s)));
		List<PointService> target = lstSelected == null ? new ArrayList<>() : lstSelected;
		dualListPointService = new DualListModel<PointService>(pointServicesSelected, target);
	}
	
	/**
	 * Este método carga la información relacionada con los servicios asignados al punto que se envia como 
	 * parámetro.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:41:02 p. m.
	 *
	 * @param point - Punto al que se desea cargar la información.
	 */
	public void loadPointForEdition(Point point) {
		try {
			showFormServices = true;
			this.point = point;
			pointServiceList = iPointServiceService.findEntityByIdPoint(this.point.getId());
			loadPointService(pointServiceList);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	/**
	 * Este método tiene como función activar el formulario para asignar servicios a un punto.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:47:50 p. m.
	 *
	 * @param point - Punto al cual se le va a asignar servicios.
	 */
	public void showFormServices(Point point) {
		try {
			this.point = point;
			showFormServices = true;
			loadPointService(null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	/**
	 * Este método sirve para asignar servicios a un determinado punto de atención.
	 * @author Mauricio Pinchao
	 * @datetime 23/02/2018 - 3:49:00 p. m.
	 *
	 */
	public void saveServicesPoint() {
		try {
			List<PointService> lstTemp = iPointServiceService.findEntityByIdPoint(this.point.getId());
			List<PointService> pointServiceTarget = dualListPointService.getTarget();
			if(pointServiceTarget.isEmpty()) {
				Messages.showMsg(Messages.MSG_WARNING, "form1", "Debe seleccionar al menos un servicio.");
				return;
			}
			iUtilDigitalTurnService.savePointService(lstTemp, pointServiceTarget);
			showFormServices = false;
			point = null;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	public void editPrefixPoint(RowEditEvent event, PrefixPoint pre) {
		try {
			prefixPoint = pre;
			if(prefixPoint != null) {
				prefixPoint.setSecuence(0);
				iPrefixPointService.mergeEntity(prefixPoint);
			}
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	/**
	 * Este método cargar los módulos del punto seleccionado.
	 * @author Mauricio Pinchao
	 * @datetime 27/02/2018 - 8:14:48 a. m.
	 * @param point - Punto del cual se va a cargar los módulos asignados.
	 */
	public void loadModules(Point point) {
		this.point = point;
		modulesByPointList = iModuleService.findEntityByPoint(this.point.getId());
		showInfoModules = true;
	}
	
	/**
	 *  
	 * @author Mauricio Pinchao
	 * @datetime 27/02/2018 - 8:14:38 a. m.
	 *
	 * @return
	 */
	public TypeModuleEnum[] getTypeModules() {
		return TypeModuleEnum.values();
	}
	
	/**
	 * Se crea una instancia de un nuevo módulo.
	 * @author Mauricio Pinchao
	 * @datetime 27/02/2018 - 8:14:10 a. m.
	 */
	public void nuevoModulo() {
		module = new Module();
	}
	
	public void loadModule(Module module) {
		this.module = module;
	}
	
	/**
	 * Este método se encarga de guardar un módulo al punto seleccionado
	 * @author Mauricio Pinchao
	 * @datetime 27/02/2018 - 8:11:05 a. m.
	 */
	public void saveModule() {
		try {
			if(module != null) {
				module.setPoint(point);
				if(modulesByPointList.isEmpty()) {
					if(module.getId() == null) {
						iModuleService.persistEntity(module);
					}
				} else {
					boolean existe = verifyNameModule(module.getNameModule());
					if(existe) {
						Messages.showMsg(Messages.MSG_ERROR, "form1", "El nombre del módulo, ya existe.");
					} else {
						if(module.getId() == null) {
							iModuleService.persistEntity(module);
						} else {
							iModuleService.mergeEntity(module);
						}
						Messages.showMsg(Messages.MSG_INFO, "form1", null);
					}
				}
				modulesByPointList = iModuleService.findEntityByPoint(point.getId());
			}
			module = null;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	/**
	 * Este método compara el nombre del módulo que se va a guardar con los
	 * nombres de los modulos que tiene asignado el punto seleccionado.
	 * @author Mauricio Pinchao
	 * @datetime 27/02/2018 - 8:11:43 a. m.
	 * @param nameModule - Nombre del módulo que se va a guardar en la BD.
	 * @return True, si existe el nombre o False si no existe.
	 */
	public boolean verifyNameModule(String nameModule) {
		boolean existe = false;
		for(Module m : modulesByPointList) {
			if(m.getNameModule().equalsIgnoreCase(nameModule)) {
				existe = true;
				break;
			}
		}
		return existe;
	}
	
	/**
	 * Este método se encarga de eliminar el módulo que se pasa como parámetro.
	 * @author Mauricio Pinchao
	 * @datetime 27/02/2018 - 8:13:19 a. m.
	 * @param module - Módulo que se desea eliminar.
	 */
	public void deleteModule(Module module) {
		try {
			this.module = module;
			if(this.module != null) {
				iModuleService.deleteEntity(this.module);
				modulesByPointList = iModuleService.findEntityByPoint(point.getId());
				this.module = null;
				Messages.showMsg(Messages.MSG_INFO, "form1", null);
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	public void cancelModule() {
		showInfoModules = true;
		module = null;
	}
	
	public void cancel() {
		point = null;
		showFormServices = false;
		showInfo = false;
		showInfoModules = false;
	}
	
	public Point getPoint() {
		return point;
	}
 
	public Module getModule() {
		return module;
	}

	public PrefixPoint getPrefixPoint() {
		return prefixPoint;
	}

	public List<Point> getPointList() {
		return pointList;
	}

	public List<Service> getServicesList() {
		return servicesList;
	}

	public List<Sede> getSedeList() {
		return sedeList;
	}

	public List<PointService> getPointServiceList() {
		return pointServiceList;
	}

	public DualListModel<PointService> getDualListPointService() {
		return dualListPointService;
	}

	public List<PrefixPoint> getPrefixPoints() {
		return prefixPoints;
	}

	public DualListModel<Service> getDualListService() {
		return dualListService;
	}

	public List<Module> getModulesByPointList() {
		return modulesByPointList;
	}

	public boolean isShowFormServices() {
		return showFormServices;
	}

	public boolean isShowInfo() {
		return showInfo;
	}

	public boolean isShowInfoModules() {
		return showInfoModules;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public void setPrefixPoint(PrefixPoint prefixPoint) {
		this.prefixPoint = prefixPoint;
	}

	public void setPointList(List<Point> pointList) {
		this.pointList = pointList;
	}

	public void setServicesList(List<Service> servicesList) {
		this.servicesList = servicesList;
	}

	public void setSedeList(List<Sede> sedeList) {
		this.sedeList = sedeList;
	}

	public void setPointServiceList(List<PointService> pointServiceList) {
		this.pointServiceList = pointServiceList;
	}

	public void setDualListPointService(DualListModel<PointService> dualListPointService) {
		this.dualListPointService = dualListPointService;
	}

	public void setPrefixPoints(List<PrefixPoint> prefixPoints) {
		this.prefixPoints = prefixPoints;
	}

	public void setDualListService(DualListModel<Service> dualListService) {
		this.dualListService = dualListService;
	}

	public void setShowFormServices(boolean showFormServices) {
		this.showFormServices = showFormServices;
	}
}
