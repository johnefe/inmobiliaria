package com.proinsalud.sistemas.web.general;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secured/general/")
public class CommonController {

	private static final Log LOG = LogFactory.getLog(CommonController.class);

	@GetMapping("/{module}/{page}")
	public String gotToModule(@PathVariable(name = "module", required = true) String ruta,
			@PathVariable(name = "page", required = true) String page) {
		LOG.info("PARAMS: ruta " + ruta);
		return "general/"+ruta+"/"+page;
	}

}
