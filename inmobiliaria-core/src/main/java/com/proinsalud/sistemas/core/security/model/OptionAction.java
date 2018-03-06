package com.proinsalud.sistemas.core.security.model;

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
 * Es la tabla intermedia entre option y action
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:02:00 a. m.
 *
 */
@Entity
@Table(name = "option_action", schema = "security")
@NamedQueries({ @NamedQuery(name = "OptionAction.findAllEntityByOption", query = "SELECT o FROM OptionAction o WHERE o.option.id=:idOption ORDER BY o.option.name ASC"), })
public class OptionAction implements Serializable {

	private static final long serialVersionUID = -3798017667197465540L;

	@Id
	@Column(name = "id_option_action")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_option")
	private Option option;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_action")
	private Action action;

	public OptionAction() {
		super();
	}

	public OptionAction(Option option, Action action) {
		super();
		this.option = option;
		this.action = action;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

}
