package com.proinsalud.sistemas.web.application.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @author Andres Santacruz
 * @datetime 29/12/2017 - 11:17:09 a. m.
 *
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	//private static final Log LOG = LogFactory.getLog(RequestInterceptor.class);

//	@Autowired
//	@Qualifier(value = Constants.REF_SESSION_BEAN)
//	private SessionBean sessionBean;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//LOG.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI()	+ getParameters(request));
//		if (sessionBean.getOptionSelectedApp() != null) {
//			LOG.info("----------- " + sessionBean.getOptionSelectedApp().getDetail());
//			String url = ConstantsApp.getUrlOption(sessionBean.getOptionSelectedApp(), "");
//			LOG.info("----------- URL : " + url);
//			LOG.info("----------- URL2 : " + request.getRequestURI().toString());
//			LOG.info("----------- URL3 : " + request.getRequestURL().toString());
//			String urlReal = request.getRequestURI().toString();
//			if(!urlReal.endsWith(url) || !urlReal.contains(url)) {
//				sessionBean.setOptionSelectedApp(null);
//			}			
//		} else {
//			LOG.info("----------- NO TIENE OPTION");
//		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		if (ex != null) {
			ex.printStackTrace();
		}
		//LOG.info("[afterCompletion][" + request + "][exception: " + ex + "]");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		//LOG.info("[postHandle][" + request + "]");
	}

//	private String getParameters(HttpServletRequest request) {
//		StringBuffer posted = new StringBuffer();
//		Enumeration<?> e = request.getParameterNames();
//		if (e != null) {
//			posted.append("?");
//		}
//		while (e.hasMoreElements()) {
//			if (posted.length() > 1) {
//				posted.append("&");
//			}
//			String curr = (String) e.nextElement();
//			posted.append(curr + "=");
//			if (curr.contains("password") || curr.contains("pass") || curr.contains("pwd")) {
//				posted.append("*****");
//			} else {
//				posted.append(request.getParameter(curr));
//			}
//		}
//		String ip = request.getHeader("X-FORWARDED-FOR");
//		String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
//		if (ipAddr != null && !ipAddr.equals("")) {
//			posted.append("&_psip=" + ipAddr);
//		}
//		return posted.toString();
//	}
//
//	private String getRemoteAddr(HttpServletRequest request) {
//		String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
//		if (ipFromHeader != null && ipFromHeader.length() > 0) {
//			//LOG.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
//			return ipFromHeader;
//		}
//		return request.getRemoteAddr();
//	}

}
