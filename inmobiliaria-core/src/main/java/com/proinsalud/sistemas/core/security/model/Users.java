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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.proinsalud.sistemas.core.general.model.Person;

/**
 * 
 * @author Andres Santacruz
 * @datetime 22/12/2017 - 10:35:42 a. m.
 *
 */
@Entity
@Table(name = "users", schema = "security")
@NamedQueries({ @NamedQuery(name = "Users.findUsersProfileTemplate", query = "SELECT u FROM Users u WHERE u.isProfile IS TRUE"), @NamedQuery(name = "Users.findEntityByUsername", query = "SELECT u FROM Users  u WHERE u.username=:username") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Users implements Serializable {

	private static final long serialVersionUID = 5836130611557642787L;

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", length = 20)
	private String username;

	@Column(name = "password", length = 101)
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "is_profile")
	private boolean isProfile;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_id_person")
	private Person person;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<UserOption> userOptions;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<UserAuthority> userAuthorities;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<UserHistorial> userHistorial;

	@Column(name = "time_session")
	private Integer timeSession = 3600;

	public Users() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<UserOption> getUserOptions() {
		return userOptions;
	}

	public void setUserOptions(List<UserOption> userOptions) {
		this.userOptions = userOptions;
	}

	public boolean isProfile() {
		return isProfile;
	}

	public void setProfile(boolean isProfile) {
		this.isProfile = isProfile;
	}

	public List<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(List<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public List<UserHistorial> getUserHistorial() {
		return userHistorial;
	}

	public void setUserHistorial(List<UserHistorial> userHistorial) {
		this.userHistorial = userHistorial;
	}

	public Integer getTimeSession() {
		return timeSession;
	}

	public void setTimeSession(Integer timeSession) {
		this.timeSession = timeSession;
	}

	@Override
	public String toString() {
		return "Userr [id=" + id + ", username=" + username + ", password=" + password + ", person=" + person + "]";
	}

}
