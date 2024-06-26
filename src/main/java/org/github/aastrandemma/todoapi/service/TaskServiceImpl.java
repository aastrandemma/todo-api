package org.github.aastrandemma.todoapi.service;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.github.aastrandemma.todoapi.converter.TaskConverterImpl;
import org.github.aastrandemma.todoapi.domain.dto.PersonDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.TaskDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.TaskDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Person;
import org.github.aastrandemma.todoapi.domain.entity.Task;
import org.github.aastrandemma.todoapi.exception.DataNotFoundException;
import org.github.aastrandemma.todoapi.repository.PersonRepository;
import org.github.aastrandemma.todoapi.repository.TaskRepository;
import org.github.aastrandemma.todoapi.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;
    private final TaskConverterImpl taskConverter;
    private final Validator validator;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskConverterImpl taskConverter, Validator validator,
                           PersonRepository personRepository) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
        this.validator = validator;
        this.personRepository = personRepository;
    }

    @Override
    public TaskDTOView createTask(@Valid TaskDTOForm dtoForm) {
        ValidationUtil.validateObject(dtoForm, validator, "Task form is not valid");

        Task taskEntity = taskConverter.DTOFormToEntity(dtoForm);
        Task createdTask = taskRepository.save(taskEntity);

        Optional<Person> person = personRepository.findById(createdTask.getPerson().getId());
        person.ifPresent(createdTask::setPerson);

        return taskConverter.entityToDTO(createdTask);
    }

    @Override
    public TaskDTOView findByTaskId(Long taskId) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new DataNotFoundException("Task not found"));
        return taskConverter.entityToDTO(foundTask);
    }

    @Override
    public void updateTask(@Valid TaskDTOForm dtoForm) {
        ValidationUtil.validateObject(dtoForm, validator, "Task form is not valid");
        Task updateTask = taskRepository.findById(dtoForm.getId())
                .orElseThrow(() -> new DataNotFoundException("Task not found"));
        updateTaskFromDTOForm(updateTask, dtoForm);
        taskRepository.save(updateTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId))
            throw new DataNotFoundException("Task not found");
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDTOView> findTasksByPersonId(Long personId) {
        return taskRepository.findByPerson_Id(personId).stream()
                .map(taskConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTOView> findTasksBetweenStartAndEndDate(LocalDate startDate, LocalDate endDate) {
        return taskRepository.findByDeadlineBetween(startDate, endDate).stream()
                .map(taskConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTOView> findAllUnassignedTasks() {
        return taskRepository.findByDoneIsFalse().stream()
                .map(taskConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTOView> findAllUnfinishedTasksAndOverdue() {
        return taskRepository.findByDoneIsFalseAndDeadlineIsBefore(LocalDate.now()).stream()
                .map(taskConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTOView addTaskToPerson(Long personId, TaskDTOForm dtoForm) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new DataNotFoundException("Person not found with id: " + personId));

        dtoForm.setPerson(
                PersonDTOForm.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .build());
        return createTask(dtoForm);
    }

    private void updateTaskFromDTOForm(Task task, TaskDTOForm dtoForm) {
        task.setTitle(dtoForm.getTitle());
        task.setDescription(dtoForm.getDescription());
        task.setDeadline(dtoForm.getDeadline());
        task.setDone(dtoForm.isDone());
    }
}