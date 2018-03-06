package com.proinsalud.sistemas.web.digital_turn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.digital_turn.model.Service;
import com.proinsalud.sistemas.core.digital_turn.service.IServiceService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Mauricio Pinchao
 * @datetime 26/02/2018 - 9:48:58 a. m.
 *
 */
@ManagedBean
@ViewScoped
public class ServicesBean implements Serializable {

	private static final long serialVersionUID = -4147658443694641362L;
	private static final Log LOG = App.getLogger(ServicesBean.class);
	
	@Autowired
	private IServiceService iServiceService;
	
	private List<Service> servicesFatherList = new ArrayList<>();
	private List<Service> servicesList = new ArrayList<>();
	
	private Service service = null;
	private Service subService = null;
	private Service serviceFather = null;
	
	private boolean showForm1 = false;
	private boolean showFormService2 = false;
	
	public ServicesBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		servicesFatherList = iServiceService.findAllEntityFather();
	}
	
	/**
	 * Este método carga la información del servicio principal seleccionado.
	 * @author Mauricio Pinchao
	 * @datetime 26/02/2018 - 11:34:25 a. m.
	 *
	 * @param service - Servicio seleccionado de la lista.
	 */
	public void loadInfoService(Service service) {
		try {
			this.service = service;
			showForm1 = true;
			if(this.service.getServiceFather() != null) {
				this.service.setServiceFather((Service) UtilWeb.loadObject(servicesFatherList, this.service.getServiceFather()));
			} else {
				this.service.setServiceFather(null);
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	public void loadInfoSubService(Service subService) {
		try {
			this.subService = subService;
			showFormService2 = false;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/**
	 * Este método se encargar de crear una nueva instancia del servicio.
	 * @author Mauricio Pinchao
	 * @datetime 26/02/2018 - 11:33:59 a. m.
	 *
	 */
	public void nuevo() {
		service = new Service();
		showForm1 = true;
	}
	
	/**
	 * Este método se encargar de crear un nuevo subservicio.
	 * @author Mauricio Pinchao
	 * @datetime 26/02/2018 - 11:47:38 a. m.
	 *
	 */
	public void nuevoSubService() {
		subService = new Service();
		showFormService2 = false;
	}
	
	/**
	 * Este método se encarga de guardar un servicio principal. (Father)
	 * @author Mauricio Pinchao
	 * @datetime 26/02/2018 - 11:33:30 a. m.
	 *
	 */
	public void saveService() {
		try {
			if(service != null) {
				if(service.getId() == null) {
					service.setServiceFather(null);
					iServiceService.persistEntity(service);
				}else {
					iServiceService.mergeEntity(service);
				}
				servicesFatherList = iServiceService.findAllEntityFather();
			}
			service = null;
			showForm1 = false;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null);
		}
	}
	
	/**
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 26/02/2018 - 11:46:40 a. m.
	 *
	 * @param service
	 */
	public void saveSubService() {
		try {
			if(this.service != null) {
				//this.subService = service;
				if(subService != null) {
					subService.setServiceFather(serviceFather);
					if(subService.getId() == null) {
						iServiceService.persistEntity(subService);
					} else {
						iServiceService.mergeEntity(subService);
					}
					servicesList = iServiceService.findEntityByIdFather(this.service.getId());
				}
				subService = null;
				showFormService2 = true;
				Messages.showMsg(Messages.MSG_INFO, "form1", null);
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	public void addServices(Service service) {
		try {
			this.service = service;
			if(this.service != null) {
				servicesList = iServiceService.findEntityByIdFather(this.service.getId());
				serviceFather = this.service;
			}
			showFormService2 = true;
			showForm1 = false;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	public void cancel() {
		service = null;
		showForm1 = false;
	}

	public void cancel2() {
		subService = null;
		showFormService2 = true;
	}
	
	public void back() {
		showFormService2 = false;
		subService = null;
		service = null;
	}
	
	/**
	 * Método que se encarga de eliminar el servicio actual.
	 * @author Mauricio Pinchao
	 * @datetime 26/02/2018 - 11:33:15 a. m.
	 */
	public void deleteService() {
		try {
			iServiceService.deleteEntity(service);
			servicesFatherList = iServiceService.findAllEntityFather();
			service = null;
			showForm1 = false;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null);
		}
	}
	
	public void deleteSubService(Service subService) {
		try {
			this.subService = subService;
			iServiceService.deleteEntity(this.subService);
			servicesList = iServiceService.findEntityByIdFather(this.service.getId());
			this.subService = null;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null);
		}
		
	}

	public List<Service> getServicesFatherList() {
		return servicesFatherList;
	}

	public List<Service> getServicesList() {
		return servicesList;
	}

	public Service getService() {
		return service;
	}
 
	public Service getSubService() {
		return subService;
	}

	public Service getServiceFather() {
		return serviceFather;
	}

	public boolean isShowForm1() {
		return showForm1;
	}

	public boolean isShowFormService2() {
		return showFormService2;
	}

	public void setServicesList(List<Service> servicesList) {
		this.servicesList = servicesList;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setSubService(Service subService) {
		this.subService = subService;
	}

	public void setServiceFather(Service serviceFather) {
		this.serviceFather = serviceFather;
	}
}
