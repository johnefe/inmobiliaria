package com.proinsalud.sistemas.core.documentary_management.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.proinsalud.sistemas.core.human_talent.model.Area;

/**
 * Esta clase contiene la información del trd
 * 
 * @author Ing Jhon Frey Diaz
 * @datetime 16/01/2018 - 17:25:15 p. m.
 */
@Entity
@Table(name = "trd", schema = "documentary_management")
public class Trd implements Serializable {

	private static final long serialVersionUID = -2618853322255094145L;
	@Id
	@Column(name = "id_trd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cod_serial", length = 50)
	private String codSerial;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_trd_item")
	private TrdItem trdItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_trd_father")
	private Trd trdFather;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area")
	private Area area;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trdFather")
	private List<Trd> trds;

	public Trd() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodSerial() {
		return codSerial;
	}

	public void setCodSerial(String codSerial) {
		this.codSerial = codSerial;
	}

	public TrdItem getTrdItem() {
		return trdItem;
	}

	public void setTrdItem(TrdItem trdItem) {
		this.trdItem = trdItem;
	}

	public Trd getTrdFather() {
		return trdFather;
	}

	public void setTrdFather(Trd trdFather) {
		this.trdFather = trdFather;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Trd> getTrds() {
		return trds;
	}

	public void setTrds(List<Trd> trds) {
		this.trds = trds;
	}

	@Override
	public String toString() {
		return "Trd [id=" + id + ", codSerial=" + codSerial + "]";
	}

}
