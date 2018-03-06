package com.proinsalud.sistemas.web.human_talent;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.human_talent.model.Area;
import com.proinsalud.sistemas.core.human_talent.service.IAreaService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 25/01/2018 - 7:22:41 a. m.
 */
@ManagedBean
@ViewScoped
public class AreaBean implements Serializable {

	private static final long serialVersionUID = -409448775689652139L;

	public static final Log LOG = App.getLogger(AreaBean.class);

	@Autowired
	private IAreaService iAreaService;

	private TreeNode root;
	private TreeNode select;
	private Area area;
	private List<Area> areas;

	public AreaBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		initAttributes();
		loadRootTree();
	}

	public void initAttributes() {
		select = null;
		area = new Area();
	}

	public void loadAreaRoot() {
		areas = iAreaService.findAreaRoot();
	}

	public void loadRootTree() {
		loadAreaRoot();
		root = new DefaultTreeNode("Root", null);
		UtilWeb.loadRootTree(root, areas, "area", "getAreas");
	}
	
	public void loadSelect() {
		try {
			area = new Area();
			if (select != null) {
				if (select.getData() instanceof Area) {
					area.setAreaFather((Area) select.getData());
				}
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void cancel() {
		initAttributes();
		UtilWeb.setRoot(root, false, false);
	}

	public void saveArea() {
		try {
			if (area.getId() == null) {
				area = iAreaService.persistEntity(area);
			}
			Long idArea = area.getId();
			loadRootTree();
			initAttributes();
			select = UtilWeb.findItemRoot(root, idArea, true, true);
			loadSelect();
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public TreeNode getSelect() {
		return select;
	}

	public void setSelect(TreeNode select) {
		this.select = select;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public TreeNode getRoot() {
		return root;
	}

	public List<Area> getAreas() {
		return areas;
	}
}
