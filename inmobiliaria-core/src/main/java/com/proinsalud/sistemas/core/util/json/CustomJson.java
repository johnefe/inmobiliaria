package com.proinsalud.sistemas.core.util.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Andres Santacruz
 * @datetime 31/01/2018 - 4:01:08 p. m.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
//@JsonInclude(Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonPropertyOrder({ "id" })
public @interface CustomJson {}