package com.proinsalud.sistemas.core.convocatory.model;

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

import com.proinsalud.sistemas.core.security.model.Users;

/**
 * Es la tabla intermedia entre step_order_convocatory_type_convocatory y users
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "step_order_convocatory_type_convocatory_users", schema = "convocatory")
@NamedQueries({ @NamedQuery(name = "StepOrderConvocatoryTyPeConvocatoryUsers.findAllEntityByIdUser", query = "SELECT c FROM StepOrderConvocatoryTyPeConvocatoryUsers c WHERE c.user.id=:idUser") })
public class StepOrderConvocatoryTyPeConvocatoryUsers implements Serializable {

	private static final long serialVersionUID = -9072138561869550177L;

	@Id
	@Column(name = "id_step_order_convocatory_type_convocatory_users")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_step_order_convocatory_type_convocatory")
	private StepOrderConvocatoryTypeConvocatory stepOCTC;

	public StepOrderConvocatoryTyPeConvocatoryUsers() {
		super();
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

	public StepOrderConvocatoryTypeConvocatory getStepOCTC() {
		return stepOCTC;
	}

	public void setStepOCTC(StepOrderConvocatoryTypeConvocatory stepOCTC) {
		this.stepOCTC = stepOCTC;
	}

	@Override
	public String toString() {
		return "StepOrderConvocatoryTyPeConvocatoryUsers [id=" + id + "]";
	}

}
