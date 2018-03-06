package com.proinsalud.sistemas.web.convocatory;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.convocatory.service.IProviderService;
import com.proinsalud.sistemas.core.convocatory.service.IUtilConvocatoryService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;

/**
 * @author Mauricio Pinchao
 * @datetime 11/01/2018 - 11:15:19 a. m.
 */
@ManagedBean
@ViewScoped
public class ProveedoresBean implements Serializable {

	private static final long serialVersionUID = 4681915490576814756L;
	private static final Log LOG = App.getLogger(ProveedoresBean.class);

	@Autowired
	private IProviderService iProviderService;

	@Autowired
	private IUtilConvocatoryService iUtilConvocatoryService;

	private List<Provider> providerList;
	private Provider provider = null;

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		initData();
	}

	public void initData() {
		try {
			providerList = iProviderService.findAllEntity();
			// provider = new Provider();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void nuevo() {
		provider = new Provider();
	}

	public void saveProvider() {
		try {
			iUtilConvocatoryService.saveProvider(provider);
			providerList = iProviderService.findAllEntity();
			provider = null;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void loadProvider(Long idProvider) {
		try {
			provider = iProviderService.findEntityById(idProvider);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	public void deleteProvider(Provider provider) {
		try {
			iProviderService.deleteEntity(provider);
			providerList = iProviderService.findAllEntity();
			provider = null;
		} catch (Exception e) {

		}
	}

	public void cancel() {
		provider = null;
	}

	public List<Provider> getProviderList() {
		return providerList;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProviderList(List<Provider> providerList) {
		this.providerList = providerList;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
}
