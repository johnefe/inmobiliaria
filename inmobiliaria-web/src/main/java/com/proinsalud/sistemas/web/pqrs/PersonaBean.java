package com.proinsalud.sistemas.web.pqrs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.jsf.FacesContextUtils;

import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IPersonaService;

//@Component si se activa esta linea el bean tambien sera administrado por spring y se corre el riesgo de que el bean se cree dos veces
@ManagedBean(name = "personaBeanTest")
@RequestScoped
public class PersonaBean implements Serializable {

	private static final long serialVersionUID = -6182170141630919935L;

	// @Autowired(required = true)Esta anotacion se la usa cuando el bean es
	// administrado por Sring para que haga la inyeccion del servicio
	// @Qualifier(value = "personaService")Esta anotacion sirve para solucionar
	// conflictos con nombres de los servicios trabaja en conjunto con @Autowired
	// @ManagedProperty(value="personaService")//esta anotacion se la utiliza cuando
	// @ManagedProperty(value="#{personaService}")//esta anotacion se la utiliza cuando
	// se hace inyeccion de otro bean administrado por JSF

	@Autowired
	@Qualifier(value = "personaService")
	private IPersonaService iPersonaService;

	public void setiPersonaService(IPersonaService iPersonaService) {
		this.iPersonaService = iPersonaService;
	}

	// Cuando se esta haciendo inyeccion de los beans desde el facex-config.xml se
	// deben habilitar los setters
	// public void setiPersonaService(IPersonaService iPersonaService) {
	// this.iPersonaService = iPersonaService;
	// }

	private List<Person> personas;
	private Person persona;

	public PersonaBean() {
	}

	@PostConstruct
	public void init() {
		// esta linea es para hacer la inyección de los servicios, debido a que el
		// administrador del bean es un managedBean y no un controller o componente de
		// spring
		FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);

		persona = new Person(new Long(99), "Carlos", "Andres", "Santacruz", "Zambrano", new Date(), "108530346","","","","","",null,null);
	}

	public List<Person> getPersonas() {
		personas = iPersonaService.findAllEntity();
		return personas;
	}

	public Person getPersona() {
		return persona;
	}

}
