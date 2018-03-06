package com.proinsalud.sistemas.web.security;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.security.model.UserHistorial;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUsersService;
import com.proinsalud.sistemas.core.util.comparators.GeneralComparator;
import com.proinsalud.sistemas.web.util.App;

/**
 * @author Jhon Frey Diaz
 * @datetime 22/12/2017 - 11:01:54 a. m.
 *
 */
@ManagedBean
@ViewScoped
public class HistorialUsuarioBean implements Serializable {

	private static final long serialVersionUID = 2985592133207292244L;
	private static final Log LOG = App.getLogger(HistorialUsuarioBean.class);

	@Autowired
	private IUsersService iUsersService;

	private List<Users> users;
	private Users user;

	public HistorialUsuarioBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		users = iUsersService.findAllEntity();
	}
	public void loadHistorial(Users user) {
		try {
			this.user = iUsersService.findEntityById(user.getId());
			List<UserHistorial> lst = this.user.getUserHistorial();
			Collections.sort(lst, GeneralComparator.UserHistorialCompareByDateAccessDesc);
			this.user.setUserHistorial(lst);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	public Users getUser() {
		return user;
	}

	public List<Users> getUsers() {
		return users;
	}

}
