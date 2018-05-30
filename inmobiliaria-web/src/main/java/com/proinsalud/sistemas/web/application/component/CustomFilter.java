package com.proinsalud.sistemas.web.application.component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.filter.GenericFilterBean;

import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.web.application.bean.SessionBean;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Constants;

/**
 * @author Andres Santacruz
 * @datetime 9/11/2017 - 11:31:14 a. m.
 *
 */
public class CustomFilter extends GenericFilterBean implements Serializable {

	private static final long serialVersionUID = -4349941450537389102L;
	private static final Log LOG = App.getLogger(CustomFilter.class);
	
	@Autowired
	@Qualifier(value = Constants.REF_SESSION_BEAN)
	private SessionBean sessionBean;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI();
		if (App.isAuthenticated()) {
			try {
				if (existeOpcion(url)) {
					chain.doFilter(req, res);
				} else {
					// res.sendRedirect("/proinsalud-web/secured/error/acceso_denegado.xhtml");
					res.sendError(403);
				}
			} catch (ServletException e) {
				LOG.error("ERROR ServletException: " + e.getMessage() + " Pagina: " + url);
			} catch (IOException e) {
				LOG.error("ERROR IOException: " + e.getMessage() + " Pagina: " + url);
			}
		} else {
			LOG.info("Recurso: " + url);
			chain.doFilter(request, response);
		}
	}

	public boolean existeOpcion(String requestURL) {
		LOG.info("URL: " + requestURL.toLowerCase());
		if (requestURL.toLowerCase().contains("javax.faces.resource")) {
			return true;
		} else if (requestURL.toLowerCase().contains("/inmobiliaria-web/resources")) {
			return true;
		} else if (requestURL.toLowerCase().equals("/inmobiliaria-web/secured/bienvenido")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/index.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/buscar_inmuebles.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/detalle_inmueble.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/inmueble/inmueble.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/inmueble")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/arriendo.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/anticres.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/venta.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/arriendo")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/anticres")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/venta")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/login")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/register")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/inicio")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/buscar_inmuebles")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/detalle_inmueble")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/logout")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/index.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/errors")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/error/acceso_denegado.xhtml")
				|| requestURL.toLowerCase().equals("/inmobiliaria-web/secured/error/error_page.xhtml")) {
			sessionBean.setOptionSelectedApp(null);
			return true;
		} else {
			String url = requestURL.split("/inmobiliaria-web/secured/")[1];
			url = url.endsWith("index.xhtml") ? url.replace("index.xhtml","" ) : url;
			List<String> urlList = Arrays.asList(url.split("/"));
			Option option = App.hasPermission(urlList);
			sessionBean.setOptionSelectedApp(option);			
			return option != null;
		}
	}
}
