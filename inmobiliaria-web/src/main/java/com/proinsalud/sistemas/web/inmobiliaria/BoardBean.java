package com.proinsalud.sistemas.web.inmobiliaria;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;

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
	private static final Log LOG = App.getLogger(IndexAppBean.class);
	
	private boolean showFormInmueble = false;
	
	public BoardBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
			
	}
	
	public void loadInmueble() {
		try {
			showFormInmueble=true;
			
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	
	}

	public boolean isShowFormInmueble() {
		return showFormInmueble;
	}
	

}
