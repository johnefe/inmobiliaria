package com.proinsalud.sistemas.web.documentary_management;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.documentary_management.model.LevelTrd;
import com.proinsalud.sistemas.core.documentary_management.model.TrdItem;
import com.proinsalud.sistemas.core.documentary_management.service.ILevelTrdService;
import com.proinsalud.sistemas.core.documentary_management.service.ITrdItemService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Ing. Jhon Frey Diaz
 * @datetime 17/01/2018 - 10:00:41 a. m.
 */
@ManagedBean
@ViewScoped
public class TrdItemBean implements Serializable {

	private static final long serialVersionUID = -2404247455135935032L;

	public static final Log LOG = App.getLogger(TrdItemBean.class);

	@Autowired
	private ITrdItemService iTrdItemService;

	@Autowired
	private ILevelTrdService iLevelTrdService;

	private List<TrdItem> trdItems;
	private TrdItem trdItem;
	private List<LevelTrd> levelTrds;
	private boolean showForm;

	public TrdItemBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		showForm = false;
		trdItems = iTrdItemService.findAllEntity();
	}

	public void loadForm() {
		showForm = true;
		levelTrds = iLevelTrdService.findAllEntity();
		trdItem = new TrdItem();
	}

	public void saveTrdItem() {
		try {
			if (trdItem != null) {
				if (trdItem.getId() != null) {

					trdItem = iTrdItemService.mergeEntity(trdItem);
				} else {

					trdItem = iTrdItemService.persistEntity(trdItem);
				}
				trdItems = iTrdItemService.findAllEntity();
				UtilWeb.uDataTable("form1:tblItemsTrd");
				showForm = false;
			}
			trdItem = new TrdItem();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}

	}

	public void loadTrdItemById(long id) {
		showForm = true;
		try {
			levelTrds = iLevelTrdService.findAllEntity();
			trdItem = iTrdItemService.findEntityById(id);
			trdItem.setLevel((LevelTrd) UtilWeb.loadObject(levelTrds, trdItem.getLevel()));
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}

	}

	public void cancel() {
		showForm = false;
		trdItem = null;
	}

	public TrdItem getTrdItem() {
		return trdItem;
	}

	public void setTrdItem(TrdItem trdItem) {
		this.trdItem = trdItem;
	}

	public List<TrdItem> getTrdItems() {
		return trdItems;
	}

	public void setTrdItems(List<TrdItem> trdItems) {
		this.trdItems = trdItems;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public List<LevelTrd> getLevelTrds() {
		return levelTrds;
	}
}
