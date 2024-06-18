package org.github.aastrandemma.todoapi.controller;

import org.github.aastrandemma.todoapi.domain.dto.PersonDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.PersonDTOView;
import org.github.aastrandemma.todoapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public ResponseEntity<PersonDTOView> doRegisterPerson(@RequestBody PersonDTOForm dtoForm) {
        PersonDTOView responseBody = personService.registerPerson(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDTOView> doGetPersonById(@PathVariable Long personId) {
        PersonDTOView responseBody = personService.findByPersonId(personId);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonDTOView> doUpdatePerson(@PathVariable Long personId, @RequestBody PersonDTOForm dtoForm) {
        dtoForm.setId(personId);
        PersonDTOView responseBody = personService.updatePerson(dtoForm);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> doDeletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<PersonDTOView>> doFindAllPeople() {
        List<PersonDTOView> peoples = personService.findAllPeople();
        return ResponseEntity.ok(peoples);
    }
}