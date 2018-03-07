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
import com.proinsalud.sistemas.core.inmobiliaria.model.TypeBussines;
import com.proinsalud.sistemas.core.inmobiliaria.service.IInmuebleService;
import com.proinsalud.sistemas.core.inmobiliaria.service.ITypeBussinesService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;
/**
 * @author Jhon Frey Diaz D
 * @datetime 2/03/2018 - 5:48:14 p. m.
 */

@ManagedBean
@ViewScoped
public class IndexAppBean implements Serializable {

	
	private static final long serialVersionUID = 2545548337011963741L;
	private static final Log LOG = App.getLogger(IndexAppBean.class);
	
	
	@Autowired
	private ITypeBussinesService iTypeBussinesService;
	
	@Autowired
	private IInmuebleService iInmuebleService;

	private List<TypeBussines> tiposNegocios;
	private List<Inmueble> inmueblesArriendo;
	private List<Inmueble> inmueblesVentas;
	private List<Inmueble> inmueblesAnticres;
	private List<Inmueble> inmuebles;
	
	
	public IndexAppBean() {
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
			loadInmuebles();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	public void loadInmuebles() {
		inmueblesArriendo=new ArrayList<Inmueble>();
		inmueblesAnticres=new ArrayList<Inmueble>();
		inmueblesVentas=new ArrayList<Inmueble>();
		inmuebles= iInmuebleService.findAllEntity();
		for(Inmueble m:inmuebles) {
			if(m.getTypeBussines().getId()==1) {
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

	public List<TypeBussines> getTiposNegocios() {
		return tiposNegocios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return LOG;
	}

	public ITypeBussinesService getiTypeBussinesService() {
		return iTypeBussinesService;
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
