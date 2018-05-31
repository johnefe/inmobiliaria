package com.proinsalud.sistemas.web.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Constants;
import com.proinsalud.sistemas.web.util.ViewConstants;
import com.sun.faces.action.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private HttpServletRequest request;

	@GetMapping("/login")
	public String login2(ModelMap model) {
		String page = ViewConstants.VIEW_LOGIN;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
		}
		if (App.isAuthenticated()) {
			page = ViewConstants.REDIRECT;
		}
		return page;
	}
	@GetMapping("/register")
	public String register(ModelMap model) {
		String page = ViewConstants.VIEW_REGISTER;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
		}
		if (App.isAuthenticated()) {
			page = ViewConstants.REDIRECT;
		}
		return page;
	}
	
	@GetMapping("/buscar_inmuebles")
	public String buscar_inmuebles(ModelMap model) {
		String page = ViewConstants.VIEW_SEARCH;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
		}
		if (App.isAuthenticated()) {
			page = ViewConstants.VIEW_SEARCH;
		}
		return page;
	}
	/*estas paginas solo funcionaran cuando no halla sesion iniciada*/
	@GetMapping("/home")
	public String home(ModelMap model) {
		String page = ViewConstants.VIEW_HOME;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
		}
		if (App.isAuthenticated()) {
			page = ViewConstants.REDIRECT;
		}
		return page;
	}
	
	@GetMapping("/inmueble")
	public String inmueble(ModelMap model) {
		String page = ViewConstants.VIEW_INMUEBLE;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
			
		}
		if (App.isAuthenticated()) {
			//page = ViewConstants.REDIRECT;
			page = ViewConstants.VIEW_INMUEBLE;
		}
		return page;
	}
	
	@GetMapping("/arriendo")
	public String arriendo(ModelMap model) {
		String page = ViewConstants.VIEW_ARRIENDO;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg == null) {
			//model.addAttribute("loginError", msg);
			//this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
			
			page = ViewConstants.VIEW_ARRIENDO;
		}
		if (App.isAuthenticated()) {
			//page = ViewConstants.REDIRECT;
			page = ViewConstants.VIEW_ARRIENDO;
		}
		return page;
	}
	@GetMapping("/anticres")
	public String anticres(ModelMap model) {
		String page = ViewConstants.VIEW_ANTICRES;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
		}
		if (App.isAuthenticated()) {
			//page = ViewConstants.REDIRECT;
			page = ViewConstants.VIEW_ANTICRES;
		}
		return page;
	}
	@GetMapping("/venta")
	public String venta(ModelMap model) {
		String page = ViewConstants.VIEW_VENTA;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
		}
		if (App.isAuthenticated()) {
			//page = ViewConstants.REDIRECT;
			page = ViewConstants.VIEW_VENTA;
		}
		return page;
	}
	@GetMapping("/detalle_inmueble")
	public String detalle_inmueble(ModelMap model) {
		String page = ViewConstants.VIEW_DETAIL_INMUEBLE;
		String msg = (String) this.request.getSession().getAttribute(Constants.Auth.VAR_SESSION_ERROR);
		if (msg != null) {
			model.addAttribute("loginError", msg);
			this.request.getSession().removeAttribute(Constants.Auth.VAR_SESSION_ERROR);
		}
		if (App.isAuthenticated()) {
			//page = ViewConstants.REDIRECT;
			page = ViewConstants.VIEW_DETAIL_INMUEBLE;
		}
		return page;
	}

	// @GetMapping("/login")
	// public String login(ModelMap model) {
	// String page = ViewConstants.VIEW_LOGIN;
	// if (App.isAuthenticated()) {
	// page = ViewConstants.REDIRECT;
	// }
	// LOG.info("--redirect to: " + page);
	// return page;
	// }

	// @GetMapping(value = "/loginError")
	// public String loginError(HttpServletRequest request, HttpServletResponse
	// response, ModelMap model) {
	// String msg = (String) request.getSession().getAttribute("error");
	// LOG.info("--loginError");
	// model.addAttribute("loginError", msg);
	// return ViewConstants.VIEW_LOGIN;
	// }

	// @GetMapping("/logout")
	// public String logoutPage(HttpServletRequest request, HttpServletResponse
	// response, ModelMap model) {
	// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// if (auth != null) {
	// Object obj = auth.getPrincipal();
	// if (obj instanceof UserDetailsHelper) {
	// WebAuthenticationDetails wad = (WebAuthenticationDetails) auth.getDetails();
	// String ip = wad.getRemoteAddress();
	// Users user = ((UserDetailsHelper) obj).getUserEntity();
	// new SecurityContextLogoutHandler().logout(request, response, auth);
	// UserHistorial uh = new UserHistorial(user, new Date(), ip,
	// Messages.getProperty(Messages.msgLogout));
	// iUserHistorialService.persistEntity(uh);
	// }
	// }
	// LOG.info("--logout");
	// return ViewConstants.REDIRECT;
	// }
}
