package com.proinsalud.sistemas.web.convocatory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.convocatory.model.ConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.model.Product;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.convocatory.model.Quotation;
import com.proinsalud.sistemas.core.convocatory.model.QuotationConvocatoryProduct;
import com.proinsalud.sistemas.core.convocatory.service.IConvocatoryService;
import com.proinsalud.sistemas.core.convocatory.service.IProviderService;
import com.proinsalud.sistemas.core.convocatory.service.IQuotationConvocatoryProductService;
import com.proinsalud.sistemas.core.convocatory.service.IQuotationService;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.LoadFileExcel;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;
import com.proinsalud.sistemas.web.util.reports.RI;
import com.proinsalud.sistemas.web.util.reports.ReportGenerator;

/**
 * @author Andres Santacruz
 * @datetime 10/01/2018 - 5:08:06 p. m.
 *
 */
@ManagedBean
@ViewScoped
public class CotizarProductosBean implements Serializable {

	private static final long serialVersionUID = 1472720559157941414L;
	private static final Log LOG = App.getLogger(CotizarProductosBean.class);

	@Autowired
	private IQuotationService iQuotationService;

	@Autowired
	private IProviderService iProviderService;

	@Autowired
	private IQuotationConvocatoryProductService iQuotationConvocatoryProductService;

	@Autowired
	private IConvocatoryService iConvocatoryService;

	private List<Quotation> quotations;
	private List<QuotationConvocatoryProduct> quotationConvocatoryProducts;
	private Quotation quotation;
	private Provider provider;
	private List<Convocatory> convocatories;
	private List<Provider> providers;
	private boolean editTbl;
	private boolean isUserSystem;
	private String rutaGenerada;

	public CotizarProductosBean() {
	}

