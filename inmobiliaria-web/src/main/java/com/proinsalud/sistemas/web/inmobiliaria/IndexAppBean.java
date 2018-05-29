package com.proinsalud.sistemas.web.inmobiliaria;

import java.io.Serializable;
import java.text.DecimalFormat;
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
 * @datetime 07/03/2018 - 5:48:14 p. m.
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
	private List<Inmueble> Arriendo;
	private List<Inmueble> Ventas;
	private List<Inmueble> Anticres;
	private List<Inmueble> inmuebles;
	
	DecimalFormat formatea = new DecimalFormat("###,###.##");
	
	public IndexAppBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		loadTypeBussines();
		inmueblesArriendo();
		
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
		inmuebles= iInmuebleService.findArriendo();	
		for(Inmueble m:inmuebles) {
			if(m.getTypeBussines().getNameTypeBussines().equals("arriendo")) {
				
				if (inmueblesArriendo.size()<4) {
					inmueblesArriendo.add(m);
				}
			}
			if(m.getTypeBussines().getNameTypeBussines().equals("anticres")) {
				
				if(inmueblesAnticres.size()<4) {
					inmueblesAnticres.add(m);
				}
			}
			if(m.getTypeBussines().getNameTypeBussines().equals("venta")) {
				
				if(inmueblesVentas.size()<4) {
					inmueblesVentas.add(m);
				}
			}
		}
		
	}
	
	
	public void inmueblesArriendo() {
		Arriendo=new ArrayList<Inmueble>();
		Anticres=new ArrayList<Inmueble>();
		Ventas=new ArrayList<Inmueble>();
		for(Inmueble m:inmuebles) {
			if(m.getTypeBussines().getNameTypeBussines().equals("arriendo")) {
				Arriendo.add(m);
			}
			if(m.getTypeBussines().getNameTypeBussines().equals("anticres")) {
				Anticres.add(m);
			}
			if(m.getTypeBussines().getNameTypeBussines().equals("venta")) {
				Ventas.add(m);
			}
		}
	}

	public List<TypeBussines> getTiposNegocios() {
		return tiposNegocios;
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
	/**/
	public DecimalFormat getFormatea() {
		return formatea;
	}

	public void setFormatea(DecimalFormat formatea) {
		this.formatea = formatea;
	}
	/**/

	public List<Inmueble> getArriendo() {
		return Arriendo;
	}

	public List<Inmueble> getVentas() {
		return Ventas;
	}

	public List<Inmueble> getAnticres() {
		return Anticres;
	}

}
