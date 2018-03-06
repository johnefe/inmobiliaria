package com.proinsalud.sistemas.core.general.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Clase que se encarga de los tipos de identificación.
 * 
 * @author Mauricio Pinchao
 * @datetime 18/12/2017 - 3:37:43 p. m.
 *
 */
@Entity
@Table(name = "document_type", schema = "general")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DocumentType implements Serializable {

	private static final long serialVersionUID = -4377494737796714602L;

	@Id
	@Column(name = "id_document_type")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "document_type", length = 100)
	private String tipoDocumento;

	public DocumentType() {
	}

	public Long getId() {
		return id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public String toString() {
		return "DocumentType [id= " + id + ", tipoDocumento= " + tipoDocumento + "]";
	}
}
