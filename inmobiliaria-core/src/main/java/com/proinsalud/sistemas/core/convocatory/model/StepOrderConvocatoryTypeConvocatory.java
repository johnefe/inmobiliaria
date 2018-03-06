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

/**
 * Es la tabla intermedia entre step_order_convocatory y type_convocatory
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 11:18:10 a. m.
 *
 */
@Entity
@Table(name = "step_order_convocatory_type_convocatory", schema = "convocatory")
@NamedQueries({ @NamedQuery(name = "StepOrderConvocatoryTypeConvocatory.findEntityByIdTypeConvocatory", query = "SELECT c FROM StepOrderConvocatoryTypeConvocatory c WHERE c.typeConvocatory.id=:idTypeConvocatory") })
public class StepOrderConvocatoryTypeConvocatory implements Serializable {

	private static final long serialVersionUID = -9072138561869550177L;

	@Id
	@Column(name = "id_step_order_convocatory_type_convocatory")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_step_order_convocatory")
	private StepOrderConvocatory stepOrderConvocatory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_convocatory")
	private TypeConvocatory typeConvocatory;

	@Column(name = "position", length = 3, nullable = false)
	private Integer position;

	@Column(name = "is_first")
	private Boolean isFirst;

	@Column(name = "is_last")
	private Boolean isLast;

	public StepOrderConvocatoryTypeConvocatory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StepOrderConvocatory getStepOrderConvocatory() {
		return stepOrderConvocatory;
	}

	public void setStepOrderConvocatory(StepOrderConvocatory stepOrderConvocatory) {
		this.stepOrderConvocatory = stepOrderConvocatory;
	}

	public TypeConvocatory getTypeConvocatory() {
		return typeConvocatory;
	}

	public void setTypeConvocatory(TypeConvocatory typeConvocatory) {
		this.typeConvocatory = typeConvocatory;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

	@Override
	public String toString() {
		return "StepOrderConvocatoryTypeConvocatory [id=" + id + ", position=" + position + ", isFirst=" + isFirst + ", isLast=" + isLast + "]";
	}

}
