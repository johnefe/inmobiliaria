package com.proinsalud.sistemas.web.application.authentication;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUsersService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Password;
import com.proinsalud.sistemas.web.util.UserDetailsHelper;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * 
 * @author Andres Santacruz
 * @datetime 29/12/2017 - 11:13:38 a. m.
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Log LOG = App.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private IUsersService iUsersService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {
			String username = authentication.getName();
			String password = (String) authentication.getCredentials();
			LOG.info("--PARAMS username='" + username + "' password='" + password + "'");
			UserDetails user = iUsersService.loadUserByUsername(username);

			if (user == null || password == null) {
				LOG.info("--ERROR: 'Usuario incorrecto'");
				throw new BadCredentialsException("Usuario incorrecto.");
			}
			boolean correctPass = Password.checkPassword(password, user.getPassword());
			if (!correctPass) {
				LOG.info("--ERROR: 'Contraseña incorrecta'");
				throw new BadCredentialsException("Contraseña incorrecta.");
			}

			if (!user.isEnabled()) {
				throw new BadCredentialsException("Usuario inactivo.");
			}

			Users userEntity = iUsersService.findUserCompletely(user.getUsername());
			//userEntity.setPassword("??????");

			UserDetailsHelper userDetailsHelper = new UserDetailsHelper(user, userEntity);
			Authentication authenticate = new UsernamePasswordAuthenticationToken(userDetailsHelper, userEntity.getPassword(), user.getAuthorities());
			LOG.info("--autenticado");
			return authenticate;

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			throw new BadCredentialsException(e.getMessage());
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
