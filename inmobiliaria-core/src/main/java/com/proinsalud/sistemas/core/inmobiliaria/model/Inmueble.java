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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.proinsalud.sistemas.core.general.model.Municipality;
import com.proinsalud.sistemas.core.security.model.Users;

@Entity
@Table(name="inmueble", schema = "inmobiliaria")
@NamedQueries({ @NamedQuery(name = "Inmueble.findArriendo", query = "SELECT i FROM Inmueble i WHERE upper(i.typeBussines.nameTypeBussines)=:nArriendo OR upper(i.typeBussines.nameTypeBussines)=:nAnticres OR upper(i.typeBussines.nameTypeBussines)=:nVenta ORDER BY i.id DESC")})
public class Inmueble implements Serializable {

	private static final long serialVersionUID = 1436245850272148180L;
	
	
	@Id
	@Column(name = "id_inmueble")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person")
	private Persons person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipality")
	private Municipality municipality;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;
	
	
	@Column(name = "title_inmueble")
	private String titleInmueble;	
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_inmueble")
	private TipoInmueble tipoInmueble;


	@Column(name = "bedroom")
	private Integer bedroom;


	@Column(name = "bathroom")
	private Integer bathroom;


	@Column(name = "kitchen")
	private Integer kitchen;


	@Column(name = "address")
	private String address;

	
	@Column(name = "state")
	private char state;


	@Column(name = "price")
	private String price;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_bussines")
	private TypeBussines typeBussines;


	@Column(name = "img_url_one")
	private String imgUrlOne;

	@Column(name = "img_url_two")
	private String imgUrlTwo;


	@Column(name = "img_url_three")
	private String imgUrlThree;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persons getPerson() {
		return person;
	}

	public void setPerson(Persons person) {
		this.person = person;
	}

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public TipoInmueble getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(TipoInmueble tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}

	public Integer getBedroom() {
		return bedroom;
	}

	public void setBedroom(Integer bedroom) {
		this.bedroom = bedroom;
	}

	public Integer getBathroom() {
		return bathroom;
	}

	public void setBathroom(Integer bathroom) {
		this.bathroom = bathroom;
	}

	public Integer getKitchen() {
		return kitchen;
	}

	public void setKitchen(Integer kitchen) {
		this.kitchen = kitchen;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public TypeBussines getTypeBussines() {
		return typeBussines;
	}

	public void setTypeBussines(TypeBussines typeBussines) {
		this.typeBussines = typeBussines;
	}

	public String getImgUrlOne() {
		return imgUrlOne;
	}

	public void setImgUrlOne(String imgUrlOne) {
		this.imgUrlOne = imgUrlOne;
	}

	public String getImgUrlTwo() {
		return imgUrlTwo;
	}

	public void setImgUrlTwo(String imgUrlTwo) {
		this.imgUrlTwo = imgUrlTwo;
	}

	public String getImgUrlThree() {
		return imgUrlThree;
	}

	public void setImgUrlThree(String imgUrlThree) {
		this.imgUrlThree = imgUrlThree;
	}

	public String getTitleInmueble() {
		return titleInmueble;
	}

	public void setTitleInmueble(String titleInmueble) {
		this.titleInmueble = titleInmueble;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Inmueble [id=" + id + ", titleInmueble=" + titleInmueble + ", bedroom=" + bedroom + ", bathroom="
				+ bathroom + ", kitchen=" + kitchen + ", address=" + address + ", state=" + state + ", price=" + price
				+ ", imgUrlOne=" + imgUrlOne + ", imgUrlTwo=" + imgUrlTwo + ", imgUrlThree=" + imgUrlThree + "]";
	}

}
