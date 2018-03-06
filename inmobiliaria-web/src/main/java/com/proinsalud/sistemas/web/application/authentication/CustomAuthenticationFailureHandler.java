package com.proinsalud.sistemas.web.application.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Constants;

/**
 * @author Andres Santacruz
 * @datetime 5/02/2018 - 10:10:52 a. m.
 *
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Log LOG = App.getLogger(CustomAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		try {
			request.getSession().setAttribute(Constants.Auth.VAR_SESSION_ERROR, exception.getMessage());
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(request.getContextPath() + Constants.Ctrl.LOGIN);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

}
