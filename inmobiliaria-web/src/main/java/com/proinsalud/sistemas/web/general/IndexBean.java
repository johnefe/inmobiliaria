package com.proinsalud.sistemas.web.general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.convocatory.model.Convocatory;
import com.proinsalud.sistemas.core.convocatory.service.IConvocatoryService;
import com.proinsalud.sistemas.core.general.model.News;
import com.proinsalud.sistemas.core.general.service.INewsService;
import com.proinsalud.sistemas.core.util.enums.StateConvocatoryEnum;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;
import com.proinsalud.sistemas.web.util.component.PaginatorComponent;

/**
 * @author Mauricio Pinchao
 * @datetime 12/01/2018 - 4:14:27 p. m.
 *
 */
@ManagedBean(name = "indexBean")
@ViewScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = -7962834882558145380L;
	
	public static final Log LOG = App.getLogger(IndexBean.class);
	
	@Autowired
	private INewsService iNewsService;
	@Autowired
	private IConvocatoryService iConvocatoryService;
	
	private List<News> newsList;
	private List<Convocatory> convocatoryList = new ArrayList<>();
	
	private PaginatorComponent paginatorNews;
	private PaginatorComponent paginatorConvocatories;
	
	private News actual = null;
	private Convocatory convocatory = null;
	
	public IndexBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		newsList = iNewsService.findAllOrderBy();
		paginatorNews = new PaginatorComponent(newsList);
		loadDataC();
	}
	
	public void loadDataC() {
		List<Convocatory> lst = iConvocatoryService.findByAllEntityByState(StateConvocatoryEnum.Publicada.toString());
		for(Convocatory co : lst) {
			if(co.isOpenConvocatory() == true) {
				convocatoryList.add(co);
			}
		}
		paginatorConvocatories = new PaginatorComponent(convocatoryList);
	}
	
	public void loadNews(Long id) {
		try {
			convocatory = new Convocatory();
			actual = iNewsService.findEntityById(id);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	public void loadConvocatory(Long id) {
		try {
			actual = new News();
			convocatory = iConvocatoryService.findEntityById(id);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	public void cancel()
	{
		actual = null;
		convocatory = null;
	}
	public List<News> getNewsList() {
		return newsList;
	}

	public List<Convocatory> getConvocatoryList() {
		return convocatoryList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public void setConvocatoryList(List<Convocatory> convocatoryList) {
		this.convocatoryList = convocatoryList;
	}

	public PaginatorComponent getPaginatorNews() {
		return paginatorNews;
	}

	public PaginatorComponent getPaginatorConvocatories() {
		return paginatorConvocatories;
	}

	public void setPaginatorNews(PaginatorComponent paginatorNews) {
		this.paginatorNews = paginatorNews;
	}

	public void setPaginatorConvocatories(PaginatorComponent paginatorConvocatories) {
		this.paginatorConvocatories = paginatorConvocatories;
	}

	public News getActual() {
		return actual;
	}

	public Convocatory getConvocatory() {
		return convocatory;
	}

	public void setActual(News actual) {
		this.actual = actual;
	}

	public void setConvocatory(Convocatory convocatory) {
		this.convocatory = convocatory;
	}
}
