package com.proinsalud.sistemas.web.convocatory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.convocatory.model.CommentOrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.CommentProductOrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.model.OrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.ProductOrderBuy;
import com.proinsalud.sistemas.core.convocatory.model.Quotation;
import com.proinsalud.sistemas.core.convocatory.model.QuotationConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.model.StepOrder;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTyPeConvocatoryUsers;
import com.proinsalud.sistemas.core.convocatory.model.StepOrderConvocatoryTypeConvocatory;
import com.proinsalud.sistemas.core.convocatory.service.ICommentOrderBuyService;
import com.proinsalud.sistemas.core.convocatory.service.ICommentProductOrderBuyService;
import com.proinsalud.sistemas.core.convocatory.service.IConvocatoryService;
import com.proinsalud.sistemas.core.convocatory.service.IStepOrderConvocatoryTyPeConvocatoryUsersService;
import com.proinsalud.sistemas.core.convocatory.service.IStepOrderConvocatoryTypeConvocatoryService;
import com.proinsalud.sistemas.core.convocatory.service.IStepOrderService;
import com.proinsalud.sistemas.core.convocatory.service.IUtilConvocatoryService;
import com.proinsalud.sistemas.core.util.comparators.GeneralComparator;
import com.proinsalud.sistemas.core.util.enums.StateConvocatoryEnum;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;
import com.proinsalud.sistemas.web.util.reports.RI;
import com.proinsalud.sistemas.web.util.reports.ReportGenerator;

/**
 * 
 * @author Andres Santacruz
 * @datetime 17/01/2018 - 11:09:18 a. m.
 *
 */
@ManagedBean
@ViewScoped
public class SeguimientoConvocatoriaBean implements Serializable {

	private static final long serialVersionUID = -3021815933440115109L;
	private static final Log LOG = App.getLogger(SeguimientoConvocatoriaBean.class);

	@Autowired
	private IConvocatoryService iConvocatoryService;

	@Autowired
	private IUtilConvocatoryService iUtilConvocatoryService;

	@Autowired
	private ICommentProductOrderBuyService iCommentProductOrderBuyService;

	@Autowired
	private IStepOrderConvocatoryTyPeConvocatoryUsersService iStepOrderConvocatoryTyPeConvocatoryUsersService;

	@Autowired
	private IStepOrderService iStepOrderService;

	@Autowired
	private ICommentOrderBuyService iCommentOrderBuyService;

	@Autowired
	private IStepOrderConvocatoryTypeConvocatoryService iStepOrderConvocatoryTyPeConvocatoryService;

	private List<Convocatory> convocatories;
	private List<ProductOrderBuy> productOrderBuys;
	private List<QuotationConvocatoryProduct> allQuotationConvocatory;
	private List<QuotationConvocatoryProduct> qcpByProduct;
	private List<StepOrderConvocatoryTyPeConvocatoryUsers> lstStepsUsers;
	private List<StepOrderConvocatoryTypeConvocatory> stepsOCTC;
	private List<StepOrderConvocatoryTypeConvocatory> lstStepsType;

	private Convocatory convocatory;
	private ConvocatoryProduct cpSelected;
	private QuotationConvocatoryProduct qcpSelected;
	private ProductOrderBuy pobSelected;
	private StepOrder stepOrder;
	private StepOrder stepCurrent;
	private StepOrderConvocatoryTypeConvocatory stepOCTCSelected;
	private boolean pnlListadoConvocatorias;
	private boolean pnlFlujoConvocatoria;
	private boolean pnlAnalisisConvocatoria;
	private boolean modificarFlujo;
	private boolean btnNoAprobarOrden;
	private String rutaGenerada;
	private String PRIMERA_OPCION = "#f39c12;";
	private String SEGUNDA_OPCION = "#00a65a;";
	private String TERCERA_OPCION = "#3c8dbc;";

	public SeguimientoConvocatoriaBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		loadConvocatories();
		pnlListadoConvocatorias = true;
	}

	/**
	 * Carga todas las convocatorias
	 * 
	 * @author Andres Santacruz
	 * @datetime 13/02/2018 - 9:22:08 a. m.
	 *
	 */
	private void loadConvocatories() {
		try {
			// List<Convocatory> convocatoriesTemp = iConvocatoryService.findAllEntity();
			// convocatories = convocatoriesTemp.stream().filter(c->
			// c.getState().toString().equals(StateConvocatoryEnum.Publicada.toString())).collect(Collectors.toList());
			convocatories = iConvocatoryService.findByAllEntityByState(StateConvocatoryEnum.Publicada.toString());
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Regresa al listado principal
	 * 
	 * @author Andres Santacruz
	 * @datetime 13/02/2018 - 9:21:58 a. m.
	 *
	 */
	public void back() {
		convocatory = null;
		hideAllPanels();
		pnlListadoConvocatorias = true;
		productOrderBuys = null;
		pobSelected = null;
		qcpSelected = null;
		cpSelected = null;
		qcpByProduct = null;
		allQuotationConvocatory = null;
		lstStepsUsers = null;
		stepsOCTC = null;
		lstStepsType = null;

		stepOrder = null;
		stepCurrent = null;
		stepOCTCSelected = null;
		modificarFlujo = false;
		btnNoAprobarOrden = false;
		rutaGenerada = null;
		loadConvocatories();
	}

	/**
	 * Setea los estados de las paneles de la vista en false
	 * 
	 * @author Andres Santacruz
	 * @datetime 16/02/2018 - 8:23:10 a. m.
	 *
	 */
	public void hideAllPanels() {
		pnlListadoConvocatorias = false;
		pnlAnalisisConvocatoria = false;
		pnlFlujoConvocatoria = false;
	}

	/**
	 * Carga la convocatoria y la orden de compra
	 * 
	 * @author Andres Santacruz
	 * @datetime 13/02/2018 - 9:21:39 a. m.
	 *
	 * @param convocatory
	 */
	public void loadConvocatory(Convocatory convocatory) {
		try {
			this.convocatory = convocatory;
			hideAllPanels();
			pnlAnalisisConvocatoria = true;
			List<Quotation> lst = new ArrayList<>(convocatory.getQuotations());
			for (Quotation q : convocatory.getQuotations()) {
				if (q.getQuotationConvocatoryProducts().isEmpty()) {
					lst.remove(q);
				}
			}
			this.convocatory.setQuotations(lst);
			productOrderBuys = new ArrayList<>();
			OrderBuy orderBuy = this.convocatory.getOrderBuy();
			orderBuy = orderBuy == null ? new OrderBuy() : orderBuy;
			orderBuy.setConvocatory(this.convocatory);
			convocatory.setOrderBuy(orderBuy);
			productOrderBuys = orderBuy.getProductsOrder();
			productOrderBuys = productOrderBuys.isEmpty() ? new ArrayList<>() : new ArrayList<>(productOrderBuys);
			searchAllQuotations();
			setCheckedProduct();
			setColorProduct();
			Collections.sort(convocatory.getOrderBuy().getComments(), GeneralComparator.CommentsOrderBuyByRegisteredDesc);
			setStepCurrentOrderBuy();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	/**
	 * Guarda todos los productos cotizados en una lista y la ordena en descendente
	 * por precio
	 * 
	 * @author Andres Santacruz
	 * @datetime 15/02/2018 - 11:26:43 a. m.
	 *
	 */
	public void searchAllQuotations() {
		allQuotationConvocatory = new ArrayList<>();
		convocatory.getQuotations().stream().forEach(q -> allQuotationConvocatory.addAll(q.getQuotationConvocatoryProducts()));
		Collections.sort(allQuotationConvocatory, GeneralComparator.QuotationConvocatoryProductByPrice);
	}

	/**
	 * Agrega el check si ya esta en la lista de productos seleccionados
	 * 
	 * @author Andres Santacruz
	 * @datetime 15/02/2018 - 11:26:17 a. m.
	 *
	 */
	public void setCheckedProduct() {
		for (ProductOrderBuy pob : productOrderBuys) {
			for (QuotationConvocatoryProduct qcp : allQuotationConvocatory) {
				if (pob.getProductSelected().getId().equals(qcp.getId())) {
					qcp.setChecked(true);
					break;
				}
			}
		}
	}

	/**
	 * Le agrega el color segun la opcion de compra
	 * 
	 * @author Andres Santacruz
	 * @datetime 15/02/2018 - 11:26:01 a. m.
	 *
	 */
	public void setColorProduct() {
		for (ConvocatoryProduct cp : convocatory.getConvocatoryProducts()) {
			List<QuotationConvocatoryProduct> subList = allQuotationConvocatory.stream().filter(qcp -> qcp.getConvocatoryProduct().getId().equals(cp.getId())).collect(Collectors.toList());
			List<String> colors = new ArrayList<>(Arrays.asList(PRIMERA_OPCION, SEGUNDA_OPCION, TERCERA_OPCION));
			QuotationConvocatoryProduct last = null;
			for (QuotationConvocatoryProduct qcp : subList) {
				if (last != null) {
					if (last.getPrice().equals(qcp.getPrice())) {
						qcp.setColor(colors.get(0));
					} else {
						colors.remove(0);
						if (colors.isEmpty()) {
							break;
						}
						qcp.setColor(colors.get(0));
					}
				} else {
					qcp.setColor(colors.get(0));
				}
				last = qcp;
				if (colors.isEmpty())
					break;
			}
		}
	}

	/**
	 * Retorna true si la cotizacion tiene productos cotizados
	 * 
	 * @author Andres Santacruz
	 * @datetime 13/02/2018 - 9:21:06 a. m.
	 *
	 * @return
	 */
	public boolean hasQuotationsProviders(Convocatory convocatory) {
		try {
			if (convocatory != null) {
				for (Quotation q : convocatory.getQuotations()) {
					if (!q.getQuotationConvocatoryProducts().isEmpty()) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			return false;
		}
	}

	/**
	 * Retorna la cotizacion del producto segun la cotizacion del proveedor y el
	 * producto
	 * 
	 * @author Andres Santacruz
	 * @datetime 13/02/2018 - 9:20:34 a. m.
	 *
	 * @param q
	 * @param cp
	 * @return
	 */
	public QuotationConvocatoryProduct getQCP(Quotation q, ConvocatoryProduct cp) {
		for (QuotationConvocatoryProduct qcp : q.getQuotationConvocatoryProducts()) {
			if (qcp.getConvocatoryProduct().getId().equals(cp.getId())) {
				return qcp;
			}
		}
		return null;
	}

	/**
	 * Agrega un producto a los productos de la orden de compra
	 * 
	 * @author Andres Santacruz
	 * @datetime 13/02/2018 - 9:15:06 a. m.
	 *
	 * @param qcp
	 */
	public void addQCP(QuotationConvocatoryProduct qcp) {
		Long qcpId = qcp.getId();
		Predicate<ProductOrderBuy> predicate = cp -> cp.getProductSelected().getId().equals(qcpId);
		boolean removed = productOrderBuys.removeIf(predicate);
		qcp.setChecked(!removed);
		if (!removed) {
			Long cpId = qcp.getConvocatoryProduct().getId();
			predicate = cp -> cp.getProductSelected().getConvocatoryProduct().getId().equals(cpId);
			ProductOrderBuy pobOld = productOrderBuys.stream().filter(predicate).findAny().orElse(null);
			if (pobOld != null) {
				pobOld.getProductSelected().setChecked(false);
				removed = productOrderBuys.removeIf(predicate);
			}
			ProductOrderBuy pob = convocatory.getOrderBuy().getProductsOrder().stream().filter(x -> qcpId.equals(x.getProductSelected().getId())).findAny().orElse(null);
			if (pob == null) {
				pob = new ProductOrderBuy();
				pob.setProductSelected(qcp);
				pob.setOrderBuy(convocatory.getOrderBuy());
			}
			productOrderBuys.add(pob);
		}
		if (qcpByProduct != null) {
			cargarCotizacionesPorProducto(qcp.getConvocatoryProduct());
		}

	}

	/**
	 * Guarda la orden de compra
	 * 
	 * @author Andres Santacruz
	 * @datetime 13/02/2018 - 9:14:58 a. m.
	 *
	 */
	public void guardarOrdenDeCompra() {
		try {
			if (productOrderBuys.isEmpty()) {
				Messages.showMsg(Messages.MSG_WARNING, "form1", "Debe seleccionar por lo menos un producto!!");
				return;
			}
			iUtilConvocatoryService.saveOrderBuy(convocatory.getOrderBuy(), App.getUser(), productOrderBuys);
			loadConvocatories();
			Convocatory convocatory = convocatories.stream().filter(c -> this.convocatory.getId().equals(c.getId())).findAny().orElse(null);
			loadConvocatory(convocatory);
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	/**
	 * Se encarga de traer los productos cotizados por el tipo de producto
	 * 
	 * @author Andres Santacruz
	 * @datetime 15/02/2018 - 11:25:01 a. m.
	 *
	 * @param cp
	 */
	public void cargarCotizacionesPorProducto(ConvocatoryProduct cp) {
		qcpByProduct = allQuotationConvocatory.stream().filter(x -> x.getConvocatoryProduct().getId().equals(cpSelected.getId())).collect(Collectors.toList());
		cleanLstQcpByProduct();
	}

	/**
	 * Eliminar los productos que ya se encuentran en la lista
	 * 
	 * @author Andres Santacruz
	 * @datetime 15/02/2018 - 11:25:38 a. m.
	 *
	 */
	private void cleanLstQcpByProduct() {
		if (qcpByProduct != null) {
			for (ProductOrderBuy pob : productOrderBuys) {
				for (QuotationConvocatoryProduct qcp : qcpByProduct) {
					if (pob.getProductSelected().getId().equals(qcp.getId())) {
						qcpByProduct.remove(qcp);
						break;
					}
				}
			}
		}
		qcpSelected = null;
	}

	public void setStepCurrentOrderBuy() {
		List<StepOrder> stepsOrder = this.convocatory.getOrderBuy().getSteps();
		if (stepsOrder != null && !stepsOrder.isEmpty()) {
			Collections.sort(stepsOrder, GeneralComparator.StepOrderByDateRegisteredDesc);
			stepCurrent = stepsOrder.get(0);
		}
	}

	/**
	 * Carga los datos para mostrar el flujo de una convocatoria
	 * 
	 * @author Andres Santacruz
	 * @datetime 16/02/2018 - 7:58:58 a. m.
	 *
	 * @param convocatory
	 */
	public void cargarFlujoConvocatoria(Convocatory convocatory) {
		try {
			this.convocatory = convocatory;
			hideAllPanels();
			pnlFlujoConvocatoria = true;
			modificarFlujo = false;
			btnNoAprobarOrden = false;
			stepOCTCSelected = null;
			stepCurrent = null;
			stepOrder = new StepOrder();
			stepOrder.setUser(App.getUser());

			lstStepsUsers = iStepOrderConvocatoryTyPeConvocatoryUsersService.findAllEntityByIdUser(stepOrder.getUser().getId());
			lstStepsType = iStepOrderConvocatoryTyPeConvocatoryService.findEntityByIdTypeConvocatory(this.convocatory.getTypeConvocatory().getId());
			List<StepOrder> stepsOrder = this.convocatory.getOrderBuy().getSteps();
			Collections.sort(stepsOrder, GeneralComparator.StepOrderByDateRegisteredDesc);
			Collections.sort(this.convocatory.getOrderBuy().getComments(), GeneralComparator.CommentsOrderBuyByRegisteredDesc);

			// SI ESTA VACIA ENTONCES EL USUARIO NO TIENE ASIGNADOS PERMISOS
			if (lstStepsUsers.isEmpty()) {
				modificarFlujo = false;
				return;
			}

			// SE OBTIENE EL ULTIMO PASO REGISTRADO, LA LISTA YA ESTA ORDENADA POR FECHA
			setStepCurrentOrderBuy();
			Integer positionCurrent = stepCurrent.getStepOCTC().getPosition();

			// SE BUSCA SI EL USUARIO TIENE PERMISOS PARA ESE FLUJO
			for (StepOrderConvocatoryTyPeConvocatoryUsers s : lstStepsUsers) {
				Integer position = s.getStepOCTC().getPosition();
				if (positionCurrent == position) {
					modificarFlujo = true;
					btnNoAprobarOrden = !stepCurrent.getStepOCTC().getIsFirst();
					break;
				}
			}

			// SI EL FLUJO NO SE MODIFICA SE TERMINA
			if (!modificarFlujo) {
				return;
			}

			if (btnNoAprobarOrden) {
				// SE LLENA LA LISTA PARA PODER DEVOLVER LA ORDEN AL ANTERIOR PASO
				stepsOCTC = new ArrayList<>();
				for (StepOrderConvocatoryTypeConvocatory s : lstStepsType) {
					if (positionCurrent > s.getPosition()) {
						stepsOCTC.add(s);
						stepOCTCSelected = stepOCTCSelected == null ? s : stepOCTCSelected;
						if (stepOCTCSelected.getPosition() < s.getPosition()) {
							stepOCTCSelected = s;
						}
					}
				}
				Collections.sort(stepsOCTC, GeneralComparator.StepOrderConvocatoryTypeConvocatoryByPosition);
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Guarda el flujo de la orden, aprobada o no aprobada
	 * 
	 * @author Andres Santacruz
	 * @datetime 16/02/2018 - 4:56:05 p. m.
	 *
	 * @param aprobada
	 */
	public void aprobarPasoOrden(boolean aprobada) {
		try {
			stepOrder.setOrderBuy(convocatory.getOrderBuy());
			if (aprobada) {
				if (stepCurrent.getStepOCTC().getIsLast()) {
					stepOrder.setAprobed(true);
					stepOrder.setStepOCTC(stepCurrent.getStepOCTC());
					stepCurrent.setAprobed(true);
					iStepOrderService.mergeEntity(stepCurrent);
				} else {
					for (StepOrderConvocatoryTypeConvocatory s : lstStepsType) {
						Integer position = s.getPosition();
						if ((stepCurrent.getStepOCTC().getPosition() + 1) == position) {
							stepOrder.setAprobed(false);
							stepOrder.setStepOCTC(s);
							stepCurrent.setAprobed(true);
							iStepOrderService.mergeEntity(stepCurrent);
							break;
						}
					}
				}
			} else {
				stepOrder.setAprobed(false);
				stepOrder.setStepOCTC(stepOCTCSelected);
			}
			iStepOrderService.persistEntity(stepOrder);

			this.convocatory = iConvocatoryService.findEntityById(convocatory.getId());
			cargarFlujoConvocatoria(convocatory);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Este metodo se encarga de generar las cotizaciones hechas a una convocatoria
	 * 
	 * @author Andres Santacruz
	 * @datetime 16/02/2018 - 7:51:00 a. m.
	 *
	 * @param convocatory
	 */
	public void exportarCotizacionConvocatoriaExcel(Convocatory convocatory) {
		try {
			ReportGenerator rG = new ReportGenerator();
			int rowsHeader = 3;
			String[][] datos = new String[convocatory.getConvocatoryProducts().size() + rowsHeader][0];
			datos[0] = new String[] { "Convocatoria:", convocatory.getName() };
			datos[1] = new String[] { "Productos", "Proveedores" };
			List<String> nameProviders = new ArrayList<>();
			nameProviders.add("Productos");
			convocatory.getQuotations().stream().forEach(q -> nameProviders.add(q.getProvider().getName()));
			datos[2] = nameProviders.toArray(new String[nameProviders.size()]);

			List<QuotationConvocatoryProduct> allQuotationConvocatory = new ArrayList<>();
			convocatory.getQuotations().stream().forEach(q -> allQuotationConvocatory.addAll(q.getQuotationConvocatoryProducts()));

			for (int i = 0; i < convocatory.getConvocatoryProducts().size(); i++) {
				ConvocatoryProduct cp = convocatory.getConvocatoryProducts().get(i);
				List<QuotationConvocatoryProduct> qcpByProductTemp = allQuotationConvocatory.stream().filter(x -> x.getConvocatoryProduct().getId().equals(cp.getId())).collect(Collectors.toList());
				List<String> lstPrices = new ArrayList<>();
				lstPrices.add(cp.getProduct().getName());
				qcpByProductTemp.stream().forEach(x -> lstPrices.add(x.getPrice() + ""));
				String[] row = lstPrices.toArray(new String[lstPrices.size()]);
				datos[i + rowsHeader] = row;
			}
			rutaGenerada = rG.generarReporteExcel(datos, RI.Convocatoria.COTIZAR_PRODUCTOS);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}

	}

	/**
	 * Se encarga de agregar un nuevo comentario a la orden de compra y actualiza
	 * los comentarios en la orden de compra
	 * 
	 * @author Andres Santacruz
	 * @datetime 21/02/2018 - 10:45:58 a. m.
	 *
	 */
	public void agregarComentarioOrdenCompra() {
		try {
			OrderBuy ob = convocatory.getOrderBuy();
			CommentOrderBuy nuevoComentario = new CommentOrderBuy(ob.getComment(), App.getUser(), convocatory.getOrderBuy());
			iCommentOrderBuyService.persistEntity(nuevoComentario);
			ob.setComments(iCommentOrderBuyService.findAllEntityByIdOrderBuy(ob.getId()));
			Collections.sort(ob.getComments(), GeneralComparator.CommentsOrderBuyByRegisteredDesc);
			ob.setComment(null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Se encarga de agregar un nuevo comentario al producto de la orden de compra y
	 * actualiza los comentarios en ese producto
	 * 
	 * @author Andres Santacruz
	 * @datetime 21/02/2018 - 10:45:58 a. m.
	 *
	 */
	public void agregarComentarioProducto() {
		try {
			CommentProductOrderBuy nuevoComentario = new CommentProductOrderBuy(pobSelected.getComment(), pobSelected, App.getUser());
			iCommentProductOrderBuyService.persistEntity(nuevoComentario);
			pobSelected.setComments(iCommentProductOrderBuyService.findAllEntityByIdProductOrderBuy(pobSelected.getId()));
			Collections.sort(this.pobSelected.getComments(), GeneralComparator.CommentsProductOrderBuyByRegisteredDesc);
			pobSelected.setComment(null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Retorna true si el paso de la orden ha sido aprobada es aprobada cuando el
	 * paso esta en true y este se encuenta en la ultima posicion del flujo del
	 * proceso
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/02/2018 - 4:22:00 p. m.
	 *
	 * @return
	 */
	public boolean ordenDeCompraEsAprobada() {
		return stepCurrent.isAprobed() && stepCurrent.getStepOCTC().getIsLast();
	}

	/**
	 * Metodo encargado de generar el StreamContent dependiendo de una ruta de
	 * archivo
	 * 
	 * @author Andres Santacruz
	 * @datetime 16/02/2018 - 7:50:22 a. m.
	 *
	 * @return
	 */
	public StreamedContent getDownload() {
		try {
			File file = new File(rutaGenerada);
			InputStream input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			return new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
		} catch (FileNotFoundException e) {
			UtilWeb.printError(LOG, e);
			return null;
		}
	}

	/**
	 * Este metodo se encarga de eliminar los archivos temporales generados, una vez
	 * terminada la descarga.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:21:32 a. m.
	 */
	public void onFinishDownload() {
		if (rutaGenerada != null) {
			ReportGenerator.deleteFileTemps(rutaGenerada);
			rutaGenerada = null;
		}
	}

	public void limpiar() {
		loadConvocatory(convocatory);
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}

	public List<Convocatory> getConvocatories() {
		return convocatories;
	}

	public List<ProductOrderBuy> getProductOrderBuys() {
		return productOrderBuys;
	}

	public void setProductOrderBuys(List<ProductOrderBuy> productOrderBuys) {
		this.productOrderBuys = productOrderBuys;
	}

	public List<QuotationConvocatoryProduct> getQcpByProduct() {
		return qcpByProduct;
	}

	public ConvocatoryProduct getCpSelected() {
		return cpSelected;
	}

	public void setCpSelected(ConvocatoryProduct cpSelected) {
		this.cpSelected = cpSelected;
	}

	public QuotationConvocatoryProduct getQcpSelected() {
		return qcpSelected;
	}

	public void setQcpSelected(QuotationConvocatoryProduct qcpSelected) {
		this.qcpSelected = qcpSelected;
	}

	public ProductOrderBuy getPobSelected() {
		return pobSelected;
	}

	public void setPobSelected(ProductOrderBuy pobSelected) {
		this.pobSelected = pobSelected;
		if (this.pobSelected != null) {
			this.pobSelected.setComment(null);
			Collections.sort(this.pobSelected.getComments(), GeneralComparator.CommentsProductOrderBuyByRegisteredDesc);
		}
	}

	public String getPrimeraOpcion() {
		return PRIMERA_OPCION;
	}

	public String getSegundaOpcion() {
		return SEGUNDA_OPCION;
	}

	public String getTerceraOpcion() {
		return TERCERA_OPCION;
	}

	public boolean isPnlFlujoConvocatoria() {
		return pnlFlujoConvocatoria;
	}

	public boolean isPnlAnalisisConvocatoria() {
		return pnlAnalisisConvocatoria;
	}

	public boolean isPnlListadoConvocatorias() {
		return pnlListadoConvocatorias;
	}

	public StepOrder getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(StepOrder stepOrder) {
		this.stepOrder = stepOrder;
	}

	public boolean isModificarFlujo() {
		return modificarFlujo;
	}

	public boolean isBtnNoAprobarOrden() {
		return btnNoAprobarOrden;
	}

	public List<StepOrderConvocatoryTypeConvocatory> getStepsOCTC() {
		return stepsOCTC;
	}

	public StepOrderConvocatoryTypeConvocatory getStepOCTCSelected() {
		return stepOCTCSelected;
	}

	public void setStepOCTCSelected(StepOrderConvocatoryTypeConvocatory stepOCTCSelected) {
		this.stepOCTCSelected = stepOCTCSelected;
	}

	public List<StepOrderConvocatoryTypeConvocatory> getLstTemp() {
		return lstStepsType;
	}

	public StepOrder getStepCurrent() {
		return stepCurrent;
	}

}
