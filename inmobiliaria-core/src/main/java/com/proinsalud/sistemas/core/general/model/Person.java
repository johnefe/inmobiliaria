package com.proinsalud.sistemas.core.general.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.human_talent.model.Employee;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.util.json.CustomJson;
import com.proinsalud.sistemas.core.util.json.DataMixFilter;
import com.proinsalud.sistemas.core.util.json.JsonViews;

/**
 * Clase que se encarga de administrar los datos de una persona.
 * 
 * @author Mauricio Pinchao
 * @datetime 18/12/2017 - 3:37:57 p. m.
 *
 */
@Entity
@Table(name = "person", schema = "general")
@NamedQueries({ @NamedQuery(name = "Person.findByNombre", query = "SELECT p FROM Person p WHERE p.firstName=:nombrePersona"),
		@NamedQuery(name = "Person.findAllEntityWithUsers", query = "SELECT p FROM Person p WHERE EXISTS (SELECT pr FROM Provider pr WHERE pr.person = p.id) or EXISTS (SELECT e FROM Employee e WHERE p.id = e.person)"),
		@NamedQuery(name = "Person.findEntityByIdentification", query = "SELECT p FROM Person p WHERE p.identification=:identification")})
@CustomJson
public class Person implements Serializable {

	private static final long serialVersionUID = -5638095781894812481L;

