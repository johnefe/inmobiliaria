package com.proinsalud.sistemas.web.inmobiliaria;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.inmobiliaria.model.TipoInmueble;
import com.proinsalud.sistemas.core.inmobiliaria.model.TypeBussines;
import com.proinsalud.sistemas.core.inmobiliaria.service.ITipoInmuebleService;
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
	private static final String PANEL_INMUEBLES_FILTRO = "PANEL_INMUEBLES_FILTRO";
	private static final String PANEL_INMUEBLES = "PANEL_INMUEBLES";

	@Autowired
	private ITypeBussinesService iTypeBussinesService;
	
	@Autowired
	private ITipoInmuebleService iTipoInmuebleService;

	private List<TypeBussines> tiposNegocios;
	private List<TipoInmueble> tiposInmuebles;
	private int sizeDivInmuebles;
	
	private boolean ShowpnlInmueblesFiltro;
	private boolean ShowpnlInmuebles;

	public TipoNegocioBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		loadTypeBussines();
	}

	public void loadTypeBussines() {
		mostrarPanel(PANEL_INMUEBLES);
		try {
			tiposNegocios = iTypeBussinesService.findAllEntity();
			tiposInmuebles = iTipoInmuebleService.findAllEntity();
			sizeDivInmuebles = sizeDiv(tiposInmuebles.size());
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	/*metodo que saca el tamaño del tipo de negocios*/
	public int sizeDiv (int elements) {
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
	
	public void ocultarPaneles() {
		ShowpnlInmueblesFiltro=false;
		ShowpnlInmuebles= false;
	}
	
	private void mostrarPanel(String panel) {
		ocultarPaneles();
		switch (panel) {
		case PANEL_INMUEBLES_FILTRO:
			ShowpnlInmueblesFiltro = true;
			ShowpnlInmuebles= false;
			break;
		case PANEL_INMUEBLES:
			ShowpnlInmuebles= true;
			ShowpnlInmueblesFiltro= false;
		default:
			break;
		}
	}
	
	public void ShowInmublesByType() {
		mostrarPanel(PANEL_INMUEBLES_FILTRO);
		System.out.println("jajajajaj");
	}
	
	/* getters and setters*/

	public List<TypeBussines> getTiposNegocios() {
		return tiposNegocios;
	}
	
	
	public List<TipoInmueble> getTiposInmuebles() {
		return tiposInmuebles;
	}

	public int getSizeDivInmuebles() {
		return sizeDivInmuebles;
	}

	public boolean isShowpnlInmueblesFiltro() {
		return ShowpnlInmueblesFiltro;
	}

	public boolean isShowpnlInmuebles() {
		return ShowpnlInmuebles;
	}
	
}
