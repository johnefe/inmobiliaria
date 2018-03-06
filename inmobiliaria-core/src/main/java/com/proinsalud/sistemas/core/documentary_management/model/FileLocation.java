package com.proinsalud.sistemas.core.documentary_management.model;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 31/01/2018 - 9:37:11 a. m.
 */

@Entity
@Table(name = "file_location", schema = "documentary_management")
@NamedQueries({ @NamedQuery(name = "FileLocation.findFileLocationRoot", query = "SELECT u FROM FileLocation u WHERE u.fileLocactionFather IS NULL ORDER BY u.nameFileLocation ASC") })
public class FileLocation implements Serializable {

	private static final long serialVersionUID = 333609192752991685L;

	@Id
	@Column(name = "id_file_location")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name_file_location")
	private String nameFileLocation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_file_location_father")
	private FileLocation fileLocactionFather;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fileLocactionFather")
	private List<FileLocation> fileLocations;

	public FileLocation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameFileLocation() {
		return nameFileLocation;
	}

	public void setNameFileLocation(String nameFileLocation) {
		this.nameFileLocation = nameFileLocation;
	}

	public FileLocation getFileLocactionFather() {
		return fileLocactionFather;
	}

	public void setFileLocactionFather(FileLocation fileLocactionFather) {
		this.fileLocactionFather = fileLocactionFather;
	}

	public List<FileLocation> getFileLocations() {
		return fileLocations;
	}

	@Override
	public String toString() {
		return "FileLocation [Id=" + id + ", nameFileLocation=" + nameFileLocation + "]";
	}


}
