package com.proinsalud.sistemas.core.inmobiliaria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "comentary", schema = "inmobiliaria")
public class Comentary implements Serializable {

	private static final long serialVersionUID = 4868919240081938513L;

	@Id
	@Column(name = "id_comentary")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inmueble")
	private Inmueble inmueble;

	@Column(name = "createAt")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date createAt;


	@Column(name = "description", length = 500)
	private String description;

	public Comentary() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Comentary [id=" + id + ", createAt=" + createAt + ", description=" + description + "]";
	}

}
