package com.proinsalud.sistemas.web.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.security.model.Action;
import com.proinsalud.sistemas.core.security.model.Level;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.model.OptionAction;
import com.proinsalud.sistemas.core.security.service.IActionService;
import com.proinsalud.sistemas.core.security.service.ILevelService;
import com.proinsalud.sistemas.core.security.service.IOptionActionService;
import com.proinsalud.sistemas.core.security.service.IOptionService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

@ManagedBean
@ViewScoped
public class OpcionesSistemaBean implements Serializable {

	private static final long serialVersionUID = -7810151400208060424L;

	@Autowired
	private ILevelService iLevelService;

	@Autowired
	private IOptionService iOptionService;

	@Autowired
	private IActionService iActionService;

	@Autowired
	private IOptionActionService iOptionActionService;

	private static final Log LOG = LogFactory.getLog(OpcionesSistemaBean.class);
	private List<Option> options;
	private List<Level> levels;
	//private List<Level> levelsCopy;
	private List<Action> actions;
	private List<Action> actionsSelected;
	private Option option;
	private TreeNode root;
	private TreeNode select;
	private Action actionSelected;

	public OpcionesSistemaBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		initAttributes();
		levels = iLevelService.findAllEntity();
		loadActions();
		loadOptionsLevels();
	}
	
	public void loadActions() {
		actions = iActionService.findAllEntity();
		Collections.sort(actions, (Action p1, Action p2) -> p1.getId().compareTo(p2.getId()));
	}
	
	//TODO completar este metodo para las opciones del sistema
	public void filterLevels(Integer... positions) {
//		List<Integer> listTemp = new ArrayList<>(Arrays.asList(positions));
//		if(positions.length>0) {
//			levels = new ArrayList<>();
//		}else {
//			levels = new ArrayList<Level>(levelsCopy);
//		}
	}

	public void initAttributes() {
		option = new Option();
		select = null;
		actionsSelected = new ArrayList<>();
		actionSelected = new Action();
	}

	public void loadOptionsLevels() {
		loadOptionsByLevel();
		root = new DefaultTreeNode("Root", null);
		UtilWeb.loadRootTree(root, options, "opcion", "getOptions");
	}

	public void cleanAll() {
		initAttributes();
	}

	public void cleanForm() {
		option = new Option();
	}

	public void saveOption() {
		try {

			if (option.getId() == null) {
				option = iOptionService.persistEntity(option);
			} else {
				option = iOptionService.mergeEntity(option);
			}

			List<OptionAction> optionActions = option.getOptionActions();
			optionActions = optionActions == null ? new ArrayList<>() : optionActions;
			List<OptionAction> newOA = new ArrayList<>();
			for (Action a : actionsSelected) {
				boolean isNew = true;
				for (OptionAction uo : optionActions) {
					if (uo.getAction().getId() == a.getId()) {
						optionActions.remove(uo);
						isNew = false;
						break;
					}
				}
				if (isNew) {
					newOA.add(new OptionAction(option, a));
				}
			}

			iOptionActionService.updateOptionAction(newOA, optionActions);

			initAttributes();
			loadOptionsLevels();
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			LOG.error(e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void prepareNewOptionChild() {
		option = new Option();
		option.setOptionFather((Option) select.getData());
	}

	public void prepareEditOption() {
		option = (Option) select.getData();
		cargarActionsOption();
		cargarLevel();
	}

	public void cargarActionsOption() {
		actionsSelected = new ArrayList<>();
		List<Action> actionsTemp = new ArrayList<Action>(actions);
		for (OptionAction oa : option.getOptionActions()) {
			for (Action a : actionsTemp) {
				if (oa.getAction().getId() == a.getId()) {
					actionsSelected.add(a);
					actionsTemp.remove(a);
					break;
				}
			}
		}
	}

	public void cargarLevel() {
		try {
			if (option.getLevel() != null) {
				for (Level l : levels) {
					if (option.getLevel().getId() == l.getId()) {
						option.setLevel(l);
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	public void deleteOption() {
		try {
			option = (Option) select.getData();
			if (option != null) {
				iOptionService.deleteEntity(option);
				initAttributes();
				loadOptionsLevels();
				Messages.showMsg(Messages.MSG_INFO, "form1", null);
			}
		} catch (Exception e) {
			initAttributes();
			LOG.error(e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void loadOptionsByLevel() {
		options = iOptionService.findByLevel(1);
	}

	public void saveAction() {
		try {
			actionSelected.setAction(actionSelected.getAction().toUpperCase());
			if (actionSelected.getId() == null) {
				iActionService.persistEntity(actionSelected);
			} else {
				iActionService.mergeEntity(actionSelected);
			}
			loadActions();
			actionSelected = new Action();
			Messages.showMsg(Messages.MSG_INFO, "formListActions", null);
		} catch (Exception e) {
			LOG.error(e);
			Messages.showMsg(Messages.MSG_ERROR, "formListActions", null, e);
		}

	}

	public void deleteAction() {
		try {
			if (actionSelected.getId() != null) {
				iActionService.deleteEntity(actionSelected);
			}
			loadActions();
			actionSelected = new Action();
			Messages.showMsg(Messages.MSG_INFO, "formListActions", null);
		} catch (Exception e) {
			LOG.error(e);
			Messages.showMsg(Messages.MSG_ERROR, "formListActions", null, e);
		}
	}

	public void cancelAction() {
		actionSelected = new Action();
	}

	public List<Option> getOptions() {
		return options;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelect() {
		return select;
	}

	public void setSelect(TreeNode select) {
		this.select = select;
	}

	public List<Action> getActionsSelected() {
		return actionsSelected;
	}

	public void setActionsSelected(List<Action> actionsSelected) {
		this.actionsSelected = actionsSelected;
	}

	public List<Action> getActions() {
		return actions;
	}

	public Action getActionSelected() {
		return actionSelected;
	}

	public void setActionSelected(Action actionSelected) {
		this.actionSelected = actionSelected;
	}

}
