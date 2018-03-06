package com.proinsalud.sistemas.core.documentary_management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ing Jhon Frey Diaz
 * @datetime 17/01/2018 - 11:58:15 p. m.
 */

@Entity
@Table(name = "level", schema = "documentary_management")
public class LevelTrd implements Serializable {

	private static final long serialVersionUID = -655320416466095407L;

	@Id
	@Column(name = "id_level")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name_level")
	private String nameLevel;

	@Column(name = "level_pos")
	private Integer levelPos;

	public LevelTrd() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameLevel() {
		return nameLevel;
	}

	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	public Integer getLevelPos() {
		return levelPos;
	}

	public void setLevelPos(Integer levelPos) {
		this.levelPos = levelPos;
	}

	@Override
	public String toString() {
		return "Level [id=" + id + ", nameLevel=" + nameLevel + ", levelPos=" + levelPos + "]";
	}

}
