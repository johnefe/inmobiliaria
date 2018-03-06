package com.proinsalud.sistemas.core.security.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.security.dao.IUserAuthorityDao;
import com.proinsalud.sistemas.core.security.dao.IUserOptionActionDao;
import com.proinsalud.sistemas.core.security.dao.IUserOptionDao;
import com.proinsalud.sistemas.core.security.model.UserAuthority;
import com.proinsalud.sistemas.core.security.model.UserOption;
import com.proinsalud.sistemas.core.security.model.UserOptionAction;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUtilSecurityService;

/**
 * 
 * @author Andres Santacruz
 * @datetime 19/12/2017 - 8:47:13 a. m.
 *
 */
@Repository(value = "UtilSecurityService")
public class UtilSecurityService implements IUtilSecurityService, Serializable {

	private static final long serialVersionUID = 1198339931674510611L;

	@Autowired(required = true)
	@Qualifier(value = "userAuthorityDao")
	private IUserAuthorityDao iUserAuthorityDao;

	@Autowired(required = true)
	@Qualifier(value = "userOptionDao")
	private IUserOptionDao iUserOptionDao;

	@Autowired(required = true)
	@Qualifier(value = "userOptionActionDao")
	private IUserOptionActionDao iUserOptionActionDao;

	@Transactional
	public void updateOptionsUser(List<UserOption> newUO, List<UserOption> deleteUO, List<UserAuthority> newUA, List<UserAuthority> deleteUA) throws Exception {
		if (newUO != null && !newUO.isEmpty()) {
			iUserOptionDao.persistEntity(newUO);
		}
		if (deleteUO != null && !deleteUO.isEmpty()) {
			iUserOptionDao.deleteEntity(deleteUO);
		}
		if (newUA != null && !newUA.isEmpty()) {
			iUserAuthorityDao.persistEntity(newUA);
		}
		if (deleteUA != null && !deleteUA.isEmpty()) {
			iUserAuthorityDao.deleteEntity(deleteUA);
		}
	}

	@Transactional
	public void copyOptionsToUser(Users user, Users userCopy) throws Exception {
		// ELIMINAR OPTIONS E INSERTAR LAS DEL USUARIO TEMPLATE
		iUserOptionDao.deleteAllEntityByIdUser(user.getId());
		if (!userCopy.getUserOptions().isEmpty()) {
			for (UserOption uo : userCopy.getUserOptions()) {
				UserOption uoP = iUserOptionDao.persistEntity(new UserOption(uo.getOption(), user));
				List<UserOptionAction> uoActions = new ArrayList<>();
				for (UserOptionAction uoa : uo.getUserOptionActions()) {
					uoActions.add(new UserOptionAction(uoP, uoa.getOptionAction()));
				}
				iUserOptionActionDao.persistEntity(uoActions);
			}
		}

		// ELIMINAR AUTORIDADES E INSERTAR LAS DEL USUARIO TEMPLATE
		iUserAuthorityDao.deleteEntity(user.getUserAuthorities());
		if (!userCopy.getUserAuthorities().isEmpty()) {
			List<UserAuthority> uAuthorities = new ArrayList<>();
			for (UserAuthority ua : userCopy.getUserAuthorities()) {
				uAuthorities.add(new UserAuthority(user, ua.getAuthority()));
			}
			iUserAuthorityDao.persistEntity(uAuthorities);
		}
	}

}
