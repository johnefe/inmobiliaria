package com.proinsalud.sistemas.core.util;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Andres Santacruz
 * @datetime 31/01/2018 - 7:50:55 a. m.
 *
 */
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonPropertyOrder({ "id", "registered", })
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = -8806222402910561897L;
}