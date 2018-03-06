package com.proinsalud.sistemas.web.application.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.proinsalud.sistemas.core.general.model.OptionFavorite;
import com.proinsalud.sistemas.core.general.service.IOptionFavoriteService;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.model.Parameter;
import com.proinsalud.sistemas.core.security.service.IParameterService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * Es el bean de session de la aplicacion
 *
 * @author Andres Santacruz
 * @datetime 4/10/2017 - 8:16:50 a. m.
 */
@ManagedBean(name = "sessionBean")
@Component
@SessionScope
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 4777763747374267400L;
	private static final Log LOG = App.getLogger(SessionBean.class);

	@Autowired
	private IOptionFavoriteService iOptionFavoriteService;

	@Autowired
	private IParameterService iParameterService;

	private List<OptionFavorite> optionFavoriteList;

	private Parameter parameter;
	private Option optionSelectedApp;

	public SessionBean() {
	}

	@PostConstruct
	public void init() {
		optionFavoriteList = new ArrayList<>();
	}

	public void loadVars() {
		loadParameter();
		loadOptionFavorite();
	}

	public void loadOptionFavorite() {
		try {
			this.optionFavoriteList = iOptionFavoriteService.findAllEntityByIdUser(App.getUser().getId());
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadParameter() {
		try {
			List<Parameter> parameterList = iParameterService.findAllEntity();
			parameter = parameterList.get(0);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public Option getOptionSelectedApp() {
		return optionSelectedApp;
	}

	public void setOptionSelectedApp(Option optionSelectedApp) {
		this.optionSelectedApp = optionSelectedApp;
	}

	public List<OptionFavorite> getOptionFavoriteList() {
		return optionFavoriteList;
	}

	public Parameter getParameter() {
		return parameter;
	}

}
