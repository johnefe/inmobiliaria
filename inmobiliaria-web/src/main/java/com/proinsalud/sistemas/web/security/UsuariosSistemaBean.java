package com.proinsalud.sistemas.web.security;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IPersonaService;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUsersService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.Password;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * 
 * @author Andres Santacruz
 * @datetime 22/12/2017 - 3:09:56 p. m.
 *
 */
@ManagedBean
@ViewScoped
public class UsuariosSistemaBean implements Serializable {

	private static final long serialVersionUID = -6923333515425873194L;
	private static final Log LOG = App.getLogger(UsuariosSistemaBean.class);

	@Autowired
	private IPersonaService iPersonaService;

	@Autowired
	private IUsersService iUsersService;

	private List<Person> persons;
	private List<Users> users;
	private Users userCurrent = null;

	private String passwordTemp = "";
	private boolean obligatorio;
	private Integer timeSession;

	public UsuariosSistemaBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		listUser();
		initData();
	}

	public void initData() {
		try {
			//persons = iPersonaService.findAllEntityWithUsers();

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void listUser() {
		try {
			users = iUsersService.findAllEntity();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadUserById(Long idUser) {
		try {
			obligatorio = false;
			userCurrent = iUsersService.findEntityById(idUser);
			timeSession = userCurrent.getTimeSession() / 60;
			passwordTemp = "";
			userCurrent.setPerson((Person) UtilWeb.loadObject(persons, userCurrent.getPerson()));
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, null, Messages.getProperty(Messages.msgErrorLoadData));
		}
	}

	public void save() {
		try {
			if (userCurrent != null) {
				userCurrent.setTimeSession(timeSession * 60);
				if (userCurrent.getId() != null) {
					obligatorio = false;
					if (!passwordTemp.isEmpty()) {
						String hashedPass = Password.hashPassword(passwordTemp);
						userCurrent.setPassword(hashedPass);
					}
					userCurrent = iUsersService.mergeEntity(userCurrent);
				} else {
					obligatorio = true;
					String hashedPass = Password.hashPassword(passwordTemp);
					userCurrent.setPassword(hashedPass);
					userCurrent = iUsersService.persistEntity(userCurrent);
				}
			}
			passwordTemp = "";
			userCurrent = null;
			initData();
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void nuevo() {
		userCurrent = new Users();
		timeSession = userCurrent.getTimeSession() / 60;
		obligatorio = true;
	}

	public void cancel() {
		userCurrent = null;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public Users getUserCurrent() {
		return userCurrent;
	}

	public List<Users> getUsers() {
		return users;
	}

	public String getPasswordTemp() {
		return passwordTemp;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setPasswordTemp(String passwordTemp) {
		this.passwordTemp = passwordTemp;
	}

	public Integer getTimeSession() {
		return timeSession;
	}

	public void setTimeSession(Integer timeSession) {
		this.timeSession = timeSession;
	}

}
