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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.proinsalud.sistemas.core.general.model.OptionFavorite;

/**
 * Es la tabla para la relacion intermedia entre users y option
 * @author Andres Santacruz
 * @datetime 18/01/2018 - 11:00:34 a. m.
 *
 */
@Entity
@Table(name = "user_option", schema = "security")
@NamedQueries({
		@NamedQuery(name = "UserOption.findByUser", query = "SELECT u FROM UserOption u WHERE u.user.id=:idUser"),
		@NamedQuery(name = "UserOption.findEntityByOptionUser", query = ""
				+ "SELECT u FROM UserOption u WHERE u.option.id=:idOption AND u.user.id=:idUser"),
		@NamedQuery(name = "UserOption.deleteAllEntityByIdUser", query = "DELETE FROM UserOption u WHERE u.user.id=:idUser"), })
public class UserOption implements Serializable {

	private static final long serialVersionUID = 8134488115347196426L;

	@Id
	@Column(name = "id_user_option")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_option")
	private Option option;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userOption")
	private List<UserOptionAction> userOptionActions;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userOption")
	private OptionFavorite userOptionFavorite;

	public UserOption() {
		super();
	}

	public UserOption(Option option, Users user) {
		super();
		this.option = option;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public List<UserOptionAction> getUserOptionActions() {
		return userOptionActions;
	}

	public void setUserOptionActions(List<UserOptionAction> userOptionActions) {
		this.userOptionActions = userOptionActions;
	}

	public OptionFavorite getUserOptionFavorite() {
		return userOptionFavorite;
	}

	public void setUserOptionFavorite(OptionFavorite userOptionFavorite) {
		this.userOptionFavorite = userOptionFavorite;
	}

	@Override
	public String toString() {
		return "UserOption [id=" + id + ", option=" + option + ", user=" + user + ", userOptionActions="
				+ userOptionActions + "]";
	}

}
