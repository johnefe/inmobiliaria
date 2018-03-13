package com.proinsalud.sistemas.web.application.controller;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proinsalud.sistemas.web.util.App;

/**
 * @author Andres Santacruz
 * @datetime 6/10/2017 - 11:58:24 a. m.
 *
 */
@Controller
@RequestMapping("/secured")
public class RouteController {

	private static final Log LOG = App.getLogger(RouteController.class);

	@GetMapping({ "/", "/bienvenido" })
	public String securedIndex() {
		LOG.info("--redirect to index");
		return "index";
	}
	
	@GetMapping({ "/{app}/inmueble" })
	public String securedInmueble() {
		LOG.info("--redirect to index");
		return "inmueble";
	}

	// @GetMapping("/{app}/{module}/{idModule}")
	// public String goToPage(@PathVariable(name = "app", required = true) String
	// appName,
	// @PathVariable(name = "module", required = true) String moduleName,
	// @PathVariable(name = "idModule", required = true) int idModule) {
	//
	// LOG.info("--PARAMS: app=" + appName + " module=" + moduleName + " idModule="
	// + idModule);
	//
	// List<Option> options = ConstantsApp.getOptionsUser();
	// Option option = ConstantsApp.searchOption(options, idModule, null);
	// sessionBean.setOptionSelectedApp(option);
	//
	// String responseRoute = ViewConstants.REDIRECT_SECURED + appName + "/" +
	// moduleName + "/";
	// LOG.info("--responseRoute= " + responseRoute);
	// return responseRoute;
	// }

	// @GetMapping("/{app}/{module}")
	// public String goToPage(@PathVariable(name = "app", required = true) String
	// appName, @PathVariable(name = "module", required = true) String moduleName) {
	//
	// LOG.info("--PARAMS: app=" + appName + " module=" + moduleName);
	//
	// List<Option> options = App.getOptionsUser();
	// Option option = App.searchOptionModule(appName, moduleName, options, null);
	// sessionBean.setOptionSelectedApp(option);
	//
	// String responseRoute = ViewConstants.REDIRECT_SECURED + appName + "/" +
	// moduleName + "/";
	// LOG.info("--responseRoute= " + responseRoute);
	// return responseRoute;
	// }
	//
	// @GetMapping("/{app}/{module}/{resource}")
	// @RequestMapping(value="/{app}/{module}/{resource}", method=
	// RequestMethod.GET)
	// public String goToResource(@PathVariable(name = "app", required = true)
	// String appName, @PathVariable(name = "module", required = true) String
	// moduleName, @PathVariable(name = "resource", required = false) String
	// resource) {
	//
	// LOG.info("--PARAMS: app=" + appName + " module=" + moduleName);
	//
	// List<Option> options = App.getOptionsUser();
	// Option option = App.searchOptionModule(appName, moduleName, options, null);
	// sessionBean.setOptionSelectedApp(option);
	//
	// String responseRoute = ViewConstants.REDIRECT_SECURED + appName + "/" +
	// moduleName + "/";
	// LOG.info("--responseRoute= " + responseRoute);
	// return responseRoute;
	// }

}
