package com.proinsalud.sistemas.web.util.component;

import java.io.IOException;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.web.application.bean.SessionBean;
import com.proinsalud.sistemas.web.util.App;

/**
 * Es el componente que crea el menu de la cabecera
 * 
 * @author Andres Santacruz
 * @datetime 28/12/2017 - 11:52:58 a. m.
 *
 */
@Component
@FacesComponent(createTag = true, tagName = "menuHeaderComponent", namespace = "http://proinsalud.co/sistemas/tags")
public class MenuHeaderComponent extends UIComponentBase {

	private static final Log LOG = App.getLogger(MenuHeaderComponent.class);

	@Autowired
	private SessionBean sessionBean;

	public MenuHeaderComponent() {
	}

	@Override
	public String getFamily() {
		return "Greeting";
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {

		// HttpServletRequest req = (HttpServletRequest)
		// context.getExternalContext().getRequest();
		// String url = req.getRequestURL().toString();
		// String temp = url.substring(0, url.length() - req.getRequestURI().length()) +
		// req.getContextPath() + "/secured/";
		// String appModule = url.substring(temp.length(), url.length());
		// if (appModule.equals("")) {
		// return;
		// }
		// String[] data = appModule.split("/");
		// if (data.length <= 1) {
		// return;
		// }
		// String app = data[0];
		// String module = data[1];

		// List<Option> options = App.getOptionsUser();
		// Option option = App.searchOptionModule(app, module, options, null);

		// ExternalContext ext = context.getExternalContext();
		// Map<String, String> requestHeader = ext.getRequestHeaderMap();
		// String urlRefered = requestHeader.get("referer");

		// ext = context.getExternalContext();
		// URI uri;
		// try {
		// uri = new URI(ext.getRequestScheme(), null, ext.getRequestServerName(),
		// ext.getRequestServerPort(), ext.getRequestContextPath(), null, null);
		// temp = uri.toASCIIString();
		// } catch (URISyntaxException e) {
		// e.printStackTrace();
		// LOG.error("Error en URI: " + e.getMessage());
		// }
		// Option optionUser = (Option) getAttributes().get("option");
		try {
			ResponseWriter writer = context.getResponseWriter();
			App.initInjectionAutowired(this);
			Option option = sessionBean.getOptionSelectedApp();
			if (option != null && option.getOptions() != null && option.getOptionFather() != null) {
				Option optionFather = option.getLevel().getLevelPos() == 2 ? option : option.getOptionFather();
				createMenuRecursive(optionFather.getOptions(), writer);
			}
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void createMenuRecursive(List<Option> options, ResponseWriter writer) throws IOException {
		for (Option option : options) {
			if (option.getOptions().isEmpty()) {
				writer.startElement("li", this);
				writer.write("<a href=\"" + option.getDetail() + "\">" + option.getName() + "</a>");
				writer.endElement("li");
			} else {
				writer.startElement("li", this);
				writer.write("<a href=\"#\">" + option.getName() + "<span class=\"caret\"></span></a>");
				writer.write("<ul class=\"dropdown-menu\">");
				createMenuRecursive(option.getOptions(), writer);
				writer.endElement("ul");
				writer.endElement("li");
			}
		}
	}
}
