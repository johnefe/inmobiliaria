package com.proinsalud.sistemas.core.general.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.proinsalud.sistemas.core.security.model.Users;

/**
 * @author Mauricio Pinchao
 * @datetime 22/12/2017 - 9:29:24 a. m.
 *
 */
@Entity
@Table(name = "news", schema = "general")
@NamedQueries({ @NamedQuery(name = "News.findAllOrderBy", query = "SELECT n FROM News n ORDER BY n.registro DESC")
})
public class News implements Serializable {

	private static final long serialVersionUID = 1378456716582517680L;

	@Id
	@Column(name = "id_new")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String titulo;
	
	@Column(name = "description")
	private String descripcion;
	
	@Column(name = "url")
	private String enlace;
	
	@Column(name = "registered", updatable = false, insertable = false)
	private Date registro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users usuario;
	
	@Column(name = "author")
	private String autor;
	
	public News() {
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	
	public Users getUsuario() {
		return usuario;
	}

	public String getAutor() {
		return autor;
	}

	public void setUsuario(Users usuario) {
		this.usuario = usuario;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", enlace=" + enlace + "]";
	}
}
