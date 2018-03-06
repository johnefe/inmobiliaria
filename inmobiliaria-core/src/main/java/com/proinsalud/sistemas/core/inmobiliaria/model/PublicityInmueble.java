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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "publicity_inmueble", schema = "inmobiliaria")
public class PublicityInmueble implements Serializable {

	private static final long serialVersionUID = 5612472906009550627L;

	@Id
	@Column(name = "id_publicity_inmueble")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inmueble")
	private Inmueble inmueble;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_publicity")
	private TypePublicity typePublicity;

	@Column(name = "date_ini")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date dateIni;

	@Column(name = "date_end")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date dateEnd;


	@Column(name = "state")
	private char state;

	public PublicityInmueble() {
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

	public TypePublicity getTypePublicity() {
		return typePublicity;
	}

	public void setTypePublicity(TypePublicity typePublicity) {
		this.typePublicity = typePublicity;
	}

	public Date getDateIni() {
		return dateIni;
	}

	public void setDateIni(Date dateIni) {
		this.dateIni = dateIni;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "PublicityInmueble [id=" + id + ", dateIni=" + dateIni + ", dateEnd=" + dateEnd + ", state=" + state
				+ "]";
	}
}
