package com.proinsalud.sistemas.web.inmobiliaria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.inmobiliaria.model.Inmueble;
import com.proinsalud.sistemas.core.inmobiliaria.service.IInmuebleService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Jhon Frey Diaz D
 * @datetime 09/03/2018 - 5:48:14 p. m.
 */

@ManagedBean
@ViewScoped
public class BoardBean implements Serializable{

	private static final long serialVersionUID = 3112416956816360848L;
	private static final Log LOG = App.getLogger(BoardBean.class);
	
	
	@Autowired
	private IInmuebleService iInmuebleService;

	private List<Inmueble> inmueblesArriendo;
	private List<Inmueble> inmueblesVentas;
	private List<Inmueble> inmueblesAnticres;
	private List<Inmueble> inmuebles;
	private boolean showFormInmueble = false;
	
	public BoardBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		//
		inmueblesArriendo=new ArrayList<Inmueble>();
		inmueblesAnticres=new ArrayList<Inmueble>();
		inmueblesVentas=new ArrayList<Inmueble>();
		inmuebles= iInmuebleService.findAllEntity();
		loadInmueblesByUser();
			
	}
	
	public void loadInmueblesByUser() {
		//App.getUser().getId();
		
		for(Inmueble m:inmuebles) {
			if(m.getUser().getId().equals(App.getUser().getId())) {
				//inmueblesArriendo.add(m);
				if(m.getTypeBussines().getId()==1 ) {
					inmueblesArriendo.add(m);
				}
				if(m.getTypeBussines().getId()==2) {
					inmueblesAnticres.add(m);
				}
				if(m.getTypeBussines().getId()==3) {
					inmueblesVentas.add(m);
				}
			}
		}
			
	}
	
	public void loadInmueble(Inmueble inmueble) {
		try {
			showFormInmueble=true;
			
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	
	}

	public boolean isShowFormInmueble() {
		return showFormInmueble;
	}
	
	public List<Inmueble> getInmueblesArriendo() {
		return inmueblesArriendo;
	}

	public List<Inmueble> getInmueblesVentas() {
		return inmueblesVentas;
	}

	public List<Inmueble> getInmueblesAnticres() {
		return inmueblesAnticres;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	

}
