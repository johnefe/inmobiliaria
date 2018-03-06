package com.proinsalud.sistemas.core.documentary_management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Esta clase contiene la información del archivo para la gestion documental
 * 
 * @author Ing Jhon Frey Diaz
 * @datetime 16/01/2018 - 17:25:15 p. m.
 */
@Entity
@Table(name = "file_dg", schema = "documentary_management")
public class FileDG implements Serializable {

	private static final long serialVersionUID = -457248502428882698L;

	@Id
	@Column(name = "id_file")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name_file", length = 50)
	private String nameFile;

	@Column(name = "route_f", length = 100)
	private String routeF;

	@Column(name = "route_d", length = 100)
	private String routeD;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_trd")
	private Trd trd;

	public FileDG() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public String getRouteF() {
		return routeF;
	}

	public void setRouteF(String routeF) {
		this.routeF = routeF;
	}

	
	public String getRouteD() {
		return routeD;
	}

	public void setRouteD(String routeD) {
		this.routeD = routeD;
	}

	public Trd getTrd() {
		return trd;
	}

	public void setTrd(Trd trd) {
		this.trd = trd;
	}

	@Override
	public String toString() {
		return "FileDG [id=" + id + ", nameFile=" + nameFile + ", routeF=" + routeF + ", routeD=" + routeD + ", trd="
				+ trd + "]";
	}

	
}