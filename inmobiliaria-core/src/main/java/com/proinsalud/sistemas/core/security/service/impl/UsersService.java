package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IUsersDao;
import com.proinsalud.sistemas.core.security.model.Authority;
import com.proinsalud.sistemas.core.security.model.UserAuthority;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUsersService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:51:04 a. m.
 *
 */
@Repository(value = "userService")
public class UsersService implements IUsersService, UserDetailsService, Serializable {

	private static final long serialVersionUID = -3012290736875671027L;

	@Autowired(required = true)
	@Qualifier(value = "userDao")
	private IUsersDao iUserDao;

	@Transactional
	public Users persistEntity(Users entity) {
		return iUserDao.persistEntity(entity);
	}

	@Transactional
	public Users mergeEntity(Users entity) {
		return iUserDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(Users entity) {
		iUserDao.deleteEntity(entity);
	}

	@Transactional
	public List<Users> findAllEntity() {
		return iUserDao.findAllEntity();
	}

	@Transactional
	public Users findEntityById(Long id) {
		return iUserDao.findEntityById(id);
	}

	@Transactional
	public Users findEntityByUsername(String username) {
		return iUserDao.findEntityByUsername(username);
	}

	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = findEntityByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("El usuario no existe.");
		}
		List<GrantedAuthority> auths = buildAuthorities(user.getUserAuthorities());
		return buildUser(user, new ArrayList<GrantedAuthority>(auths));
	}

	private User buildUser(Users user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildAuthorities(List<UserAuthority> userAuthorities) {
		Set<GrantedAuthority> auths = new HashSet<>();
		for (UserAuthority userAuthority : userAuthorities) {
			Authority auth = userAuthority.getAuthority();
			auths.add(new SimpleGrantedAuthority("ROLE_" + auth.getName()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	@Transactional
	public Users findUserCompletely(String username) {
		return iUserDao.findUserCompletely(username);
	}

	@Transactional
	public List<Users> findUsersProfileTemplate() {
		return iUserDao.findUsersProfileTemplate();
	}

}
