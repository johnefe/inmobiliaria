package com.proinsalud.sistemas.core.convocatory.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.proinsalud.sistemas.core.util.CustomPostgreSQLEnumType;
import com.proinsalud.sistemas.core.util.enums.StateConvocatoryEnum;

/**
 * Es la tabla convocatoria
 * 
 * @author Andres Santacruz
 * @datetime 18/12/2017 - 7:38:45 a. m.
 *
 */
@Entity
@Table(name = "convocatory", schema = "convocatory")
@TypeDef(name = "pgsql_enum_convocatory", typeClass = CustomPostgreSQLEnumType.class)
@NamedQueries({ @NamedQuery(name = "Convocatory.findAllEntityByState", query = "SELECT c FROM Convocatory c WHERE c.state=:state"), @NamedQuery(name = "Convocatory.findAllEntity", query = "SELECT c FROM Convocatory c ORDER BY c.registered DESC"), })
@DynamicUpdate
public class Convocatory implements Serializable {

	private static final long serialVersionUID = 534582895494131731L;

	@Id
	@Column(name = "id_convocatory")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "registered", insertable = false, updatable = false)
	private Date registered;

	@Column(name = "expiration")
	private Date expiration;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "convocatory")
	private List<Quotation> quotations;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "convocatory")
	private List<ConvocatoryProduct> convocatoryProducts;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", columnDefinition = "convocatory.state_convocatory")
	@Type(type = "pgsql_enum_convocatory")
	private StateConvocatoryEnum state;

	@Column(name = "opening")
	private Date opening;

	@Column(name = "open_convocatory")
	private boolean openConvocatory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_convocatory")
	private TypeConvocatory typeConvocatory;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "convocatory")
	private OrderBuy orderBuy;

	public Convocatory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ConvocatoryProduct> getConvocatoryProducts() {
		return convocatoryProducts;
	}

	public void setConvocatoryProducts(List<ConvocatoryProduct> convocatoryProducts) {
		this.convocatoryProducts = convocatoryProducts;
	}

	public StateConvocatoryEnum getState() {
		return state;
	}

	public void setState(StateConvocatoryEnum state) {
		this.state = state;
	}

	public Date getOpening() {
		return opening;
	}

	public void setOpening(Date opening) {
		this.opening = opening;
	}

	public boolean isOpenConvocatory() {
		return openConvocatory;
	}

	public void setOpenConvocatory(boolean openConvocatory) {
		this.openConvocatory = openConvocatory;
	}

	public TypeConvocatory getTypeConvocatory() {
		return typeConvocatory;
	}

	public void setTypeConvocatory(TypeConvocatory typeConvocatory) {
		this.typeConvocatory = typeConvocatory;
	}

	public OrderBuy getOrderBuy() {
		return orderBuy;
	}

	public void setOrderBuy(OrderBuy orderBuy) {
		this.orderBuy = orderBuy;
	}

	@Override
	public String toString() {
		return "Convocatory [id=" + id + ", name=" + name + ", description=" + description + ", registered=" + registered + ", expiration=" + expiration + ", state=" + state + ", opening=" + opening + ", openConvocatory=" + openConvocatory + "]";
	}

}
