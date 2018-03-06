package com.proinsalud.sistemas.web.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IPersonaService;


/**
 * @author Mauricio Pinchao
 * @datetime 29/01/2018 - 5:26:02 p. m.
 *
 */
@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private IPersonaService iPersonaService;
	
	@RequestMapping("getName")
	public String getName() {
		return "Hola";
	}
	
	@RequestMapping(value="persons", method = RequestMethod.GET)
	public List<Person> getAllPersons(){
		return iPersonaService.findAllEntity();
	}
	
	@GetMapping(value="personas")
	public @ResponseBody List<Person> getPersons(){
		return this.iPersonaService.findAllEntity();
	}
	
	@RequestMapping(value="/listado_personas")
	public ModelAndView listado(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("personas", this.iPersonaService.findAllEntity());
		return new ModelAndView("listaPersonas", "model", model);
	}
	
	@RequestMapping(value = "/{idPerson}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPerson(@PathVariable long idPerson){
		Person person = iPersonaService.findEntityById(idPerson);
		ResponseEntity<Person> response;
		if(person == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(person, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertPerson(@RequestBody Person person) {
		iPersonaService.persistEntity(person);
	}
	
	@RequestMapping(value = "/{idPerson}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePerson(@PathVariable long idPerson, @RequestBody Person person) {
		Person personOld = iPersonaService.findEntityById(idPerson);
		if(personOld != null) {
			iPersonaService.mergeEntity(person);
		}
	}
	
	@RequestMapping(value = "/{idPerson}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable long idPerson) {
		Person personDelete = iPersonaService.findEntityById(idPerson);
		iPersonaService.deleteEntity(personDelete);
	}
}
