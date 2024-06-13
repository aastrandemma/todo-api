package org.github.aastrandemma.todoapi.service;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.github.aastrandemma.todoapi.converter.PersonConverterImpl;
import org.github.aastrandemma.todoapi.domain.dto.PersonDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.PersonDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Person;
import org.github.aastrandemma.todoapi.exception.DataNotFoundException;
import org.github.aastrandemma.todoapi.repository.PersonRepository;
import org.github.aastrandemma.todoapi.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonConverterImpl personConverter;
    private final Validator validator;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonConverterImpl personConverter, Validator validator) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.validator = validator;
    }

    @Override
    public PersonDTOView registerPerson(@Valid PersonDTOForm dtoForm) {
        ValidationUtil.validateObject(dtoForm, validator, "Person form is not valid");
        Person savedPerson = personRepository.save(new Person(dtoForm.getName()));
        return personConverter.entityToDTO(savedPerson);
    }

    @Override
    public PersonDTOView findByPersonId(Long personId) {
        Person findPerson = personRepository.findById(personId)
                .orElseThrow(() -> new DataNotFoundException("Person not found"));
        return personConverter.entityToDTO(findPerson);
    }

    @Override
    public List<PersonDTOView> findAllPeople() {
        return personRepository.findAll().stream()
                .map(personConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updatePerson(@Valid PersonDTOForm dtoForm) {
        ValidationUtil.validateObject(dtoForm, validator, "Person form is not valid");
        Person updatePerson = personRepository.findById(dtoForm.getId())
                .orElseThrow(() -> new DataNotFoundException("Person not found"));
        updatePerson.setName(dtoForm.getName());
        personRepository.save(updatePerson);
    }

    @Override
    public void deletePerson(Long personId) {
        if (!personRepository.existsById(personId))
            throw new DataNotFoundException("Person not found");
        personRepository.deleteById(personId);
    }
}