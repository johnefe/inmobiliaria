package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.util.CustomPostgreSQLEnumType;
import com.proinsalud.sistemas.core.util.enums.StateTurnEnum;

/**
 * @author Mauricio Pinchao
 * @datetime 6/02/2018 - 7:35:00 a. m.
 *
 */
@Entity
@Table(name = "digital_turn", schema = "digital_turn")
@TypeDef(name = "pgsql_enum_digital_turn", typeClass = CustomPostgreSQLEnumType.class)
public class DigitalTurn implements Serializable {

	private static final long serialVersionUID = -3055574800438076168L;

	@Id
	@Column(name = "id_digital_turn")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_persona")
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_point_service")
	private PointService pointService;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_module_user")
	private ModuleUser moduleUser;

	@Column(name = "hour_turn")
	private Date hourTurn;

	@Column(name = "hour_attention")
	private Date hourAttention;
	
	@Column(name = "hour_finished_attention")
	private Date hourFinishedAttention;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", columnDefinition = "digital_turn.state_turn")
	@Type(type = "pgsql_enum_digital_turn")
	private StateTurnEnum state;

	@Column(name = "identification_person")
	private String identificationPerson;
	
	@Column(name = "code_turn")
	private String codeTurn;

	@Column(name = "observation")
	private String observation;
	
	@Column(name = "id_service")
	private Long idService;
	
	@Column(name = "id_user")
	private Long idUser;
	
	public DigitalTurn() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public PointService getPointService() {
		return pointService;
	}

	public ModuleUser getModuleUser() {
		return moduleUser;
	}

	public Date getHourTurn() {
		return hourTurn;
	}

	public Date getHourAttention() {
		return hourAttention;
	}

	public Date getHourFinishedAttention() {
		return hourFinishedAttention;
	}

	public StateTurnEnum getState() {
		return state;
	}

	public String getIdentificationPerson() {
		return identificationPerson;
	}

	public String getCodeTurn() {
		return codeTurn;
	}

	public String getObservation() {
		return observation;
	}

	public Long getIdService() {
		return idService;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setPointService(PointService pointService) {
		this.pointService = pointService;
	}

	public void setModuleUser(ModuleUser moduleUser) {
		this.moduleUser = moduleUser;
	}

	public void setHourTurn(Date hourTurn) {
		this.hourTurn = hourTurn;
	}

	public void setHourAttention(Date hourAttention) {
		this.hourAttention = hourAttention;
	}

	public void setHourFinishedAttention(Date hourFinishedAttention) {
		this.hourFinishedAttention = hourFinishedAttention;
	}

	public void setState(StateTurnEnum state) {
		this.state = state;
	}

	public void setIdentificationPerson(String identificationPerson) {
		this.identificationPerson = identificationPerson;
	}

	public void setCodeTurn(String codeTurn) {
		this.codeTurn = codeTurn;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "DigitalTurn [id=" + id + ", hourTurn=" + hourTurn + ", hourAttention=" + hourAttention
				+ ", hourFinishedAttention=" + hourFinishedAttention + ", state=" + state + ", identificationPerson="
				+ identificationPerson + ", codeTurn=" + codeTurn + ", observation=" + observation + ", idService="
				+ idService + ", idUser=" + idUser + "]";
	}

	
}
