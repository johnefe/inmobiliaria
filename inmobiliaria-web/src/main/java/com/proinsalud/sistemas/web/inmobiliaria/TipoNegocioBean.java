package com.proinsalud.sistemas.web.inmobiliaria;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.inmobiliaria.model.TypeBussines;
import com.proinsalud.sistemas.core.inmobiliaria.service.ITypeBussinesService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Jhon Frey Diaz
 * @datetime 2/03/2018 - 5:45:13 p. m.
 *
 */
@ManagedBean
@ViewScoped
public class TipoNegocioBean implements Serializable {

	private static final long serialVersionUID = -306780998175143210L;
	private static final Log LOG = App.getLogger(TipoNegocioBean.class);

	@Autowired
	private ITypeBussinesService iTypeBussinesService;

	private List<TypeBussines> tiposNegocios;

	public TipoNegocioBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		loadTypeBussines();
	}

	public void loadTypeBussines() {
		
		try {
			tiposNegocios = iTypeBussinesService.findAllEntity();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public List<TypeBussines> getTiposNegocios() {
		return tiposNegocios;
	}

}