	@PostConstruct
	private void init() {
		try {
			App.initInjectionAutowired(this);
			load();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	private void load() {
		loadProvider();
		if (isUserSystem) {
			convocatories = iConvocatoryService.findAllEntity();
			Collections.sort(convocatories, (Convocatory p1, Convocatory p2) -> p2.getRegistered().compareTo(p1.getRegistered()));
		} else {
			loadQuotations();
		}
	}

	public void loadProviders() {
		try {
			providers = iProviderService.findAllEntity();
			quotations = new ArrayList<>();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	private void loadProvider() {
		try {
			Person p = App.getUser().getPerson();
			provider = iProviderService.findEntityByIdPerson(p.getId());
			isUserSystem = provider == null;
			if (isUserSystem) {
				loadProviders();
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void saveQuotationConvocatory() {
		try {
			boolean ok = false;
			for (QuotationConvocatoryProduct qcp : quotationConvocatoryProducts) {
				if (qcp.getPrice() > 0) {
					ok = true;
					break;
				}
			}
			if (!ok) {
				Messages.showMsg(Messages.MSG_WARNING, "form1", "Debe cotizar como minimo un producto!");
				return;
			}
			convocatoriaVigente();
			if (editTbl) {
				if (quotation.getQuotationConvocatoryProducts().isEmpty()) {
					iQuotationConvocatoryProductService.persistEntity(quotationConvocatoryProducts);
				} else {
					iQuotationConvocatoryProductService.mergeEntity(quotationConvocatoryProducts);
				}
				loadQuotations();
			} else {
				Messages.showMsg(Messages.MSG_WARNING, "form1", "La convocatoria se encuentra cerrada!");
				return;
			}

			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void cancel() {
		quotation = null;
	}

	public void loadQuotations() {
		try {
			quotations = new ArrayList<>();
			if (provider != null) {
				quotations = iQuotationService.findEntityByIdProvider(provider.getId());
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void loadQuotationsProducts() {
		try {
			convocatoriaVigente();
			if (quotation.getQuotationConvocatoryProducts().isEmpty()) {
				quotationConvocatoryProducts = new ArrayList<>();
				quotation.getConvocatory().getConvocatoryProducts().forEach(cp -> quotationConvocatoryProducts.add(new QuotationConvocatoryProduct(quotation, cp, cp.getQuantity(), 0.0)));
			} else {
				quotationConvocatoryProducts = quotation.getQuotationConvocatoryProducts();
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void convocatoriaVigente() {
		editTbl = quotation.getConvocatory().getExpiration().after(new Date());
	}

	public void exportarExcel() {
		try {
			ReportGenerator rG = new ReportGenerator();
			int rowsHeader = 2;
			String[][] datos = new String[quotationConvocatoryProducts.size() + rowsHeader][0];
			datos[0] = new String[] { "", "", "", "Proveedor: " + provider.getName() };
			datos[1] = new String[] { "Codígo", "Producto", "Cantidad", "Valor Unitario" };
			for (int i = 0; i < quotationConvocatoryProducts.size(); i++) {
				QuotationConvocatoryProduct qcp = quotationConvocatoryProducts.get(i);
				ConvocatoryProduct cp = qcp.getConvocatoryProduct();
				Product p = cp.getProduct();
				String[] row = new String[] { cp.getId() + "", p.getName(), cp.getQuantity() + "", qcp.getPrice() + "" };
				datos[i + rowsHeader] = row;
			}
			rutaGenerada = rG.generarReporteExcel(datos, RI.Convocatoria.COTIZAR_PRODUCTOS);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
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
		ReportGenerator.deleteFileTemps(rutaGenerada);
		rutaGenerada = null;
	}

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

	public void loadDataExcel(FileUploadEvent event) {
		LoadFileExcel poiUtils = new LoadFileExcel();
		if (event != null && event.getFile() != null) {
			UploadedFile uploadFile = event.getFile();
			if (uploadFile != null && uploadFile.getFileName() != null && !uploadFile.getFileName().equals("")) {
				try {
					ArrayList<ArrayList<String[]>> arrayDatosExcel = poiUtils.readExcelFileToArray(uploadFile);
					List<QuotationConvocatoryProduct> lstQCP = new ArrayList<>();
					for (ArrayList<String[]> hoja : arrayDatosExcel) {
						for (int i = 2; i < hoja.size(); i++) {
							String[] row = hoja.get(i);
							Double price = Double.parseDouble(row[3]);
							Long codigo = new Long(row[0]);

							if (codigo != null) {
								ConvocatoryProduct cp = null;
								for (int j = 0; j < quotationConvocatoryProducts.size(); j++) {
									ConvocatoryProduct cpTemp = quotationConvocatoryProducts.get(j).getConvocatoryProduct();
									if (cpTemp.getId().equals(codigo)) {
										cp = cpTemp;
										break;
									}
								}
								if (cp != null) {
									QuotationConvocatoryProduct q = new QuotationConvocatoryProduct();
									q.setConvocatoryProduct(cp);
									q.setPrice(price);
									q.setQuantity(cp.getQuantity());
									q.setQuotation(quotation);
									lstQCP.add(q);
								}
							}
						}
					}
					// iQuotationConvocatoryProductService.persistEntity(lstQCP);
					// quotation = iQuotationService.findEntityById(quotation.getId());
					quotation.setQuotationConvocatoryProducts(lstQCP);
					loadQuotationsProducts();
					Messages.showMsg(Messages.MSG_INFO, "form1", "El archivo se cargo correctamente.");
				} catch (Exception e) {
					UtilWeb.printError(LOG, e);
					Messages.showMsg(Messages.MSG_ERROR, "form1", "El archivo no pudo ser cargado debido a un error.");
				}
			}
		}
	}

	// public boolean hasQuotationsConvocatoryProducts() {
	// if (quotation.getQuotationConvocatoryProducts().isEmpty()) {
	// return false;
	// }
	// if (quotation.getQuotationConvocatoryProducts().get(0).getId() == null) {
	// return false;
	// }
	// return true;
	// }

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	public List<QuotationConvocatoryProduct> getQuotationConvocatoryProducts() {
		return quotationConvocatoryProducts;
	}

	public Provider getProvider() {
		return provider;
	}

	public boolean isEditTbl() {
		return editTbl;
	}

	public List<Convocatory> getConvocatories() {
		return convocatories;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public boolean isUserSystem() {
		return isUserSystem;
	}

}
