package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.domain.dto.PersonDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.PersonDTOView;

import java.util.List;

public interface PersonService {
    PersonDTOView registerPerson(PersonDTOForm dtoForm);
    PersonDTOView findByPersonId(Long personId);
    List<PersonDTOView> findAllPeople();
    PersonDTOView updatePerson(PersonDTOForm dtoForm);
    void deletePerson(Long personId);
}