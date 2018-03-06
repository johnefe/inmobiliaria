package com.proinsalud.sistemas.web.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.proinsalud.sistemas.core.convocatory.model.Provider;
import com.proinsalud.sistemas.core.convocatory.service.IProviderService;
import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IPersonaService;
import com.proinsalud.sistemas.core.util.json.JsonViews;

//@Controller
@RestController
@RequestMapping("/ws")
public class PersonaController {

	@Autowired
	private IPersonaService iPersonaService;

	@Autowired
	private IProviderService iProviderService;

	@RequestMapping(value="getpersons2" ,method = RequestMethod.POST)
	public String getPersonsObjetMapper(@RequestBody String parametroPost) {
		List<Person> lst = iPersonaService.findAllEntity();
		try {
			return Person.getObjectMapper(lst);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "[]";
		}
	}
	
	
	@RequestMapping("getperson2")
	public String getPersonObjetMapper() {
		Person lst = iPersonaService.findEntityById(new Long("1"));
		try {
			return Person.getObjectMapper(lst);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "[]";
		}
	}

	@RequestMapping("getperson")
	@JsonView(JsonViews.OnlyAtributos.class)
	public ResponseEntity<Person> getPersonResponseEntity() {
		Person p = iPersonaService.findAllEntity().get(0);
		ResponseEntity<Person> res = new ResponseEntity<Person>(p, HttpStatus.OK);
		return res;
	}

	@RequestMapping("getpersonsR")
	@JsonView(JsonViews.OnlyAtributos.class)
	public ResponseEntity<List<Person>> getPersonsResponseEntity() {
		List<Person> lst = iPersonaService.findAllEntity();
		ResponseEntity<List<Person>> res = new ResponseEntity<List<Person>>(lst, HttpStatus.OK);
		return res;
	}

	@RequestMapping("providers")
	public List<Provider> getProviders() {
		List<Provider> lst = iProviderService.findAllEntity();
		return lst;
	}

	@RequestMapping("getpersons")
	public List<Person> getPersons() {
		List<Person> lst = iPersonaService.findAllEntity();
		return lst;
	}

	@RequestMapping("getper")
	public Person getP() {
		return iPersonaService.findAllEntity().get(0);
	}

	@RequestMapping("/users/{name}")
	public @ResponseBody String getUsers(@PathVariable(name = "name", required = true) String nameGet) {
		return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," + "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}" + nameGet;
	}

	@RequestMapping("personabody")
	public @ResponseBody Person getPersonaBody() {
		return iPersonaService.findEntityById(new Long("1"));
	}

	@RequestMapping("personasbody")
	public @ResponseBody List<Person> getPersonasBody() {
		return iPersonaService.findAllEntity();
	}

	@RequestMapping("studentlist")
	public @ResponseBody List<Student> getStudentList() {
		List<Student> lst = new ArrayList<>();
		lst.add(new Student("nombre", 25, new Student("Jairo", 12, null)));
		lst.add(new Student("nombre2", 25, new Student("Jairo", 12, null)));
		lst.add(new Student("nombre3", 25, new Student("Jairo", 12, null)));
		lst.add(new Student("nombre4", 25, new Student("Jairo", 12, null)));
		return lst;
	}

	@RequestMapping(value = "500Error", method = RequestMethod.GET)
	public void throwRuntimeException() {
		// para prueba de error de pagina
		throw new NullPointerException("Error de null pointer exception");
	}

	@RequestMapping(value = "/testCore", method = RequestMethod.GET)
	public @ResponseBody String sayHelloTestCore() {
		// String sayHello = new ProinCoreTest().sayHello() + new TestCore().sayHello();
		String sayHello = "Hello";
		return sayHello;
	}

	public class Student {
		private String name;
		private int edad;
		private Student s1;

		public Student(String name, int edad, Student s1) {
			super();
			this.name = name;
			this.edad = edad;
			this.s1 = s1;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) {
			this.edad = edad;
		}

		public Student getS1() {
			return s1;
		}

		public void setS1(Student s1) {
			this.s1 = s1;
		}

	}
}
