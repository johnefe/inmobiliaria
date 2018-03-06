package com.proinsalud.sistemas.web.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.security.model.Authority;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.model.OptionAction;
import com.proinsalud.sistemas.core.security.model.UserAuthority;
import com.proinsalud.sistemas.core.security.model.UserOption;
import com.proinsalud.sistemas.core.security.model.UserOptionAction;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IAuthorityService;
import com.proinsalud.sistemas.core.security.service.IOptionActionService;
import com.proinsalud.sistemas.core.security.service.IOptionService;
import com.proinsalud.sistemas.core.security.service.IUserOptionActionService;
import com.proinsalud.sistemas.core.security.service.IUserOptionService;
import com.proinsalud.sistemas.core.security.service.IUsersService;
import com.proinsalud.sistemas.core.security.service.IUtilSecurityService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

@ManagedBean
@ViewScoped
public class PermisoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 7935397576725365452L;

	@Autowired
	private IUtilSecurityService iUtilSecurityService;

	@Autowired
	private IUsersService iUsersService;

	@Autowired
	private IOptionService iOptionService;

	@Autowired
	private IUserOptionService iUserOptionService;

	@Autowired
	private IAuthorityService iAuthorityService;

	@Autowired
	private IOptionActionService iOptionActionService;

	@Autowired
	private IUserOptionActionService iUserOptionActionService;

	private List<Users> users;
	private Users userSelected;
	private Users userProfileSelected;
	private List<Option> options;
	private List<Option> optionsUser;
	private TreeNode[] selectedNodes;
	private TreeNode root;
	private Option optionSelected;
	private List<Authority> authorities;
	private List<Authority> authoritiesSelected;
	private List<Users> usersProfile;
	private List<OptionAction> actionsOption;
	private List<OptionAction> actionsSelected;
	private UserOption userOptionSelected;

	public PermisoUsuariosBean() {
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		users = iUsersService.findAllEntity();
		options = iOptionService.findByLevel(1);
		authorities = iAuthorityService.findAllEntity();
		usersProfile = iUsersService.findUsersProfileTemplate();
	}

	public void loadUserCompletely() {
		if (userSelected != null) {
			authoritiesSelected = new ArrayList<>();
			unselectOption(null);
			root = new DefaultTreeNode("Root", null);
			UtilWeb.loadRootTree(root, options, "opcion", "getOptions");
			userSelected = iUsersService.findUserCompletely(userSelected.getUsername());
			optionsUser = UtilWeb.getOptionsUser(userSelected);
			optionsUser = UtilWeb.getOptionsUnorder(optionsUser);
			loadTreeRecursivity(root.getChildren(), optionsUser);

			List<UserAuthority> uaCurrent = userSelected.getUserAuthorities();
			for (UserAuthority ua : uaCurrent) {
				for (Authority a : authorities) {
					if (ua.getAuthority().getId() == a.getId()) {
						authoritiesSelected.add(a);
						break;
					}
				}
			}
		}
	}

	public void loadTreeRecursivity(List<TreeNode> nodes, List<Option> options) {
		for (TreeNode tN : nodes) {
			Option opt = (Option) tN.getData();
			for (Option o : options) {
				if (o.getId() == opt.getId()) {
					tN.setSelected(true);
					options.remove(o);
					if (!tN.getChildren().isEmpty()) {
						tN.setExpanded(true);
						loadTreeRecursivity(tN.getChildren(), options);
					}
					break;
				}
			}
		}
	}

	public void cancel() {
		userSelected = null;
		optionsUser = null;
		selectedNodes = null;
		optionSelected = null;
		authoritiesSelected = new ArrayList<>();
	}

	public void save() {

		// ORGANIZAR OPCIONES
		List<Option> optionSaved = new ArrayList<>();
		getOptionSaveRecursivity(root.getChildren(), optionSaved);

		List<UserOption> userOptions = iUserOptionService.findByUser(userSelected.getId());
		List<UserOption> newUO = new ArrayList<>();
		for (Option opt : optionSaved) {
			boolean isNew = true;
			for (UserOption uo : userOptions) {
				if (uo.getOption().getId() == opt.getId()) {
					userOptions.remove(uo);
					isNew = false;
					break;
				}
			}
			if (isNew) {
				newUO.add(new UserOption(opt, userSelected));
			}
		}

		// ORGANIZAR AUTORIDADES
		List<UserAuthority> newUA = new ArrayList<>();
		List<UserAuthority> uaCurrent = userSelected.getUserAuthorities();
		for (Authority a : authoritiesSelected) {
			boolean isNew = true;
			for (UserAuthority ua : uaCurrent) {
				if (ua.getAuthority().getId() == a.getId()) {
					uaCurrent.remove(ua);
					isNew = false;
					break;
				}
			}
			if (isNew) {
				newUA.add(new UserAuthority(userSelected, a));
			}
		}

		// GUARDAR DATOS EN UNA TRANSACCION
		try {
			iUtilSecurityService.updateOptionsUser(newUO, userOptions, newUA, uaCurrent);
			loadUserCompletely();
			Messages.showMsg(Messages.MSG_INFO, "formUsers", "Las Opciones de usuario se guardaron correctamente");
		} catch (Exception e) {
			Messages.showMsg(Messages.MSG_ERROR, "formUsers", "Ocurrio un error guardando las opciones\n" + e.getMessage());
		}
	}

	private List<Option> getOptionSaveRecursivity(List<TreeNode> nodes, List<Option> optionSaved) {
		for (TreeNode tn : nodes) {
			Option opt = (Option) tn.getData();
			if (tn.isSelected()) {
				optionSaved.add(opt);
				if (!tn.getChildren().isEmpty()) {
					getOptionSaveRecursivity(tn.getChildren(), optionSaved);
				}
			} else if (!tn.getChildren().isEmpty()) {
				int sizeActual = optionSaved.size();
				getOptionSaveRecursivity(tn.getChildren(), optionSaved);
				if (sizeActual < optionSaved.size()) {
					optionSaved.add(opt);
				}
			}
		}
		return optionSaved;
	}

	public void saveActions() {
		List<UserOptionAction> newUOA = new ArrayList<>();
		List<UserOptionAction> uoaCurrent = userOptionSelected.getUserOptionActions();
		for (OptionAction oa : actionsSelected) {
			boolean isNew = true;
			for (UserOptionAction uoa : uoaCurrent) {
				if (uoa.getOptionAction().getId() == oa.getId()) {
					uoaCurrent.remove(uoa);
					isNew = false;
					break;
				}
			}
			if (isNew) {
				newUOA.add(new UserOptionAction(userOptionSelected, oa));
			}
		}

		try {
			iUserOptionActionService.updateUserOptionAction(newUOA, uoaCurrent);
			userOptionSelected = iUserOptionService.findEntityByOptionUser(optionSelected.getId(), userSelected.getId());
			Messages.showMsg(Messages.MSG_INFO, "formListadoAcciones", "Las Acciones se guardaron correctamente");
		} catch (Exception e) {
			Messages.showMsg(Messages.MSG_ERROR, "formListadoAcciones", "Ocurrio un error guardando\n" + e.getMessage());
		}
	}

	public void selectOption(NodeSelectEvent event) {
		actionsSelected = new ArrayList<>();
		optionSelected = (Option) event.getTreeNode().getData();

		userOptionSelected = iUserOptionService.findEntityByOptionUser(optionSelected.getId(), userSelected.getId());
		if (userOptionSelected == null) {
			unselectOption(null);
			return;
		}
		actionsOption = iOptionActionService.findAllEntityByOption(optionSelected.getId());
		if (actionsOption.isEmpty()) {
			unselectOption(null);
			return;
		}

		List<OptionAction> optionActionsTemp = new ArrayList<OptionAction>(actionsOption);
		for (UserOptionAction uoA : userOptionSelected.getUserOptionActions()) {
			for (OptionAction oa : optionActionsTemp) {
				if (uoA.getOptionAction().getId() == oa.getId()) {
					actionsSelected.add(oa);
					optionActionsTemp.remove(oa);
					break;
				}
			}
		}

	}

	public void unselectOption(NodeUnselectEvent event) {
		actionsSelected = new ArrayList<>();
		optionSelected = null;
		actionsOption = null;
		userOptionSelected = null;
		userProfileSelected = null;
	}

	public void inheritOptions() {
		if (userProfileSelected != null) {
			try {
				iUtilSecurityService.copyOptionsToUser(userSelected, userProfileSelected);
				loadUserCompletely();
				Messages.showMsg(Messages.MSG_INFO, "formUsers", "Las Opciones se heredaron correctamente");
			} catch (Exception e) {
				Messages.showMsg(Messages.MSG_ERROR, "formUsers", "Ocurrio un error guardando copiando las opciones\n" + e.getMessage());
			}

		}
	}

	public List<Users> getUsers() {
		return users;
	}

	public Users getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(Users userSelected) {
		this.userSelected = userSelected;
	}

	public List<Option> getOpciones() {
		return options;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public TreeNode getRoot() {
		return root;
	}

	public List<Authority> getAuthoritiesSelected() {
		return authoritiesSelected;
	}

	public void setAuthoritiesSelected(List<Authority> authoritiesSelected) {
		this.authoritiesSelected = authoritiesSelected;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public List<Users> getUsersProfile() {
		return usersProfile;
	}

	public Users getUserProfileSelected() {
		return userProfileSelected;
	}

	public void setUserProfileSelected(Users userProfileSelected) {
		this.userProfileSelected = userProfileSelected;
	}

	public Option getOptionSelected() {
		return optionSelected;
	}

	public List<OptionAction> getActionsSelected() {
		return actionsSelected;
	}

	public void setActionsSelected(List<OptionAction> actionsSelected) {
		this.actionsSelected = actionsSelected;
	}

	public List<OptionAction> getActionsOption() {
		return actionsOption;
	}

}
