package com.proinsalud.sistemas.web.digital_turn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.digital_turn.model.Module;
import com.proinsalud.sistemas.core.digital_turn.model.ModuleUser;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleService;
import com.proinsalud.sistemas.core.digital_turn.service.IModuleUserService;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUsersService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

@ManagedBean
@ViewScoped
public class DigiTurnoBean implements Serializable {

	private static final long serialVersionUID = -3854456660122407955L;

	private static final Log LOG = App.getLogger(DigitalTurnBean.class);

	@Autowired
	private IUsersService iUsersService;

	@Autowired
	private IModuleService iModuleService;

	@Autowired
	private IModuleUserService iModuleUserService;

	private List<Users> users;
	private List<ModuleUser> modules;
	private List<ModuleUser> moduleUsers;
	private List<Module> module;

	private List<Module> lista = null;
	private List<Module> lista2 = null;
	private ModuleUser moduleUser;
	private Users userr;
	private Long idUser;
	private  boolean elModuleEsta = false;
	private boolean showForm;
	
	public DigiTurnoBean() {
		super();

	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		moduleUsers = null;
		module = null;
		users = iUsersService.findAllEntity();
	}

	public void loadModules(Users user) {
		showForm=true;
		idUser = user.getId();
		userr = user;
		moduleUsers = iModuleUserService.findEntityByUserId(idUser);
		module = iModuleService.findAllEntity();

		lista = new ArrayList<Module>();
		lista2 = new ArrayList<Module>();

		if (moduleUsers.isEmpty()) {
			for (Module mm : module) {
				lista.add(mm);
			}
			lista2 = new ArrayList<Module>();
		}

		if (!moduleUsers.isEmpty()) {

			for (ModuleUser m : moduleUsers) {

				for (Module mm : module) {

					if (mm.getId().equals(m.getModule().getId())) {
						lista2.add(mm);
					}
				}
			}

		}

		if (!moduleUsers.isEmpty()) {

			for (int y=0; y<module.size();y++) {
				elModuleEsta=false;
				for(int i=0; i<lista2.size();i++) {
					
					if(module.get(y) == (lista2.get(i))) {
						
						elModuleEsta=true;
					}				
				}
				if(elModuleEsta == false) {
					
					lista.add(module.get(y));
				}
			}			

		}
		
	}

	//este metodo no funciona por la llaves foraneas, ver si en ves de eliminar se des habilita
	public void deleteModuleUser(Module module) {

		try {

			
			if (module != null && module.getId() != null) {

				idUser= userr.getId();
				iModuleUserService.deleteEntityByIdModule(module.getId(),idUser);				
			}

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	

	public void addModuleUser(Module module) {
		try {

			moduleUser = new ModuleUser();
			moduleUser.setUser(userr);
			moduleUser.setModule(module);

			if (module != null && userr.getId() != null) {

				iModuleUserService.persistEntity(moduleUser);

			}

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void viewModule() {

	}

	public List<Users> getUsers() {
		return users;
	}

	public Users getUserr() {
		return userr;
	}

	public List<ModuleUser> getModuleUsers() {
		return moduleUsers;
	}

	public List<ModuleUser> getModules() {
		return modules;
	}

	public List<Module> getModule() {
		return module;
	}

	public List<Module> getLista() {
		return lista;
	}

	public List<Module> getLista2() {
		return lista2;
	}

	public ModuleUser getModuleUser() {
		return moduleUser;
	}

	public void setModuleUser(ModuleUser moduleUser) {
		this.moduleUser = moduleUser;
	}

	public boolean isShowForm() {
		return showForm;
	}
	

}
