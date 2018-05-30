package com.proinsalud.sistemas.web.application.authentication;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.proinsalud.sistemas.core.security.model.Action;
import com.proinsalud.sistemas.core.security.model.UserHistorial;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IActionService;
import com.proinsalud.sistemas.core.security.service.IUserHistorialService;
import com.proinsalud.sistemas.web.application.bean.SessionBean;
import com.proinsalud.sistemas.web.util.ActionPermits;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Constants;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UserDetailsHelper;

/**
 * @author Andres Santacruz
 * @datetime 5/02/2018 - 8:59:07 a. m.
 *
 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Log LOG = App.getLogger(CustomAuthenticationSuccessHandler.class);

	@Autowired
	@Qualifier(value = Constants.REF_SESSION_BEAN)
	private SessionBean sessionBean;

	@Autowired
	private IUserHistorialService iUserHistorialService;

	@Autowired
	private IActionService iActionService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		try {
			if (authentication != null && authentication.isAuthenticated()) {
				Object obj = authentication.getPrincipal();
				if (obj instanceof UserDetailsHelper) {

					LOG.info(ActionPermits.getStringActions());
					List<Action> actions = iActionService.findAllEntity();
					String ok = ActionPermits.validateActions(actions);
					if(ok != null) {
						LOG.error(ok);
					}

					sessionBean.loadVars();

					WebAuthenticationDetails wad = (WebAuthenticationDetails) authentication.getDetails();
					Users usersLogin = ((UserDetailsHelper) obj).getUserEntity();
					UserHistorial uh = new UserHistorial(usersLogin, new Date(), wad.getRemoteAddress(), Messages.getProperty(Messages.msgLogin));
					iUserHistorialService.persistEntity(uh);

					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(usersLogin.getTimeSession());

					String url = Constants.Ctrl.WELCOME;
					response.setStatus(HttpServletResponse.SC_OK);
					// response.sendRedirect(url);
					redirectStrategy.sendRedirect(request, response, url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}
}
