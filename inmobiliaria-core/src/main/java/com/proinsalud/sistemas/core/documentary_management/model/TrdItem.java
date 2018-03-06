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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Esta clase contiene la información del trd_item
 * 
 * @author Ing Jhon Frey Diaz
 * @datetime 16/01/2018 - 17:25:15 p. m.
 */
@Entity
@Table(name = "trd_item", schema = "documentary_management")
@NamedQueries({ @NamedQuery(name = "TrdItem.findAllEntityByLevelTrdItem", query = "SELECT t FROM TrdItem t WHERE t.level.levelPos=:level"), })
public class TrdItem implements Serializable {

	private static final long serialVersionUID = 1361061637412253895L;

	@Id
	@Column(name = "id_trd_item")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_level")
	private LevelTrd level;

	public TrdItem(String code, String name, LevelTrd level) {

		this.code = code;
		this.name = name;
		this.level = level;
	}

	public TrdItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LevelTrd getLevel() {
		return level;
	}

	public void setLevel(LevelTrd level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "TrdItem [id=" + id + ", code=" + code + ", name=" + name + ", level=" + level + "]";
	}

}
