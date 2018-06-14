package com.proinsalud.sistemas.web.inmobiliaria;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.inmobiliaria.model.Comentary;
import com.proinsalud.sistemas.core.inmobiliaria.model.Inmueble;
import com.proinsalud.sistemas.core.inmobiliaria.model.TipoInmueble;
import com.proinsalud.sistemas.core.inmobiliaria.model.TypeBussines;
import com.proinsalud.sistemas.core.inmobiliaria.service.IComentaryService;
import com.proinsalud.sistemas.core.inmobiliaria.service.IInmuebleService;
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
	private static final String PANEL_DETAIL_INMUEBLE = "PANEL_DETAIL_INMUEBLE";

	@Autowired
	private ITypeBussinesService iTypeBussinesService;
	
	@Autowired
	private ITipoInmuebleService iTipoInmuebleService;
	
	@Autowired
	private IInmuebleService iInmuebleService;
	
	@Autowired
	private IComentaryService iComentaryService;
	private List<TypeBussines> tiposNegocios;
	private List<TipoInmueble> tiposInmuebles;
	private List<Inmueble> inmuebles;
	private List<Inmueble> listInmueblesArriendo;
	private List<Inmueble> listInmueblesVenta;
	private List<Inmueble> listInmueblesAnticres;
	private TipoInmueble tipoInmueble;
	private Inmueble inmueble;
	private Inmueble detailInmueble;
	private Comentary comentary;
	private int sizeDivInmuebles;
	private String comentario="";
	private boolean ShowpnlInmueblesFiltro;
	private boolean ShowpnlInmuebles;
	private boolean ShowpnlDetailInmueble;
	private Date fecha;

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
			tiposNegocios = new ArrayList<TypeBussines>();
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
		ShowpnlDetailInmueble = false;
	}
	
	private void mostrarPanel(String panel) {
		ocultarPaneles();
		switch (panel) {
		case PANEL_INMUEBLES_FILTRO:
			ShowpnlInmueblesFiltro = true;
			ShowpnlInmuebles= false;
			ShowpnlDetailInmueble = false;
			break;
		case PANEL_INMUEBLES:
			ShowpnlInmuebles= true;
			ShowpnlInmueblesFiltro= false;
			ShowpnlDetailInmueble = false;
			break;
		case PANEL_DETAIL_INMUEBLE:
			ShowpnlInmuebles= false;
			ShowpnlInmueblesFiltro= false;
			ShowpnlDetailInmueble = true;
			break;
		default:
			break;
		}
	}
	
	public void ShowDetailInmueble(Inmueble inmueble) {
		mostrarPanel(PANEL_DETAIL_INMUEBLE);
		detailInmueble = inmueble;

	}
	
	public void SendMessage( Inmueble inmueblex) {
		
		//System.out.println(comentario);
		try {
			
			if(comentario != null && inmueblex.getId() != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				detailInmueble = inmueblex;
				Date date = new Date();
				dateFormat.format(date);
				comentary= new Comentary();
				comentary.setDescription(comentario);
				comentary.setInmueble(detailInmueble);
				comentary.setCreateAt(date);
				comentary = iComentaryService.persistEntity(comentary);
				RequestContext.getCurrentInstance().execute("cleanComentary();");
			}
			
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	public void ShowInmublesByType(TipoInmueble tipoInmueble) {
		inmuebles = new ArrayList<Inmueble>();
		long i=tipoInmueble.getId();
		listInmueblesArriendo = new ArrayList<Inmueble>();
		listInmueblesVenta = new ArrayList<Inmueble>();
		listInmueblesAnticres = new ArrayList<Inmueble>();
		inmuebles = iInmuebleService.findInmuebleByTipo(i);
		mostrarPanel(PANEL_INMUEBLES_FILTRO);
		
		for(Inmueble m:inmuebles) {
			
				if(m.getTypeBussines().getNameTypeBussines().equals("arriendo")) {		
					listInmueblesArriendo.add(m);
				}
				if(m.getTypeBussines().getNameTypeBussines().equals("venta")) {		
					listInmueblesVenta.add(m);
				}
				if(m.getTypeBussines().getNameTypeBussines().equals("anticres")) {		
					listInmueblesAnticres.add(m);
				}
		}
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

	public TipoInmueble getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(TipoInmueble tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public List<Inmueble> getListInmueblesArriendo() {
		return listInmueblesArriendo;
	}

	public static String getPanelInmueblesFiltro() {
		return PANEL_INMUEBLES_FILTRO;
	}

	public static String getPanelInmuebles() {
		return PANEL_INMUEBLES;
	}
	
	public static String getPanelDetailInmueble() {
		return PANEL_DETAIL_INMUEBLE;
	}

	public ITypeBussinesService getiTypeBussinesService() {
		return iTypeBussinesService;
	}

	public ITipoInmuebleService getiTipoInmuebleService() {
		return iTipoInmuebleService;
	}

	public IInmuebleService getiInmuebleService() {
		return iInmuebleService;
	}

	public List<Inmueble> getListInmueblesVenta() {
		return listInmueblesVenta;
	}

	public List<Inmueble> getListInmueblesAnticres() {
		return listInmueblesAnticres;
	}

	public boolean isShowpnlDetailInmueble() {
		return ShowpnlDetailInmueble;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Inmueble getDetailInmueble() {
		return detailInmueble;
	}

	public Comentary getComentary() {
		return comentary;
	}

	public void setComentary(Comentary comentary) {
		this.comentary = comentary;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
