package com.proinsalud.sistemas.web.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.ViewConstants;

@Controller
public class ErrorController {

	private static final Log LOG = LogFactory.getLog(ErrorController.class);

	@RequestMapping(value = "errors", method = RequestMethod.GET)
	public ModelAndView rendererErrorPage(HttpServletRequest httpServletRequest) {
		//getHeadersInfo(httpServletRequest);
		ModelAndView errorPage = new ModelAndView(ViewConstants.VIEW_ERROR_PAGE);
		String errorMsg = "";
		String errorImg = "";
		int httpErrorCode = getErrorCode(httpServletRequest);

		switch (httpErrorCode) {
		case 400: {
			errorMsg = Messages.msgError400;
			errorImg = Messages.msgError400Img;
			break;
		}
		case 401: {
			errorMsg = Messages.msgError401;
			errorImg = Messages.msgError401Img;
			break;
		}
		case 403: {
			errorMsg = Messages.msgError403;
			errorImg = Messages.msgError403Img;
			break;
		}
		case 404: {
			errorMsg = Messages.msgError404;
			errorImg = Messages.msgError404Img;
			break;
		}
		case 500: {
			errorMsg = Messages.msgError500;
			errorImg = Messages.msgError500Img;
			break;
		}
		}
		errorPage.addObject("errorMsg", Messages.getProperty(errorMsg));
		errorPage.addObject("errorImg", Messages.getProperty(errorImg));
		LOG.info("--ERROR: code_error:" + httpErrorCode + " --QueryString:" + httpServletRequest.getQueryString()
				+ " --RequestURI: " + httpServletRequest.getRequestURI());
		return errorPage;
	}

	private int getErrorCode(HttpServletRequest httpServletRequest) {
		return (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
	}

//	private Map<String, String> getHeadersInfo(HttpServletRequest request) {
//		Map<String, String> map = new HashMap<String, String>();
//		Enumeration headerNames = request.getHeaderNames();
//		StringBuilder builder = new StringBuilder();
//		while (headerNames.hasMoreElements()) {
//			String key = (String) headerNames.nextElement();
//			String value = request.getHeader(key);
//			builder.append("key= " + key + "- value=" + value);
//			map.put(key, value);
//		}
//		LOG.info("--HEADERS: " + builder);
//		return map;
//	}

}
