package com.proinsalud.sistemas.web.application.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;

import com.proinsalud.sistemas.core.security.model.Users;
//import com.proinsalud.sistemas.web.digital_turn.DigitalTurnAttentionBean;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Jhon Frey Diaz
 * @datetime 13/03/2018 - 4:55:22 p. m.
 *
 */
@ManagedBean
@ViewScoped
public class RegistroUsuarioBean implements Serializable  {

	private static final long serialVersionUID = -6213800443677802094L;
	
	//private static final Log LOG = App.getLogger(DigitalTurnAttentionBean.class);
	
	private Users user;
	
	public RegistroUsuarioBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		
	}
	
	public void saveUsuario() {
		
		try {
			
			
			
		} catch (Exception e) {
		//	UtilWeb.printError(LOG, e);
		}
		
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	

}
