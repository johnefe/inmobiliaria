package com.proinsalud.sistemas.core.security.model;

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
 * Es la tabla option
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:02:19 a. m.
 *
 */
@Entity
@Table(name = "option", schema = "security")
@NamedQueries({ @NamedQuery(name = "Option.findByLevel", query = "SELECT o FROM Option o JOIN FETCH o.level WHERE o.level.levelPos=:levelPos ORDER BY o.name ASC") })
public class Option implements Serializable {

	private static final long serialVersionUID = 683403364252464699L;

	@Id
	@Column(name = "id_option")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_level")
	private Level level;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_option_father")
	private Option optionFather;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "optionFather")
	private List<Option> options;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "option")
	private List<OptionAction> optionActions;

	@Column(name = "detail")
	private String detail;

	@Column(name = "name")
	private String name;

	@Column(name = "active")
	private boolean active;

	public Option() {
		super();
		active = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Option getOptionFather() {
		return optionFather;
	}

	public void setOptionFather(Option optionFather) {
		this.optionFather = optionFather;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public List<OptionAction> getOptionActions() {
		return optionActions;
	}

	public void setOptionActions(List<OptionAction> optionActions) {
		this.optionActions = optionActions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Option [id=" + id + ", level=" + level + ", detail=" + detail + ", name=" + name + ", active=" + active + "]";
	}

}
