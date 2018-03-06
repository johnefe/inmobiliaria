package com.proinsalud.sistemas.web.convocatory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.model.Product;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.convocatory.model.Quotation;
import com.proinsalud.sistemas.core.convocatory.model.TypeConvocatory;
import com.proinsalud.sistemas.core.convocatory.service.IConvocatoryService;
import com.proinsalud.sistemas.core.convocatory.service.IProviderService;
import com.proinsalud.sistemas.core.convocatory.service.ITypeConvocatoryService;
import com.proinsalud.sistemas.core.convocatory.service.IUtilConvocatoryService;
import com.proinsalud.sistemas.core.util.enums.StateConvocatoryEnum;
import com.proinsalud.sistemas.core.util.enums.TypeConvocatoryEnum;
import com.proinsalud.sistemas.core.warehouse.model.Medicine;
import com.proinsalud.sistemas.core.warehouse.model.ProductWarehouse;
import com.proinsalud.sistemas.core.warehouse.service.IMedicineService;
import com.proinsalud.sistemas.core.warehouse.service.IProductWarehouseService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 5:42:54 p. m.
 *
 */
@ManagedBean
@ViewScoped
public class ConvocatoriaBean implements Serializable {

	private static final long serialVersionUID = 4426916598128888472L;
	private static final Log LOG = App.getLogger(ConvocatoriaBean.class);

	@Autowired
	private IConvocatoryService iConvocatoryService;

	@Autowired
	private IProviderService iProviderService;

	@Autowired
	private IUtilConvocatoryService iUtilConvocatoryService;

	@Autowired
	private ITypeConvocatoryService iTypeConvocatoryService;

	@Autowired
	private IMedicineService iMedicineService;

	@Autowired
	private IProductWarehouseService iProductWarehouseService;

	private List<Convocatory> convocatories;
	private List<Product> products;
	private List<Provider> providers;
	private List<ConvocatoryProduct> convocatoryProducts;
	private List<TypeConvocatory> typeConvocatories;
	private DualListModel<ConvocatoryProduct> dualListConvocatoryProducts;
	private DualListModel<Quotation> dualListQuotation;

	private Convocatory convocatory;
	private ConvocatoryProduct convocatoryProduct;

	private boolean showForm;
	private boolean showInfoConvocatory;

	private Date minDate;

