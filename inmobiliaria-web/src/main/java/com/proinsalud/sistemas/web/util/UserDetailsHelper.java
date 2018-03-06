package com.proinsalud.sistemas.web.util;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;

import com.proinsalud.sistemas.core.security.model.Users;
/**
 * 
 * @author Andres Santacruz
 * @datetime 3/01/2018 - 8:47:15 a. m.
 *
 */
public class UserDetailsHelper implements Serializable {

	private static final long serialVersionUID = 794758661404989999L;

	private UserDetails userDetails;

	private Users userEntity;

	public UserDetailsHelper(UserDetails userDetails, Users userEntity) {
		super();
		this.userDetails = userDetails;
		this.userEntity = userEntity;
	}

	public UserDetailsHelper() {
		super();
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Users getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(Users userEntity) {
		this.userEntity = userEntity;
	}

}
