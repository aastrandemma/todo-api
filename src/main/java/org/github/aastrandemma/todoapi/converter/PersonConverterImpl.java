package org.github.aastrandemma.todoapi.converter;

import org.github.aastrandemma.todoapi.domain.dto.PersonDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonConverterImpl implements Converter<Person, PersonDTOView> {
    @Override
    public PersonDTOView entityToDTO(Person entity) {
        return PersonDTOView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Person DTOToEntity(PersonDTOView dtoView) {
        return Person.builder()
                .id(dtoView.getId())
                .name(dtoView.getName())
                .build();
    }
}