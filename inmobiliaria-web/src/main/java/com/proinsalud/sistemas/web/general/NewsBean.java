package com.proinsalud.sistemas.web.general;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.general.model.News;
import com.proinsalud.sistemas.core.general.service.INewsService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.component.PaginatorComponent;

/**
 * @author Mauricio Pinchao
 * @datetime 22/12/2017 - 9:48:27 a. m.
 *
 */
@ManagedBean(name = "newsBean")
@ViewScoped
public class NewsBean {
	
	public static final Log LOG = App.getLogger(NewsBean.class); 
	
	@Autowired
	private INewsService iNewsService;
	
	private News actual = null;
	private PaginatorComponent paginator;
	
	private List<News> newsList;
	
	private boolean show = false;
	
	public NewsBean() {
		super();	
	}
	
	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		newsList = iNewsService.findAllOrderBy();
		paginator = new PaginatorComponent(newsList);
	}
	
	public void nuevo() {
		actual = new News();
	}
	
	public void saveNews() {
		try {
			if(actual != null) {
				validarEnlace();
				if(actual.getId() == null) {
					actual = iNewsService.persistEntity(actual);
				} else {
					actual = iNewsService.mergeEntity(actual);
				}
				newsList = iNewsService.findAllOrderBy();
			}
			actual = null;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	public void loadNews(Long id) {
		try {
			actual = iNewsService.findEntityById(id);
		} catch (Exception e) {
			LOG.error("ERROR: loadNews() -> " + e.getMessage());
		}
	}
	
	public void deleteNews(News n) {
		try {
			iNewsService.deleteEntity(n);
			newsList = iNewsService.findAllOrderBy();
			actual = null;
			Messages.showMsg(Messages.MSG_INFO, "form1", "La noticia se elimino correctamente");
		} catch (Exception e) {
			LOG.error("ERROR: deleteNews() ->" + e.getMessage());
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}
	
	public void cancel() {
		actual = null;
	}
	
	public void validarEnlace() {
		String link = actual.getEnlace();
		if(link.startsWith("http://") || link.startsWith("https://")) {
			actual.setEnlace(link);
		}else {
			link = "//" + link;
			actual.setEnlace(link);
		}
	}

	public News getActual() {
		return actual;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setActual(News actual) {
		this.actual = actual;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public PaginatorComponent getPaginator() {
		return paginator;
	}

	public void setPaginator(PaginatorComponent paginator) {
		this.paginator = paginator;
	}
}
