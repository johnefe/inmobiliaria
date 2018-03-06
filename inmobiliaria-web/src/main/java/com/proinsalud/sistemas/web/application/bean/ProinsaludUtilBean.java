package com.proinsalud.sistemas.web.application.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;

import com.proinsalud.sistemas.web.util.App;

/**
 * @author Andres Santacruz
 * @datetime 26/12/2017 - 5:04:04 p. m.
 *
 */
@ManagedBean
@RequestScoped
public class ProinsaludUtilBean implements Serializable {

	private static final long serialVersionUID = 8433204091320229150L;

	private static final Log LOG = App.getLogger(ProinsaludUtilBean.class);

	public ProinsaludUtilBean() {
		super();
	}

	@PostConstruct
	public void init() {
	}

	/**
	 * Metodo para recortar un texto al tamaño pasado por parametro
	 * El parametro points sirve para mostrar los '...' despuesd del texto cortado en el caso de que sea true
	 * @author Andres Santacruz
	 * @datetime 10/01/2018 - 10:36:43 a. m.
	 *
	 * @param text
	 * @param size
	 * @param points
	 * @return
	 */
	public String truncate(String text, int size, boolean points) {
		try {
			text = text != null ? text : "";
			return !text.isEmpty() && text.length() > size ? text.substring(0, size).concat((points ? "..." : "")) : text;
		} catch (Exception e) {
			LOG.error(e);
			return "";
		}
	}
}
