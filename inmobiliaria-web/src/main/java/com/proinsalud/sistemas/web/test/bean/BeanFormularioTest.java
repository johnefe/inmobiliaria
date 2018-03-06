/**
 * @author Andres Santacruz
 * @datetime 27/11/2017 - 3:30:00 p. m.
 */
package com.proinsalud.sistemas.web.test.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import com.proinsalud.sistemas.web.application.component.CustomFilter;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;

/**
 * @author Andres Santacruz
 * @datetime 27/11/2017 - 3:30:00 p. m.
 *
 */
@ManagedBean(name = "formularioTest")
@ViewScoped
public class BeanFormularioTest implements Serializable {

	private static final Logger LOG = Logger.getLogger(CustomFilter.class);

	@ManagedProperty(value = "#{eventObject}")
	private EventObject dTurn;

	private static final long serialVersionUID = 6443035065249165782L;

	private String text;

	public BeanFormularioTest() {
		LOG.info("---------------------BeanFormularioTest");
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);

		EventListener dtel = new EventListener() {

			@Override
			public void notifyChange(Object obj) {
				// System.out.println("USUARIO: " + App.getUser().getUsername() + " -" + obj);
				text = obj.toString();
				RequestContext.getCurrentInstance().update("text");
			}
		};
		dTurn.addListener(dtel);
		// dTurn.setObj(App.getUser().getUsername() + " " + new Date());

	}

	public void sendText() {
		if (text != null && text.length() > 0) {
			String t = dTurn.getObj() != null ? dTurn.getObj().toString() : "";
			dTurn.setObj(t + text);
		}
	}

	public void showMessage(String msg) {
		Messages.showMsg(Messages.MSG_INFO, "form1", msg);
	}

	public EventObject getdTurn() {
		return dTurn;
	}

	public void setdTurn(EventObject dTurn) {
		this.dTurn = dTurn;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
