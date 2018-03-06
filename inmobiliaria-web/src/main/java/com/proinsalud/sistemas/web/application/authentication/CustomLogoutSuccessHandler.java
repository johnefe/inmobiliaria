package com.proinsalud.sistemas.web.application.authentication;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.proinsalud.sistemas.core.security.model.UserHistorial;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUserHistorialService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UserDetailsHelper;

/**
 * @author Andres Santacruz
 * @datetime 5/02/2018 - 9:57:30 a. m.
 *
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Log LOG = App.getLogger(CustomLogoutSuccessHandler.class);

	@Autowired
	private IUserHistorialService iUserHistorialService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		try {
			if (authentication != null && authentication.isAuthenticated()) {
				Object obj = authentication.getPrincipal();
				if (obj instanceof UserDetailsHelper) {
					WebAuthenticationDetails wad = (WebAuthenticationDetails) authentication.getDetails();
					Users user = ((UserDetailsHelper) obj).getUserEntity();
					new SecurityContextLogoutHandler().logout(request, response, authentication);
					UserHistorial uh = new UserHistorial(user, new Date(), wad.getRemoteAddress(), Messages.getProperty(Messages.msgLogout));
					iUserHistorialService.persistEntity(uh);
				}
			}
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}
}
