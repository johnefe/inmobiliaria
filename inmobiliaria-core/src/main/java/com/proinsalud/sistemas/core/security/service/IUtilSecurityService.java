package com.proinsalud.sistemas.core.security.service;

import java.util.List;

import com.proinsalud.sistemas.core.security.model.UserAuthority;
import com.proinsalud.sistemas.core.security.model.UserOption;
import com.proinsalud.sistemas.core.security.model.Users;

public interface IUtilSecurityService {

	public void updateOptionsUser(List<UserOption> newUO, List<UserOption> deleteUO, List<UserAuthority> newUA, List<UserAuthority> deleteUA) throws Exception;

	public void copyOptionsToUser(Users user, Users userCopy) throws Exception;

}
