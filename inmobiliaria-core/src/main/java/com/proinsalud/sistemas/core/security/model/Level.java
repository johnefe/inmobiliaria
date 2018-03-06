package com.proinsalud.sistemas.core.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Es la tabla level, define el nivel de la opcion
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:02:38 a. m.
 *
 */
@Entity
@Table(name = "level", schema = "security")
public class Level implements Serializable {

	private static final long serialVersionUID = -5016017845295363460L;

	@Id
	@Column(name = "id_level")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "level_pos")
	private Integer levelPos;

	@Column(name = "is_resource")
	private boolean isResource;

	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "level")
	// private List<Option> options;

	public Level() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevelPos() {
		return levelPos;
	}

	public void setLevelPos(Integer levelPos) {
		this.levelPos = levelPos;
	}

	public boolean isResource() {
		return isResource;
	}

	public void setResource(boolean isResource) {
		this.isResource = isResource;
	}

	@Override
	public String toString() {
		return "Level [id=" + id + ", name=" + name + ", description=" + description + ", levelPos=" + levelPos + ", isResource=" + isResource + "]";
	}

}
