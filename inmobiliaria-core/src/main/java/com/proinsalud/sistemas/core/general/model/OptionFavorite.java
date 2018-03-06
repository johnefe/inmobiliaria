package com.proinsalud.sistemas.core.general.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.proinsalud.sistemas.core.security.model.UserOption;

@Entity
@Table(name = "option_favorite", schema = "general")
@NamedQueries({
	@NamedQuery(name = "OptionFavorite.findAllEntityByIdUser", query = "SELECT o FROM OptionFavorite o WHERE o.userOption.user.id=:idUser"),
})
public class OptionFavorite implements Serializable {

	private static final long serialVersionUID = 7651298295990075930L;
	
	@Id
	@Column(name = "id_option_favorite")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user_option")
	private UserOption userOption;

	public OptionFavorite() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public UserOption getUserOption() {
		return userOption;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserOption(UserOption userOption) {
		this.userOption = userOption;
	}
	
	@Override
	public String toString() {
		return "OptionFavorite [id=" + id + "]";
	}
}
