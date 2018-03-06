package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Mauricio Pinchao
 * @datetime 6/02/2018 - 7:46:47 a. m.
 *
 */
@Entity
@Table(name = "service", schema = "digital_turn")
@NamedQueries({
	@NamedQuery(name = "Service.findAllEntityFather", query = "SELECT s FROM Service s WHERE s.serviceFather = null"),
	@NamedQuery(name = "Service.findEntityByIdFather", query = "SELECT s FROM Service s WHERE s.serviceFather.id=:idFather"),
})
public class Service implements Serializable {

	private static final long serialVersionUID = -3413624102709496866L;
	
	@Id
	@Column(name = "id_service")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_service", length = 100)
	private String nameService;
	
	@Column(name = "code")
	private String code;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_service_father")
	private Service serviceFather;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceFather")
	private List<Service> services;
	
	public Service() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNameService() {
		return nameService;
	}

	public String getCode() {
		return code;
	}

	public Service getServiceFather() {
		return serviceFather;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setServiceFather(Service serviceFather) {
		this.serviceFather = serviceFather;
	}

	public List<Service> getServices() {
		return services;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", nameService=" + nameService + ", code=" + code + ", serviceFather=" + serviceFather + "]";
	}
}
