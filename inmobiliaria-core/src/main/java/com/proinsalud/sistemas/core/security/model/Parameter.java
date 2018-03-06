package com.proinsalud.sistemas.core.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Es la tabla parametro de la aplicacion
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:01:45 a. m.
 *
 */
@Entity
@Table(name = "parameter", schema = "security")
public class Parameter implements Serializable {

	private static final long serialVersionUID = -3983506806226654289L;

	@Id
	@Column(name = "id_parameter")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "num_favorites")
	private Integer numFavorites;

	@Column(name = "name_application")
	private String nameApplication;

	@Column(name = "icon_app")
	private String iconApp;

	public Parameter() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumFavorites() {
		return numFavorites;
	}

	public void setNumFavorites(Integer numFavorites) {
		this.numFavorites = numFavorites;
	}

	public String getNameApplication() {
		return nameApplication;
	}

	public void setNameApplication(String nameApplication) {
		this.nameApplication = nameApplication;
	}

	public String getIconApp() {
		return iconApp;
	}

	public void setIconApp(String iconApp) {
		this.iconApp = iconApp;
	}

	@Override
	public String toString() {
		return "Parameter [id=" + id + ", numFavorites=" + numFavorites + ", nameApplication=" + nameApplication + ", iconApp=" + iconApp + "]";
	}

}
