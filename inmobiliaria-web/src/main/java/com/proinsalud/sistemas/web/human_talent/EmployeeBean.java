package com.proinsalud.sistemas.web.human_talent;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.human_talent.model.Employee;
import com.proinsalud.sistemas.core.human_talent.service.IEmployeeService;
import com.proinsalud.sistemas.core.human_talent.service.IUtilHumanTalentService;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUsersService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.Password;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Mauricio Pinchao
 * @datetime 2/01/2018 - 9:36:41 a. m.
 */
@ManagedBean(name = "employeeBean")
@ViewScoped
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = -7320449367505442104L;

	public static final Log LOG = App.getLogger(EmployeeBean.class);
	
	@Autowired
	private IUtilHumanTalentService iUtilHumanTalentService;
	@Autowired
	private IUsersService iUsersService;
	@Autowired
	private IEmployeeService iEmployeeService;
	
	private Employee employee;
	private Person person;
	private Users user;
	private UserDetails userD;
	
	private boolean editar = false;
	private String currentPassword;
	private String newPassword;
	private String passConfirm;
	
	public EmployeeBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		user = App.getUser();
		person = user.getPerson();
		employee = iEmployeeService.findEntityByIdPerson(person.getId());
	}
	
	public void nuevo() {
		employee = new Employee();
	}
	
	public void edit() {
		editar = true;
	}
	
	public void saveEmployee() {
		try {
			if(employee != null && person != null) {
				iUtilHumanTalentService.updateEmployee(person, employee);
			}
			employee = null;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
	
	public void loadEmployee(Long id) {
		try {
			employee = iEmployeeService.findEntityById(id);
		} catch (Exception e) {
			LOG.error("ERROR: loadUserInfo() -> " + e.getMessage());
		}
	}
	
	public void deleteEmployee(Employee user) {
		try {
			iEmployeeService.deleteEntity(user);
			employee = null;
		} catch (Exception e) {
			LOG.error("ERROR: deleteEmployee() -> " + e.getMessage());
		}
	}
	
	public void saveNewPassword() {
		try {
			userD = iUsersService.loadUserByUsername(user.getUsername());
			if(currentPassword != null) {
				boolean correctPass = Password.checkPassword(currentPassword, userD.getPassword());
				if(correctPass == true) {
					if(newPassword.equals(passConfirm)) {
						String hashedPass = Password.hashPassword(newPassword);
						user.setPassword(hashedPass);
						user = iUsersService.mergeEntity(user);
					}
					Messages.showMsg(Messages.MSG_INFO, "form11", null);
					cleanForm();
				}else {
					Messages.showMsg(Messages.MSG_ERROR, "form11", null);
					cleanForm();
				}
			}
			cleanForm();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	public void cleanForm() {
		newPassword = null;
		passConfirm = null;
		currentPassword = null;
	}
	
	public void cancel() {
		editar = false;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setUEmployee(Employee employee) {
		this.employee = employee;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isEditar() {
		return editar;
	}

	public String getPassConfirm() {
		return passConfirm;
	}

	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public UserDetails getUserD() {
		return userD;
	}

}
