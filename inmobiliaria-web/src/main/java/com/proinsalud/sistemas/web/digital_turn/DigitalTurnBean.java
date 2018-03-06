package com.proinsalud.sistemas.web.digital_turn;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.digital_turn.model.DigitalTurn;
import com.proinsalud.sistemas.core.digital_turn.model.ModuleUser;
import com.proinsalud.sistemas.core.digital_turn.model.Point;
import com.proinsalud.sistemas.core.digital_turn.model.PointService;
import com.proinsalud.sistemas.core.digital_turn.model.PointServicePriority;
import com.proinsalud.sistemas.core.digital_turn.model.PrefixPoint;
import com.proinsalud.sistemas.core.digital_turn.model.PriorityTurn;
import com.proinsalud.sistemas.core.digital_turn.model.Service;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleUserService;
import com.proinsalud.sistemas.core.digital_turn.service.IPointServicePriorityService;
import com.proinsalud.sistemas.core.digital_turn.service.IPointServiceService;
import com.proinsalud.sistemas.core.digital_turn.service.IPrefixPointService;
import com.proinsalud.sistemas.core.digital_turn.service.IPriorityTurnService;
import com.proinsalud.sistemas.core.digital_turn.service.IUtilDigitalTurnService;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IPersonaService;
import com.proinsalud.sistemas.core.util.enums.TypeModuleEnum;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Mauricio Pinchao
 * @datetime 5/02/2018 - 4:31:26 p. m.
 */
@ManagedBean
@ViewScoped
public class DigitalTurnBean implements Serializable {

	private static final long serialVersionUID = -5880735572928025861L;

	private static final Log LOG = App.getLogger(DigitalTurnBean.class);
	
	@Autowired
	private IPointServicePriorityService iPointServicePriorityService;
	@Autowired
	private IPrefixPointService iPrefixPointService;
	@Autowired
	private IPersonaService iPersonaService;
	@Autowired
	private IPriorityTurnService iPriorityTurnService;
	@Autowired
	private IModuleUserService iModuleUserService;
	@Autowired
	private IPointServiceService iPointServiceService;
	@Autowired
	private IUtilDigitalTurnService iUtilDigitalTurnService;
	
	private Service selectedService1 = null;
	private Service selectedService2 = null;
	private Person person;
	private DigitalTurn digitalTurn = null;
	private PriorityTurn priorityTurn;
	private Point point;
	private PointServicePriority pointServicePriority;
	private PrefixPoint prefixPoint;
	private ModuleUser moduleUser;
	
	private List<Service> servicesByFather;
	private List<PriorityTurn> priorityList;
	private List<PriorityTurn> priorityListTemp;
	private List<ModuleUser> moduleUserList;
	private List<PointService> pointServiceList = new ArrayList<>();
	private List<PointService> pointServicesTemp;
	
	private boolean showForm;
	private boolean showResult;
	private String identificationPerson;
	private String mensaje;
	private String codeTurn = "";

