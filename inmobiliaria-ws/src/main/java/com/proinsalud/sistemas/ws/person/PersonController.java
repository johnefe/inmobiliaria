package com.proinsalud.sistemas.ws.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proinsalud.sistemas.core.general.model.Person;
import com.proinsalud.sistemas.core.general.service.IPersonaService;

@RestController
@RequestMapping(value = "/person", produces = "application/json")
public class PersonController {

	@Autowired
	private IPersonaService iPersonaService;

	@RequestMapping(value = "/getperson")
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Person> getPerson() {
		Person p = iPersonaService.findAllEntity().get(0);
		p.setMunicipality(null);
		ResponseEntity<Person> res = new ResponseEntity<Person>(p, HttpStatus.OK);
		return res;
	}

	@RequestMapping(value = "/allpersons", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public List<Person> getAllPerson() {
		return iPersonaService.findAllEntity();
	}

	
}
