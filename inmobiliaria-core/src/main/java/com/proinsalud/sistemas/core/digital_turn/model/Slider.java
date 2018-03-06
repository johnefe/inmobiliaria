package com.proinsalud.sistemas.core.digital_turn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 23/02/2018 - 3:54:16 p. m.
 */

@Entity
@Table(name = "slider", schema = "digital_turn")
public class Slider implements Serializable {

	private static final long serialVersionUID = -7709778820790973591L;

	@Id
	@Column(name = "id_slider")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "url_image")
	private String urlImage;

	@Column(name = "title_slider")
	private String titleSlider;

	@Column(name = "content_slider")
	private String contentSlider;

	@Column(name = "state")
	private boolean state;

	public Slider() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getTitleSlider() {
		return titleSlider;
	}

	public void setTitleSlider(String titleSlider) {
		this.titleSlider = titleSlider;
	}

	public String getContentSlider() {
		return contentSlider;
	}

	public void setContentSlider(String contentSlider) {
		this.contentSlider = contentSlider;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Slider [id=" + id + ", urlImage=" + urlImage + ", titleSlider=" + titleSlider + ", contentSlider="
				+ contentSlider + ", state=" + state + "]";
	}
}
