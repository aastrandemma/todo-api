package org.github.aastrandemma.todoapi.converter;

import org.github.aastrandemma.todoapi.domain.dto.TaskDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.TaskDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskConverterImpl implements Converter<Task, TaskDTOView> {
    @Override
    public TaskDTOView entityToDTO(Task entity) {
        return TaskDTOView.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .done(entity.isDone())
                .build();
    }

    @Override
    public Task DTOToEntity(TaskDTOView dtoView) {
        return Task.builder()
                .id(dtoView.getId())
                .title(dtoView.getTitle())
                .description(dtoView.getDescription())
                .deadline(dtoView.getDeadline())
                .done(dtoView.isDone())
                .build();
    }

    public Task DTOFormToEntity(TaskDTOForm dtoForm) {
        Task.TaskBuilder taskBuilder = Task.builder()
                .title(dtoForm.getTitle())
                .description(dtoForm.getDescription())
                .deadline(dtoForm.getDeadline())
                .done(dtoForm.isDone());
        if (dtoForm.getId() != null && dtoForm.getId() >= 0) {
            taskBuilder.id(dtoForm.getId());
        }
        return taskBuilder.build();
    }
}