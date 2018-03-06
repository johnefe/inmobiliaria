package com.proinsalud.sistemas.web.application.authentication;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.commons.logging.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.proinsalud.sistemas.core.security.model.UserHistorial;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUserHistorialService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UserDetailsHelper;

/**
 * @author Andres Santacruz
 * @datetime 6/02/2018 - 4:22:21 p. m.
 *
 */
@Component
public class CustomHttpSessionEventPublisher extends HttpSessionEventPublisher {
	
	private static final Log LOG = App.getLogger(CustomHttpSessionEventPublisher.class);

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		try {
			HttpSession session = event.getSession();
			double timeUse = (System.currentTimeMillis() - session.getLastAccessedTime()) / 1000;
			boolean expired = timeUse >= session.getMaxInactiveInterval();
			if (expired) {
				SecurityContextImpl securitySpring = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
				Authentication authentication = securitySpring.getAuthentication();
				ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
				IUserHistorialService iUserHistorialService = (IUserHistorialService) ctx.getBean(IUserHistorialService.NAME_SERVICE);
				if (authentication != null && authentication.isAuthenticated()) {
					Object obj = authentication.getPrincipal();
					if (obj instanceof UserDetailsHelper) {
						WebAuthenticationDetails wad = (WebAuthenticationDetails) authentication.getDetails();
						Users user = ((UserDetailsHelper) obj).getUserEntity();
						UserHistorial uh = new UserHistorial(user, new Date(), wad.getRemoteAddress(), Messages.getProperty(Messages.msgLogoutTimeout));
						iUserHistorialService.persistEntity(uh);
					}
				}
			}
			super.sessionDestroyed(event);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			super.sessionDestroyed(event);
		}
	}

}
