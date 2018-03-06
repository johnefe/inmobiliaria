package com.proinsalud.sistemas.web.test.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * @author Andres Santacruz
 * @datetime 19/02/2018 - 11:54:07 a. m.
 *
 */
@ManagedBean(name = "eventObject")
@ApplicationScoped
public class EventObject {

	private List<EventListener> listeners = new ArrayList<EventListener>();

	private Object obj;

	public EventObject() {
		super();
	}

	@PostConstruct
	public void init() {
		System.out.println("Se inicio el bean de EventObject");
	}

	public EventObject(Object source, Object obj) {
		this.obj = obj;
	}

	public void addListener(EventListener listener) {
		listeners.add(listener);
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
		for (EventListener dt : listeners) {
			dt.notifyChange(this.obj);
		}
	}

}
