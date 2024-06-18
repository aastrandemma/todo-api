package org.github.aastrandemma.todoapi.converter;

import org.github.aastrandemma.todoapi.domain.dto.PersonDTOView;
import org.github.aastrandemma.todoapi.domain.dto.TaskDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.TaskDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Person;
import org.github.aastrandemma.todoapi.domain.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskConverterImpl implements Converter<Task, TaskDTOView> {
    @Override
    public TaskDTOView entityToDTO(Task entity) {
        PersonDTOView personDTOView = null;
        if (entity.getPerson() != null) {
            personDTOView = PersonDTOView.builder()
                    .id(entity.getPerson().getId())
                    .name(entity.getPerson().getName())
                    .build();
        }

        return TaskDTOView.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .done(entity.isDone())
                .person(personDTOView)
                .build();
    }

    @Override
    public Task DTOToEntity(TaskDTOView dtoView) {
        Person person = null;
        if (dtoView.getPerson() != null) {
            person = Person.builder()
                    .id(dtoView.getPerson().getId())
                    .name(dtoView.getPerson().getName())
                    .build();
        }

        return Task.builder()
                .id(dtoView.getId())
                .title(dtoView.getTitle())
                .description(dtoView.getDescription())
                .deadline(dtoView.getDeadline())
                .done(dtoView.isDone())
                .person(person)
                .build();
    }

    public Task DTOFormToEntity(TaskDTOForm dtoForm) {
        return Task.builder()
                .id(dtoForm.getId())
                .title(dtoForm.getTitle())
                .description(dtoForm.getDescription())
                .deadline(dtoForm.getDeadline())
                .done(dtoForm.isDone())
                .build();
    }
}