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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.proinsalud.sistemas.core.util.CustomPostgreSQLEnumType;
import com.proinsalud.sistemas.core.util.enums.StateTurnEnum;

/**
 * @author Mauricio Pinchao
 * @datetime 14/02/2018 - 8:30:05 a. m.
 *
 */
@Entity
@Table(name = "digital_turn_temp", schema = "digital_turn")
@NamedQueries({ @NamedQuery(name = "DigitalTurnTemp.findAllEntityByOrder", query = "SELECT d FROM DigitalTurnTemp d ORDER BY d.prefixOrder ASC, d.hourRegistered ASC"),
		@NamedQuery(name = "DigitalTurnTemp.findAllEntityByPoint", query = "SELECT u FROM DigitalTurnTemp u JOIN FETCH u.digitalTurn d JOIN FETCH d.pointService p WHERE p.point.id =:idPoint AND u.state !=:state ORDER BY u.prefixOrder ASC, u.hourRegistered ASC"), })
@NamedNativeQueries(value = { @NamedNativeQuery(name = "DigitalTurnTemp.findTurnByStateAttention", query = "SELECT p.* FROM digital_turn.digital_turn_temp p " + "INNER JOIN digital_turn.digital_turn dt ON p.id_digital_turn = dt.id_digital_turn "
		+ "INNER JOIN digital_turn.point_service ps ON dt.id_point_service = ps.id_point_service " + "WHERE ps.id_point=:idPoint AND p.state='Atendido' ORDER BY p.hour_finished_attention DESC LIMIT 6; ", resultClass = DigitalTurnTemp.class) })

@TypeDef(name = "pgsql_enum_digital_turn_temp", typeClass = CustomPostgreSQLEnumType.class)
public class DigitalTurnTemp implements Serializable {

	private static final long serialVersionUID = 1069568034815287250L;

	@Id
	@Column(name = "id_digital_turn_temp")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "identification")
	private String identification;

	@Column(name = "name_person")
	private String namePerson;

	@Column(name = "service")
	private String service;

	@Column(name = "hour_registered")
	private Date hourRegistered;

	@Column(name = "hour_attention")
	private Date hourAttention;

	@Column(name = "hour_finished_attention")
	private Date hourFinishedAttention;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", columnDefinition = "digital_turn.state_turn")
	@Type(type = "pgsql_enum_digital_turn_temp")
	private StateTurnEnum state;

	@Column(name = "observation")
	private String observation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_digital_turn")
	private DigitalTurn digitalTurn;

	@Column(name = "color")
	private String color;

	@Column(name = "prefix_order")
	private Integer prefixOrder;

	public DigitalTurnTemp() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getIdentification() {
		return identification;
	}

	public String getNamePerson() {
		return namePerson;
	}

	public String getService() {
		return service;
	}

	public Date getHourRegistered() {
		return hourRegistered;
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

	public String getObservation() {
		return observation;
	}

	public DigitalTurn getDigitalTurn() {
		return digitalTurn;
	}

	public String getColor() {
		return color;
	}

	public Integer getPrefixOrder() {
		return prefixOrder;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public void setNamePerson(String namePerson) {
		this.namePerson = namePerson;
	}

	public void setService(String service) {
		this.service = service;
	}

	public void setHourRegistered(Date hourRegistered) {
		this.hourRegistered = hourRegistered;
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

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setDigitalTurn(DigitalTurn digitalTurn) {
		this.digitalTurn = digitalTurn;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrefixOrder(Integer prefixOrder) {
		this.prefixOrder = prefixOrder;
	}

	@Override
	public String toString() {
		return "DigitalTurnTemp [id=" + id + ", code=" + code + ", identification=" + identification + ", namePerson=" + namePerson + ", hourRegistered=" + hourRegistered + ", hourAttention=" + hourAttention + ", hourFinishedAttention=" + hourFinishedAttention + ", state=" + state + ", observation="
				+ observation + ", color=" + color + ", prefixOrder=" + prefixOrder + "]";
	}

}
