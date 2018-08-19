package by.shalukho.controller;

import by.shalukho.dto.PersonDto;
import by.shalukho.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/api/person", method = RequestMethod.POST)
    public String createPerson(@RequestBody final PersonDto personDto) {
        personService.save(personDto);
        return "PersonEntity created";
    }

    @RequestMapping(value = "/api/person/{id}", method = RequestMethod.DELETE)
    public String deletePerson(@PathVariable("id") final Long id) {
        personService.delete(id);
        return "PersonEntity deleted";
    }

    @RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET)
    public PersonDto getPerson(@PathVariable("id") final Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "/api/person", method = RequestMethod.GET)
    public List<PersonDto> getPersons() {
        return personService.findAll();
    }

}