	@Id
	@Column(name = "id_person")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.OnlyAtributos.class)
	private Long id;

	@Column(name = "first_name", length = 100, nullable = false)
	@JsonView(JsonViews.OnlyAtributos.class)
	private String firstName;

	@Column(name = "second_name", length = 100)
	@JsonView(JsonViews.OnlyAtributos.class)
	private String secondName;

	@Column(name = "last_name", length = 100, nullable = false)
	@JsonView(JsonViews.OnlyAtributos.class)
	private String lastName;

	@Column(name = "second_last_name", length = 100)
	@JsonView(JsonViews.OnlyAtributos.class)
	private String secondLastName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "birthdate", nullable = true)
	@JsonView(JsonViews.OnlyAtributos.class)
	private Date birthdate;

	@Column(name = "identification", length = 20, nullable = false, unique = true, updatable = true)
	@JsonView(JsonViews.OnlyAtributos.class)
	private String identification;

	@JsonProperty("fecha_de_registro")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Column(name = "registered", updatable = false, insertable = false)
	@JsonView(JsonViews.OnlyAtributos.class)
	private Date registered;

	@Column(name = "address")
	@JsonView(JsonViews.OnlyAtributos.class)
	private String address;

	@Column(name = "telephone")
	@JsonView(JsonViews.OnlyAtributos.class)
	private String telefono;

	@Column(name = "cellphone")
	@JsonView(JsonViews.OnlyAtributos.class)
	private String movil;

	@Column(name = "email")
	@JsonView(JsonViews.OnlyAtributos.class)
	private String email;

	@Column(name = "sex")
	@JsonView(JsonViews.OnlyAtributos.class)
	private String sexo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipality")
	@JsonManagedReference
	@JsonIdentityReference(alwaysAsId = true)
	@JsonView(JsonViews.Complete.class)
	private Municipality municipality;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_document_type")
	@JsonUnwrapped
	@JsonManagedReference
	@JsonIgnoreProperties({ "id"})
	@JsonView(JsonViews.Complete.class)
	private DocumentType tipoDocumento;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	@JsonView(JsonViews.Complete.class)
	private List<Users> users;

	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
	@JsonView(JsonViews.Complete.class)
	private Provider provider;

	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
	@JsonView(JsonViews.Complete.class)
	private Employee employee;

	public static String getObjectMapper(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		//mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);Activa el nombre de la entidad @JsonRootName(value = "persona") VA EN LA CABECERA
//		@JsonUnwrapped para que los atributos de la clase se muestren en la actual VA EN EL ATTR
//		@JsonIdentityReference(alwaysAsId = true)SIRVE PARA MOSTRAR SOLO EL ID DE LA REFERENCIA DEL OTRO OBJETO VA EN EL ATTR
		//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })PARA INGNORAR LOS ATRIBUTOS VA EN LA CABECERA
		//mapper.setSerializationInclusion(Include.NON_NULL); PARA QUE NO MUESTRE LOS CAMPOS CON null
		
		//ObjectWriter objectWriter = mapper.writer().withoutAttribute("sexo");
		//String res = mapper.writerWithView(JsonViews.OnlyAtributos.class).withoutAttribute("sexo").writeValueAsString(object);
		
		
//		String [] exclude = new String[] {"employee" , "provider" , "tipoDocumento"};
//		SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter("filter",SimpleBeanPropertyFilter.filterOutAllExcept(exclude));
//		mapper.addMixIn(Persons.class, DataMixFilter.class);
//		mapper.setFilterProvider(filterProvider);
		//String res = mapper.writeValueAsString(object);
		//String res = mapper.writer(filterProvider).writeValueAsString(object);
		
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("filter", theFilter);
		mapper.addMixIn(Person.class, DataMixFilter.class);//Solo filtra en la clase persona
		//mapper.addMixIn(Object.class, DataMixFilter.class);//filtra en todos los objectos
		String res = mapper.writer(filters).writeValueAsString(object);
		return res;
//		return objectWriter.writeValueAsString(object);
	}
	
	static PropertyFilter theFilter = new SimpleBeanPropertyFilter() {
		   @Override
		   public void serializeAsField
		    (Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
		     throws Exception {
		      if (include(writer)) {
		    	  List<String> exclude = new ArrayList<>(Arrays.asList("id", "firstName" , "provider" , "tipoDocumento"));
		    	  if(exclude.contains(writer.getName()) == false) {
		         //if (!writer.getName().equals("firstName")) {
		            writer.serializeAsField(pojo, jgen, provider);
		            return;
		         }
		    	 if(writer.getName().equals("firstName")) {
		    		 String  value = ((Person) pojo).getFirstName();
			         if (value.equals("ROSA")) {
			            writer.serializeAsField(pojo, jgen, provider);
			         }
		    	 }
		      } else if (!jgen.canOmitFields()) { // since 2.3
		         writer.serializeAsOmittedField(pojo, jgen, provider);
		      }
		   }
		   @Override
		   protected boolean include(BeanPropertyWriter writer) {
		      return true;
		   }
		   @Override
		   protected boolean include(PropertyWriter writer) {
		      return true;
		   }
		};
	

	public Person() {
	}

	public Person(Long id, String firstName, String secondName, String lastName, String secondLastName, Date birthdate, String identification, String address, String telefono, String movil, String email, String sexo, Municipality municipality, DocumentType tipoDocumento) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.secondLastName = secondLastName;
		this.birthdate = birthdate;
		this.identification = identification;
		this.address = address;
		this.telefono = telefono;
		this.movil = movil;
		this.email = email;
		this.sexo = sexo;
		this.municipality = municipality;
		this.tipoDocumento = tipoDocumento;

	}

	@Column(name = "id_person")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getMovil() {
		return movil;
	}

	public String getEmail() {
		return email;
	}

	public String getSexo() {
		return sexo;
	}

	public DocumentType getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(DocumentType tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Provider getProvider() {
		return provider;
	}

	public Employee getEmployee() {
		return employee;
	}

	public static String[] getTitles() {
		return new String[] { "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Tipo de Id", "Numero Identificacion", "Municipio", "Direccion", "Email", "Celular" };
	}

	@JsonIgnore
	public String getNameCompleted() {
		return firstName + " " + (secondName != null ? secondName + " " : "") + lastName + " " + (secondLastName != null ? secondLastName + " " : "");
	}
	
	@JsonIgnore
	public String[] getData() {
		return new String[] { firstName, secondName, lastName, secondLastName, tipoDocumento != null ? tipoDocumento.getTipoDocumento() : "", identification, municipality != null ? municipality.getName() : "", address, email, movil };
	}

	@Override
	public String toString() {
		return "Persons [id=" + id + ", first_name=" + firstName + ", second_name=" + secondName + ", last_name=" + lastName + ", second_last_name=" + secondLastName + ", birthdate=" + birthdate + ", identification=" + identification + ", registered=" + registered + ", users="
				+ (Hibernate.isInitialized(users) ? users : "null") + "]";
	}

}
