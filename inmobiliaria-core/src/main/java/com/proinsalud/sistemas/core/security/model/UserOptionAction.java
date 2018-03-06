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
import javax.persistence.Table;

/**
 * 
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 10:59:36 a. m.
 *
 */
@Entity
@Table(name = "user_option_action", schema = "security")
public class UserOptionAction implements Serializable {

	private static final long serialVersionUID = 6285585719770823122L;

	@Id
	@Column(name = "id_user_option_action")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user_option")
	private UserOption userOption;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_option_action")
	private OptionAction optionAction;

	public UserOptionAction() {
		super();
	}

	public UserOptionAction(UserOption userOption, OptionAction optionAction) {
		super();
		this.userOption = userOption;
		this.optionAction = optionAction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserOption getUserOption() {
		return userOption;
	}

	public void setUserOption(UserOption userOption) {
		this.userOption = userOption;
	}

	public OptionAction getOptionAction() {
		return optionAction;
	}

	public void setOptionAction(OptionAction optionAction) {
		this.optionAction = optionAction;
	}

	@Override
	public String toString() {
		return "UserOptionAction [id=" + id + ", userOption=" + userOption + ", optionAction=" + optionAction + "]";
	}

}
