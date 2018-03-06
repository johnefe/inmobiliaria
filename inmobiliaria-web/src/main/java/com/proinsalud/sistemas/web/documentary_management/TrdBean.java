package com.proinsalud.sistemas.web.documentary_management;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.documentary_management.model.TrdItem;
import com.proinsalud.sistemas.core.documentary_management.service.ITrdItemService;
import com.proinsalud.sistemas.core.documentary_management.service.ITrdService;
import com.proinsalud.sistemas.core.human_talent.model.Area;
import com.proinsalud.sistemas.core.human_talent.service.IAreaService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Ing. Jhon Frey Diaz
 * @datetime 22/01/2018 - 08:40:41 a. m.
 */
@ManagedBean
@ViewScoped
public class TrdBean implements Serializable {

	private static final long serialVersionUID = -2597844814005151029L;

	public static final Log LOG = App.getLogger(TrdBean.class);

	@Autowired
	private IAreaService iAreaService;

	@Autowired
	private ITrdItemService iTrdItemService;

	@Autowired
	private ITrdService iTrdService;

	private Trd trd;
	private Trd trdNew;
	private TreeNode root;
	private TreeNode select;
	private Area area;
	private List<TrdItem> trdItems;
	private List<Area> areas;
	private boolean showBtn;
	private boolean showForm;

	public TrdBean() {
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
		trd = null;
		area = null;
		trdItems = null;
		trdNew = null;
		showBtn = false;
		showForm = false;
	}

	public void loadAreaRoot() {
		areas = iAreaService.findAreaItemsRoot();
	}

	public void loadRootTree() {
		loadAreaRoot();
		root = new DefaultTreeNode("Root", null);
		UtilWeb.loadAreas(root, areas);
	}

	public void cleanForm() {
		try {
			trd = new Trd();
			area = null;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadTrdsItemsByLevel(Integer level) {
		try {
			trdItems = iTrdItemService.findAllEntityByLevelTrdItem(level);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadForm() {
		try {
			trdNew = new Trd();
			trdNew.setCodSerial("");
			if (select.getData() instanceof Area) {
				area = (Area) select.getData();
				trdNew.setArea(area);
				loadTrdsItemsByLevel(1);
			} else if (select.getData() instanceof Trd) {
				trd = (Trd) select.getData();
				trdNew.setTrdFather(trd);
				area = getAreaRecursivamente(trd);
				loadTrdsItemsByLevel(trd.getTrdItem().getLevel().getLevelPos() + 1);
			}

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadSelect() {
		try {
			area = null;
			trd = null;
			trdNew = null;
			showBtn = false;
			if (select != null) {
				if (select.getData() instanceof Area) {
					area = (Area) select.getData();
					showBtn = area.getAreas().isEmpty();
				} else if (select.getData() instanceof Trd) {
					trd = (Trd) select.getData();
					area = getAreaRecursivamente(trd);
					showBtn = true;
				}
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public Area getAreaRecursivamente(Trd trd) {
		if (trd != null) {
			while (trd.getTrdFather() != null) {
				trd = trd.getTrdFather();
			}
			return trd.getArea();
		}
		return null;
	}

	public void concatCode() {
		if (trdNew.getTrdItem() != null) {
			String codeFather = trd != null && trd.getCodSerial() != null ? trd.getCodSerial() : "";
			trdNew.setCodSerial(codeFather + trdNew.getTrdItem().getCode());
		}
	}

	public void cancel() {
		initAttributes();
	}

	public void saveTrd() {
		try {
			trdNew = iTrdService.persistEntity(trdNew);
			Long idItem = trdNew.getId();
			loadRootTree();
			TreeNode node = UtilWeb.findItemRoot(root, idItem, true, true);
			initAttributes();
			select = node;
			loadSelect();
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public Trd getTrdNew() {
		return trdNew;
	}

	public void setTrdNew(Trd trdNew) {
		this.trdNew = trdNew;
	}

	public Trd getTrd() {
		return trd;
	}

	public TreeNode getRoot() {
		return root;
	}

	public Area getArea() {
		return area;
	}

	public List<TrdItem> getTrdItems() {
		return trdItems;
	}

	public boolean isShowBtn() {
		return showBtn;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setSelect(TreeNode select) {
		this.select = select;
	}

	public TreeNode getSelect() {
		return select;
	}
}
