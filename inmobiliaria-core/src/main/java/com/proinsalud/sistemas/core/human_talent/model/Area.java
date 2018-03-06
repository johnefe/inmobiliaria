package com.proinsalud.sistemas.core.human_talent.model;

import java.io.Serializable;
import java.util.Collections;
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

import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.util.comparators.GeneralComparator;

/**
 * Esta clase contiene la información del Area
 * 
 * @author Ing Jhon Frey Diaz
 * @datetime 16/01/2018 - 17:25:15 p. m.
 */
@Entity
@Table(name = "area", schema = "human_talent")
@NamedQueries({ @NamedQuery(name = "Area.findAreaRoot", query = "SELECT u FROM Area u WHERE u.areaFather IS NULL ORDER BY u.name ASC") })
public class Area implements Serializable {

	private static final long serialVersionUID = 2739058427327062563L;

	@Id
	@Column(name = "id_area")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 50)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area_father")
	private Area areaFather;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	private List<Trd> trds;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "areaFather")
	private List<Area> areas;

	public Area() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Area getAreaFather() {
		return areaFather;
	}

	public void setAreaFather(Area areaFather) {
		this.areaFather = areaFather;
	}

	public List<Trd> getTrds() {
		return trds;
	}

	public void setTrds(List<Trd> trds) {
		this.trds = trds;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	/**
	 * Organiza las areas y los items por nombre en orden ascendente
	 * 
	 * @author Andres Santacruz
	 * @datetime 25/01/2018 - 8:01:19 a. m.
	 *
	 * @param unorder
	 */
	public static void organizedItemsAsc(List<Area> unorder) {
		Collections.sort(unorder, GeneralComparator.AreaCompareByName);
		for (Area o : unorder) {
			List<Area> lst = o.getAreas();
			if (!lst.isEmpty()) {
				organizedItemsAsc(lst);
			} else {
				GeneralComparator.organized(GeneralComparator.TrdCompareByName, o.getTrds(), "getTrds");
			}
		}
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", name=" + name + "]";
	}

}
