package com.proinsalud.sistemas.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.jsf.FacesContextUtils;

import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.model.UserOption;
import com.proinsalud.sistemas.core.security.model.Users;

/**
 * Es la clase con los metodos principales de la aplicacion
 * 
 * @author Andres Santacruz
 * @datetime 3/01/2018 - 8:39:01 a. m.
 *
 */
public class App implements Serializable {

	private static final long serialVersionUID = -6809297720099762873L;

	/**
	 * Esta clase inicializa la inyeccion de dependencias para que se pueda combinar
	 * jsf con spring
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:06:34 a. m.
	 *
	 * @param obj
	 */
	public static void initInjectionAutowired(Object obj) {
		FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(obj);
	}

	/**
	 * Es la clase que retorna el logger
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:06:20 a. m.
	 *
	 * @param c
	 * @return
	 */
	public static Log getLogger(@SuppressWarnings("rawtypes") Class c) {
		// private static final Log LOG = LogFactory.getLog(CustomFilter.class);
		// private static final Logger LOG =
		// Logger.getLogger(UsuariosSistemaBean.class);
		return LogFactory.getLog(c);
	}

	/**
	 * Retorna la clase que administra la autenticacion
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:06:03 a. m.
	 *
	 * @return
	 */
	private static Authentication getAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			Object principal = auth.getPrincipal();
			if (principal instanceof UserDetailsHelper) {
				return auth;
			}
		}
		return null;
	}

	/**
	 * Retorna true si el usuario esta autenticado en caso contrario false
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:05:44 a. m.
	 *
	 * @return
	 */
	public static boolean isAuthenticated() {
		Authentication auth = getAuthenticated();
		return auth != null;
	}

	/**
	 * Retorna la clase que contiene la informacion de autenticacion
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:05:17 a. m.
	 *
	 * @return
	 */
	private static UserDetailsHelper getUserDetailsHelper() {
		Authentication auth = getAuthenticated();
		if (auth != null) {
			Object principal = auth.getPrincipal();
			UserDetailsHelper userDetailsHelper = (UserDetailsHelper) principal;
			return userDetailsHelper;
		}
		return null;
	}

	/**
	 * Retorna el usuario logeado
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:05:06 a. m.
	 *
	 * @return
	 */
	public static Users getUser() {
		UserDetailsHelper userDetailsHelper = getUserDetailsHelper();
		if (userDetailsHelper != null) {
			return userDetailsHelper.getUserEntity();
		}
		return null;
	}

	/**
	 * Retorna las opciones del usuario
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:04:54 a. m.
	 *
	 * @return
	 */
	public static List<Option> getOptionsUser() {
		List<Option> options = new ArrayList<>();
		Users user = getUser();
		if (user != null) {
			for (UserOption userOption : user.getUserOptions()) {
				options.add(userOption.getOption());
			}
		}
		return options;
	}

	/**
	 * Este metodo retorna la opcion si el usuario tiene permiso a esa opcion de lo
	 * contrario retorna null
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 8:44:10 a. m.
	 *
	 * @param url
	 * @return
	 */
	public static Option hasPermission(List<String> url) {
		return hasPermissionUser(getOptionsUser(), url);
	}

	/**
	 * Este metodo retorna la opcion si el usuario tiene permiso a esa opcion de lo
	 * contrario retorna null
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 8:43:32 a. m.
	 *
	 * @param options
	 * @param url
	 * @return
	 */
	private static Option hasPermissionUser(List<Option> options, List<String> url) {
		for (Option option : options) {
			if (url != null && !url.isEmpty()) {
				String parteUrl = url.get(0);
				if (option.getDetail().equals(parteUrl)) {
					List<String> url2 = new ArrayList<>(url);
					url2.remove(0);
					if (!option.getOptions().isEmpty() && !url2.isEmpty()) {
						return hasPermissionUser(option.getOptions(), url2);
					}
					return option;
				}
			}
		}
		return null;
	}
}
