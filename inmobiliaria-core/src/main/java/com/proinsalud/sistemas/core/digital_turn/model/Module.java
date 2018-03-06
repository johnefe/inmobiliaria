package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;

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
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.proinsalud.sistemas.core.util.CustomPostgreSQLEnumType;
import com.proinsalud.sistemas.core.util.enums.TypeModuleEnum;

/**
 * @author Mauricio Pinchao
 * @datetime 6/02/2018 - 7:44:10 a. m.
 *
 */
@Entity
@Table(name = "module", schema = "digital_turn")
@TypeDef(name = "pgsql_enum_module", typeClass = CustomPostgreSQLEnumType.class)
@NamedQueries({ @NamedQuery(name = "Module.findPointByModule", query = "SELECT u FROM Module u WHERE u.id=:idModule"),
	@NamedQuery(name = "Module.findEntityByPoint", query = "SELECT m FROM Module m WHERE m.point.id =:idPoint ORDER BY m.nameModule ASC")
})
public class Module implements Serializable {

	private static final long serialVersionUID = -3446680410414102183L;

	@Id
	@Column(name = "id_module")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_module", length = 100)
	private String nameModule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_point")
	private Point point;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_module", columnDefinition = "digital_turn.type_module")
	@Type(type = "pgsql_enum_module")
	private TypeModuleEnum typeModule;
	
	public Module() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNameModule() {
		return nameModule;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNameModule(String nameModule) {
		this.nameModule = nameModule;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public TypeModuleEnum getTypeModule() {
		return typeModule;
	}

	public void setTypeModule(TypeModuleEnum typeModule) {
		this.typeModule = typeModule;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", nameModule=" + nameModule + ", typeModule=" + typeModule + "]";
	}

	
}
