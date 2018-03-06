package com.proinsalud.sistemas.core.inmobiliaria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 01/02/2018 - 10:06:54 a. m.
 */
@Entity
@Table(name = "cliente", schema = "inmobiliaria")
public class Cliente implements Serializable {

	
	private static final long serialVersionUID = -7909701874196846018L;

	@Id
	@Column(name="id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "name")
	private String nombre;
	
	
	@Column(name = "last_name")
	private String apellido;

	
	@Column(name = "email")
	private String email;
		
	@Column(name = "createAt")
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date createAt;
	
	@Column(name = "foto")
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", foto="
				+ foto + ", createAt=" + createAt + "]";
	}
	
}