	public ConvocatoriaBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		loadConvocatories();
		minDate = new Date();
	}

	private void initVars() {
		convocatory = new Convocatory();
		convocatoryProduct = new ConvocatoryProduct();
		providers = new ArrayList<>();
		convocatoryProducts = new ArrayList<>();
		products = new ArrayList<>();
	}

	private void loadConvocatories() {
		try {
			convocatories = iConvocatoryService.findAllEntity();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	private void loadProviders(List<Quotation> lstRegistered) {
		providers = iProviderService.findAllEntity();
		if (lstRegistered != null && !lstRegistered.isEmpty()) {
			for (Quotation q : lstRegistered) {
				for (Provider p : providers) {
					if (q.getProvider().getId().equals(p.getId())) {
						providers.remove(p);
						break;
					}
				}
			}
		}
		List<Quotation> quotations = new ArrayList<>();
		providers.forEach(p -> quotations.add(new Quotation(convocatory, p)));
		List<Quotation> target = lstRegistered == null ? new ArrayList<>() : lstRegistered;
		dualListQuotation = new DualListModel<Quotation>(quotations, target);
	}

	private void loadTypeConvocatories() {
		try {
			typeConvocatories = iTypeConvocatoryService.findAllEntity();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void back() {
		showForm = false;
		showInfoConvocatory = false;
		initVars();
	}

	public void showInfoConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
		showInfoConvocatory = true;
	}

	public void initForm() {
		try {
			initVars();
			loadTypeConvocatories();
			loadProviders(null);
			showForm = true;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void saveProduct() {
		try {
			Long productoId = convocatoryProduct.getProduct().getIdElement();
			Predicate<ConvocatoryProduct> predicate = p -> p.getProduct().getIdElement().equals(productoId);
			boolean remove = convocatoryProducts.removeIf(predicate);
			convocatoryProduct.setConvocatory(convocatory);
			convocatoryProducts.add(0, convocatoryProduct);
			if (!remove) {
				products.remove(convocatoryProduct.getProduct());
			}
			convocatoryProduct = new ConvocatoryProduct();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	/**
	 * Quita el producto de la tabla y lo almacena en la lista productos
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 8:20:35 a. m.
	 *
	 */
	public void quitProduct() {
		try {
			convocatoryProducts.remove(convocatoryProduct);
			products.add(convocatoryProduct.getProduct());
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Guarda la convocatoria
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 8:20:21 a. m.
	 *
	 */
	public void saveConvocatory() {
		try {
			if (convocatoryProducts.isEmpty()) {
				Messages.showMsg(Messages.MSG_WARNING, "form1", "Debe seleccionar al menos un producto.");
				return;
			}
			List<Quotation> quotationsTarget = dualListQuotation.getTarget();
			if (quotationsTarget.isEmpty()) {
				Messages.showMsg(Messages.MSG_WARNING, "form1", "Debe seleccionar al menos un proveedor.");
				return;
			}
			List<Product> products = new ArrayList<>();
			convocatoryProducts.forEach(cp -> products.add(cp.getProduct()));
			iUtilConvocatoryService.saveConvocatory(convocatory, convocatoryProducts, quotationsTarget, products);
			loadConvocatories();
			showForm = false;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	/**
	 * Elimina la convocatoria se eliminan relaciones cotizaciones y productos de la
	 * convocatoria
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 8:19:29 a. m.
	 *
	 * @param convocatory
	 */
	public void deleteConvocatoria(Convocatory convocatory) {
		try {
			if (convocatory != null && convocatory.getId() != null) {
				iUtilConvocatoryService.deleteConvocatory(convocatory);
				initVars();
				loadConvocatories();
				Messages.showMsg(Messages.MSG_INFO, "form1", Messages.getProperty(Messages.msgConvocatoriaEliminada));
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	/**
	 * Cambia el estado de la convocatoria a PUBLICADA
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 8:19:04 a. m.
	 *
	 * @param convocatory
	 */
	public void publicarConvocatoria(Convocatory convocatory) {
		try {
			convocatory.setState(StateConvocatoryEnum.Publicada);
			iConvocatoryService.mergeEntity(convocatory);
			loadConvocatories();
			Messages.showMsg(Messages.MSG_INFO, "form1", Messages.getProperty(Messages.msgConvocatoriaPublicada));
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	/**
	 * Retorna true si la convocatoria tiene cotizaciones realizadas por los
	 * proveedores
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 11:35:43 a. m.
	 *
	 * @param convocatory
	 * @return
	 */
	public boolean convocatoriaTieneCotizaciones(Convocatory convocatory) {
		for (Quotation q : convocatory.getQuotations()) {
			if (!q.getQuotationConvocatoryProducts().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public void clean() {
		initForm();
	}

	public void cancel() {
		showForm = false;
	}

	/**
	 * Carga la lista de productos cuando selecciona el tipo de convocatoria en la
	 * vista
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 8:17:57 a. m.
	 *
	 */
	public void loadListProducts() {
		try {
			convocatory.setTypeConvocatory((TypeConvocatory) UtilWeb.loadObject(typeConvocatories, convocatory.getTypeConvocatory()));
			products = new ArrayList<>();
			convocatoryProducts = new ArrayList<>();
			convocatoryProduct = new ConvocatoryProduct();
			if (convocatory.getTypeConvocatory().getName().equals(TypeConvocatoryEnum.Medicamentos.name())) {
				List<Medicine> lst = iMedicineService.findAllEntity();
				lst.forEach(i -> products.add(new Product(i.getName(), i.getId(), i)));
			} else if (convocatory.getTypeConvocatory().getName().equals(TypeConvocatoryEnum.Almacen.name())) {
				List<ProductWarehouse> lst2 = iProductWarehouseService.findAllEntity();
				lst2.forEach(i -> products.add(new Product(i.getName(), i.getId(), i)));
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Este metodo es llamado desde la vista cuando se selecciona la opcion editar
	 * convocatoria
	 * 
	 * @author Andres Santacruz
	 * @datetime 23/01/2018 - 8:17:32 a. m.
	 *
	 * @param convocatory
	 */
	public void loadConvocatoryForEdition(Convocatory convocatory) {
		try {
			this.convocatory = convocatory;
			loadTypeConvocatories();
			loadListProducts();
			convocatoryProducts = convocatory.getConvocatoryProducts();
			List<ConvocatoryProduct> temp = new ArrayList<>();
			for (ConvocatoryProduct cp : convocatoryProducts) {
				for (Product p : products) {
					if (cp.getProduct().getIdElement().equals(p.getIdElement())) {
						products.remove(p);
						temp.add(new ConvocatoryProduct(null, convocatory, p, cp.getQuantity(), cp.getPrice()));
						break;
					}
				}
			}
			convocatoryProducts = temp;
			loadProviders(convocatory.getQuotations());
			convocatoryProduct = new ConvocatoryProduct();
			showForm = true;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public boolean showEdit(Convocatory convocatory) {
		if (convocatory.getState() != StateConvocatoryEnum.Creada) {
			return false;
		}
		return !convocatoriaTieneCotizaciones(convocatory);
	}

	public void initConvocatoryProduct() {
		convocatoryProduct = new ConvocatoryProduct();
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}

	public DualListModel<ConvocatoryProduct> getDualListConvocatoryProducts() {
		return dualListConvocatoryProducts;
	}

	public void setDualListConvocatoryProducts(DualListModel<ConvocatoryProduct> dualListConvocatoryProducts) {
		this.dualListConvocatoryProducts = dualListConvocatoryProducts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public List<ConvocatoryProduct> getConvocatoryProducts() {
		return convocatoryProducts;
	}

	public void setConvocatoryProducts(List<ConvocatoryProduct> convocatoryProducts) {
		this.convocatoryProducts = convocatoryProducts;
	}

	public ConvocatoryProduct getConvocatoryProduct() {
		return convocatoryProduct;
	}

	public void setConvocatoryProduct(ConvocatoryProduct convocatoryProduct) {
		this.convocatoryProduct = convocatoryProduct;
	}

	public DualListModel<Quotation> getDualListQuotation() {
		return dualListQuotation;
	}

	public void setDualListQuotation(DualListModel<Quotation> dualListQuotation) {
		this.dualListQuotation = dualListQuotation;
	}

	public List<Convocatory> getConvocatories() {
		return convocatories;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public Date getMinDate() {
		return minDate;
	}

	public List<TypeConvocatory> getTypeConvocatories() {
		return typeConvocatories;
	}

	public boolean isShowInfoConvocatory() {
		return showInfoConvocatory;
	}

}