	public DigitalTurnBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		showForm = false;
		priorityList = iPriorityTurnService.findAllEntity();
		moduleUserList = iModuleUserService.findAllEntityByUserTypeModule(App.getUser().getId(), TypeModuleEnum.ModuloAyuda.toString());
		if(moduleUserList.size() == 1) {
			moduleUser = moduleUserList.get(0);	
			loadServicesByPoint(moduleUser);
		} else {
			moduleUser = null;
		}
		person = null;
	}

	/**
	 * Este método se encarga de cargar el modulo seleccionado y de ese modulo
	 * carga la lista de servicios que pertenecen a un determinado punto. 
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:26:54 a. m.
	 *
	 * @param mUser - Modulo del usuario que fue seleccionado
	 */
	public void loadServicesByPoint(ModuleUser mUser) {
		try {
			moduleUser = mUser;
			if(moduleUser != null) {
				point = moduleUser.getModule().getPoint();
				pointServiceList = iPointServiceService.findEntityByIdPoint(point.getId());
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este metodo carga todos los servicios principales.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:26:21 a. m.
	 *
	 * @return lst - Lista de servicios principales. 
	 */
	public List<PointService> loadPointServicesFather() {
		List<PointService> lst = new ArrayList<>();
		for(PointService pointS : pointServiceList) {
			Service s = pointS.getService();
			if(s.getServiceFather() == null) {
				lst.add(pointS);
			}
		}
		return lst;
	}
	
	/**
	 * Este metodo se encarga de recibir la identificación de la persona y la busca en la 
	 * base de datos para verificar si se encuentra registrada. Si se encuentra registrada hace las 
	 * verificaciones para saber en que rango de prioridades podría estar.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:23:00 a. m.
	 *
	 */
	public void searchPerson() {
		priorityListTemp = new ArrayList<>();
		try {
			if(identificationPerson != null) {
				person = iPersonaService.findEntityByIdentification(identificationPerson);
				if(person != null) {
					mensaje = "Bienvenido: " + person.getNameCompleted();
					String sex = person.getSexo();
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
					int nacimientoPersona = Integer.parseInt(dateFormat.format(person.getBirthdate()));
					int edad = year - nacimientoPersona;
					for(PriorityTurn p : priorityList) {
						if(edad <= 5) {
							if(p.getNamePriorityTurn().equals("Usuario discapacitado") || p.getNamePriorityTurn().equals("Menores de 5 años") || p.getNamePriorityTurn().equals("Ninguna")) {
								priorityListTemp.add(p);
							}
						}
						else if(edad > 5 && edad < 60) {
							if(sex.equals("M")) {
								if(p.getNamePriorityTurn().equals("Usuario discapacitado") || p.getNamePriorityTurn().equals("Ninguna")) {
									priorityListTemp.add(p);
								}
							} else {
								if(p.getNamePriorityTurn().equals("Mujeres embarazadas") || p.getNamePriorityTurn().equals("Usuario discapacitado") || p.getNamePriorityTurn().equals("Ninguna")) {
									priorityListTemp.add(p);
								}
							}
						}
						else if(edad >= 60) {
							if(p.getNamePriorityTurn().equals("Adulto mayor") || p.getNamePriorityTurn().equals("Usuario discapacitado") || p.getNamePriorityTurn().equals("Ninguna")) {
								priorityListTemp.add(p);
							}
						}
					}
				} else {
					mensaje = "La persona con identificación " + identificationPerson + " no se encuentra registrada.";
					priorityListTemp = priorityList;
				}
				showForm = true;
			} else {
				showForm = false;
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este metodo obtiene la prioridad que selecciona el usuario.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:22:39 a. m.
	 *
	 * @param priority - Prioridad seleccionada por el usuario.
	 */
	public void savePriority(PriorityTurn priority) {
		try {
			priorityTurn = priority;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este metodo se encarga de obtener el servicio que eligió el usuario y
	 * verifica si dentro de ese servicio hay mas servicios. Si no hay más servicios
	 * llama al metodo de imprimir el digiturno.
	 */
	public void loadSelectedService(PointService pointService) {
		try {
			selectedService1 = pointService.getService();
			if(selectedService1 != null) {
				servicesByFather = selectedService1.getServices();
				if(servicesByFather.isEmpty()) {
					printDigitalTurn(pointService);
				} else {
					pointServicesTemp = new ArrayList<>();
					for(PointService p : pointServiceList) {
						if(p.getService().getServiceFather() != null) {
							for(Service s : servicesByFather) {
								if(p.getService().getId() == s.getId()) {
									pointServicesTemp.add(p);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Este metodo se encarga de guardar los datos del digiturno y luego de guardarlo, lo imprime.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:18:11 a. m.
	 *
	 * @param pointService - Recibe como parametro un pointService para obtener el punto donde esta ubicado 
	 * el servicio elegido por el usuario. 
	 */
	public void printDigitalTurn(PointService pointService) {
		digitalTurn = new DigitalTurn();
		try {
			selectedService2 = pointService.getService();
			pointServicePriority = iPointServicePriorityService.findEntityByIdPointService(pointService.getId(), priorityTurn.getId());
			prefixPoint = iPrefixPointService.findEntityByPrefixPoint(pointServicePriority.getPrefix().getId(), pointService.getPoint().getId());
			if(selectedService2 != null) {
				if(person != null) {
					digitalTurn.setPerson(person);
					digitalTurn.setIdentificationPerson(person.getIdentification());
				} else {
					digitalTurn.setIdentificationPerson(identificationPerson);
				}
				digitalTurn.setIdUser(App.getUser().getId());
			}
			iUtilDigitalTurnService.saveDigitalTurn(digitalTurn, pointService, prefixPoint);
			showResult = true;
			actualizarDate();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Este metodo se encarga de enviar un valor cuando se hace una modificación. 
	 * Ejemplo, se registra un nuevo digiturno
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:19:39 a. m.
	 *
	 */
	public void actualizarDate() {
		Refresh.setValue(moduleUser.getModule().getPoint().getId(), System.currentTimeMillis());
	}
	
	public void printTicket() {
		System.out.println("| --------------------------------------------------------------------- |");
		System.out.println("| 									|");
		System.out.println("|		*****	BIENVENIDOS A PROINSALUD	*****		|");
		System.out.println("|		    	     Su turno es:			    	|");
		System.out.println("|				" + digitalTurn.getCodeTurn() + "					|");
		System.out.println("|			     " + digitalTurn.getPointService().getService().getNameService() + "		             |");
		System.out.println("|			" + digitalTurn.getHourTurn() + "			|");
		System.out.println("| 									|");
		System.out.println("| --------------------------------------------------------------------- |");
		cleanForm();
	}
	
	/**
	 * Metodo que se encarga de limpiar el formulario luego de registrar un nuevo digiturno.
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:20:20 a. m.
	 *
	 */
	public void cleanForm() {
		showForm = false;
		priorityTurn = null;
		identificationPerson = null;
		selectedService1 = null;
		selectedService2 = null;
		digitalTurn = null;
		person = null;
		codeTurn = null;
		showResult = false;
	}
	
	/**
	 * Este metodo tiene como proposito devolver a la vista del menu anterior
	 * dependiendo de la ubicacion entre los menus. 
	 * @author Mauricio Pinchao
	 * @datetime 21/02/2018 - 9:20:48 a. m.
	 *
	 */
	public void back() {
		if(showForm == true && priorityTurn == null) {
			showForm = false;
			cleanForm();
		}
		if(priorityTurn != null && selectedService1 == null) {
			priorityTurn = null;
		}
		if(selectedService1 != null && selectedService2 == null) {
			selectedService1 = null;
		} 
		if(selectedService1 == null && selectedService2 != null) {
			selectedService2 = null;
		}
	}

	public Service getSelectedService1() {
		return selectedService1;
	}

	public Service getSelectedService2() {
		return selectedService2;
	}

	public Person getPerson() {
		return person;
	}

	public DigitalTurn getDigitalTurn() {
		return digitalTurn;
	}

	public PriorityTurn getPriorityTurn() {
		return priorityTurn;
	}

	public Point getPoint() {
		return point;
	}

	public ModuleUser getModuleUser() {
		return moduleUser;
	}

	public List<Service> getServicesByFather() {
		return servicesByFather;
	}

	public List<PriorityTurn> getPriorityList() {
		return priorityList;
	}

	public List<PriorityTurn> getPriorityListTemp() {
		return priorityListTemp;
	}

	public List<ModuleUser> getModuleUserList() {
		return moduleUserList;
	}

	public List<PointService> getPointServiceList() {
		return pointServiceList;
	}

	public List<PointService> getPointServicesTemp() {
		return pointServicesTemp;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public boolean isShowResult() {
		return showResult;
	}

	public String getIdentificationPerson() {
		return identificationPerson;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getCodeTurn() {
		return codeTurn;
	}

	public IPointServicePriorityService getiPointServicePriorityService() {
		return iPointServicePriorityService;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setDigitalTurn(DigitalTurn digitalTurn) {
		this.digitalTurn = digitalTurn;
	}

	public void setPriorityTurn(PriorityTurn priorityTurn) {
		this.priorityTurn = priorityTurn;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public void setIdentificationPerson(String identificationPerson) {
		this.identificationPerson = identificationPerson;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
