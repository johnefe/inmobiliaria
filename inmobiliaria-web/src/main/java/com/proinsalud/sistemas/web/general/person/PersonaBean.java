package com.proinsalud.sistemas.web.general.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.general.model.Department;
import com.proinsalud.sistemas.core.general.model.DocumentType;
import com.proinsalud.sistemas.core.general.model.Municipality;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IDepartmentService;
import com.proinsalud.sistemas.core.general.service.IDocumentTypeService;
import com.proinsalud.sistemas.core.general.service.IMunicipalityService;
import com.proinsalud.sistemas.core.general.service.IPersonaService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.LoadFileExcel;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;
import com.proinsalud.sistemas.web.util.reports.ReportGenerator;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Mauricio Pinchao
 * @datetime 19/12/2017 - 11:03:00 a. m.
 */
@ManagedBean(name = "personaBean")
@ViewScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = 4097159848181987234L;

	private static final Log LOG = App.getLogger(PersonaBean.class);

	@Autowired
	private IPersonaService iPersonaService;
	@Autowired
	private IDepartmentService iDepartmentService;
	@Autowired
	private IMunicipalityService iMunicipalityService;
	@Autowired
	private IDocumentTypeService iDocumentTypeService;

	private Person person = null;
	private Department department;

	private List<Person> persons;
	private List<Department> departmentList;
	private List<Municipality> municipalityList;
	private List<DocumentType> documentTypeList;

	private String rutaGenerada;

	private Long idMunicipio = null;

	public PersonaBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		persons = iPersonaService.findAllEntity();
		departmentList = iDepartmentService.findAllEntity();
		documentTypeList = iDocumentTypeService.findAllEntity();
	}

	public void nuevo() {
		person = new Person();
	}

	public void savePerson() {
		try {
			if (person != null) {
				if (person.getId() != null) {
					person = iPersonaService.mergeEntity(person);
				} else {
					person = iPersonaService.persistEntity(person);
				}
				persons = iPersonaService.findAllEntity();
			}
			person = null;
			department = null;
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void loadPerson(Person personIn) {
		try {
			this.person = personIn;
			if (this.person.getMunicipality() != null) {
				department = this.person.getMunicipality().getDepartment();
				setDepartment((Department) UtilWeb.loadObject(departmentList, department));
				municipalityList = department.getMunicipalities();
				// municipalityList =
				// iMunicipalityService.findByIdDepartment(department.getId());
				this.person.setMunicipality((Municipality) UtilWeb.loadObject(municipalityList, this.person.getMunicipality()));
			}
			this.person.setTipoDocumento((DocumentType) UtilWeb.loadObject(documentTypeList, this.person.getTipoDocumento()));
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void deletePerson(Person person) {
		try {
			iPersonaService.deleteEntity(person);
			persons = iPersonaService.findAllEntity();
			Messages.showMsg(Messages.MSG_INFO, "form1", "El registro se elimino correctamente");
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void loadMunicipalities() {
		municipalityList = new ArrayList<>();
		try {
			if (department != null) {
				municipalityList = iMunicipalityService.findByIdDepartment(department.getId());
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Este metodo se encarga degenerar los reportes.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:22:20 a. m.
	 */
	public void generarReporte() {
		try {
			ReportGenerator rG = new ReportGenerator();
			ArrayList<File> files = new ArrayList<>();
			rutaGenerada = rG.generarReportePersonas(persons.size());
			files.add(new File(rutaGenerada));
			rutaGenerada = rG.generarReportePersonas(persons.size());
			files.add(new File(rutaGenerada));
			rutaGenerada = rG.generarReportePersonas(persons.size());
			files.add(new File(rutaGenerada));

			rutaGenerada = rG.createZip(files, "Reportes Personas");

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	/**
	 * Este metodo se encarga de eliminar los archivos temporales generados, una vez
	 * terminada la descarga.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:21:32 a. m.
	 */
	public void onFinishDownload() {
		ReportGenerator.deleteFileTemps(rutaGenerada);
		rutaGenerada = null;
	}

	public StreamedContent getDownload() {
		try {
			File file = new File(rutaGenerada);
			InputStream input = new FileInputStream(file);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			return new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
		} catch (FileNotFoundException e) {
			UtilWeb.printError(LOG, e);
			return null;
		}
	}

	public void exportarExcel() throws JRException, IOException {
		try {
			ReportGenerator rG = new ReportGenerator();
			String[] titulos = Person.getTitles();
			String[][] datos = new String[persons.size()][0];
			for (int i = 0; i < persons.size(); i++) {
				datos[i] = persons.get(i).getData();
			}
			rutaGenerada = rG.generarReporteExcel(titulos, datos);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadDataExcel(FileUploadEvent event) {
		LoadFileExcel poiUtils = new LoadFileExcel();
		if (event != null && event.getFile() != null) {
			UploadedFile uploadFile = event.getFile();
			if (uploadFile != null && uploadFile.getFileName() != null && !uploadFile.getFileName().equals("")) {
				Long l = (long) 1;
				DocumentType doc = iDocumentTypeService.findEntityById(l);
				Long m = (long) 801;
				Municipality mun = iMunicipalityService.findEntityById(m);
				try {
					ArrayList<ArrayList<String[]>> arrayDatosExcel = poiUtils.readExcelFileToArray(uploadFile);
					List<Person> personsPersist = new ArrayList<>();
					for (ArrayList<String[]> hoja : arrayDatosExcel) {
						for (int i = 1; i < hoja.size(); i++) {
							String[] datos = hoja.get(i);
							datos = limpiarFila(datos);
							Person nPerson = new Person();
							nPerson.setFirstName(datos[0]);
							nPerson.setSecondName(datos[1]);
							nPerson.setLastName(datos[2]);
							nPerson.setSecondLastName(datos[3]);
							nPerson.setTipoDocumento(doc);
							nPerson.setIdentification(datos[5]);
							nPerson.setMunicipality(mun);
							nPerson.setAddress(datos[7]);
							nPerson.setEmail(datos[8]);
							nPerson.setMovil(datos[9]);
							personsPersist.add(nPerson);
						}
					}
					iPersonaService.persistEntity(personsPersist);
					persons = iPersonaService.findAllEntity();
					Messages.showMsg(Messages.MSG_INFO, "loadData", "El archivo se cargo correctamente.");
				} catch (Exception e) {
					UtilWeb.printError(LOG, e);
					Messages.showMsg(Messages.MSG_ERROR, "loadData", "El archivo no pudo ser cargado.");
				}
			}
		}
	}

	public String[] limpiarFila(String[] fila) {
		for (int i = 0; i < fila.length; i++) {
			if (fila[i] == null) {
				fila[i] = "";
			}
		}
		return fila;
	}

	public void cleanForm() {
		person = new Person();
	}

	public void cancel() {
		person = null;
	}

	public void edit(Person person) {
		this.person = person;
	}

	/**
	 * GETTERS AND SETTERS
	 * 
	 * @datetime 19/12/2017 - 10:37:34 a. m.
	 */

	public Person getPerson() {
		return person;
	}

	public Department getDepartment() {
		return department;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public List<Municipality> getMunicipalityList() {
		return municipalityList;
	}

	public List<DocumentType> getDocumentTypeList() {
		return documentTypeList;
	}

	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/**
	 * public void setDepartmentList(List<Department> departmentList) {
	 * this.departmentList = departmentList; }
	 **/

	public void setMunicipalityList(List<Municipality> municipalityList) {
		this.municipalityList = municipalityList;
	}

	/**
	 * public void setDocumentTypeList(List<DocumentType> documentTypeList) {
	 * this.documentTypeList = documentTypeList; }
	 **/

	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
}
