package com.proinsalud.sistemas.core.security.model;

import java.io.Serializable;
import java.util.Date;

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
 * @author Jhon Frey Diaz
 * @datetime 22/12/2017 - 11:01:54 a. m.
 *
 */
@Entity
@Table(name = "user_access_history", schema = "security")
public class UserHistorial implements Serializable {

	private static final long serialVersionUID = -3475052239299645403L;

	@Id
	@Column(name = "id_history")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;

	@Column(name = "date_access")
	private Date dateAccess;

	@Column(name = "ip_access")
	private String ipAccess;

	@Column(name = "detail")
	private String detail;

	public UserHistorial() {
		super();
	}

	public UserHistorial(Users user, Date dateAccess, String ipAccess, String detail) {
		this.user = user;
		this.dateAccess = dateAccess;
		this.ipAccess = ipAccess;
		this.detail = detail;
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

	public Date getDateAccess() {
		return dateAccess;
	}

	public void setDateAccess(Date dateAccess) {
		this.dateAccess = dateAccess;
	}

	public String getIpAccess() {
		return ipAccess;
	}

	public void setIp_access(String ipAccess) {
		this.ipAccess = ipAccess;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "UserHistorial [id=" + id + ", user=" + user + ", dateAccess=" + dateAccess + ", ipAccess=" + ipAccess + ", detail=" + detail + "]";
	}

}
