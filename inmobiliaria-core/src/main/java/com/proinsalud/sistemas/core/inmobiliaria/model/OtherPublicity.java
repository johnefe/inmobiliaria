package com.proinsalud.sistemas.core.inmobiliaria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "other_publicity", schema = "inmobiliaria")
public class OtherPublicity implements Serializable {

	private static final long serialVersionUID = -9050516769917616581L;

	@Id
	@Column(name = "id_other_publicity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_publicity")
	private TypePublicity typePublicity;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person")
	private Persons person;

	
	@Column(name = "title")
	private String title;

	
	@Column(name = "url_image")
	private String urlImage;

	
	@Column(name = "description", length = 500)
	private String description;

	
	@Column(name = "state")
	private boolean state;

	public OtherPublicity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypePublicity getTypePublicity() {
		return typePublicity;
	}

	public void setTypePublicity(TypePublicity typePublicity) {
		this.typePublicity = typePublicity;
	}

	public Persons getPerson() {
		return person;
	}

	public void setPerson(Persons person) {
		this.person = person;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "OtherPublicity [id=" + id + ", typePublicity=" + typePublicity + ", title=" + title + ", urlImage="
				+ urlImage + ", description=" + description + ", state=" + state + "]";
	}

}
