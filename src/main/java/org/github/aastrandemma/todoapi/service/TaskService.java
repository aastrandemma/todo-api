package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.domain.dto.TaskDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.TaskDTOView;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    TaskDTOView createTask(TaskDTOForm dtoForm);
    TaskDTOView findByTaskId(Long taskId);
    void updateTask(TaskDTOForm dtoForm);
    void deleteTask(Long taskId);
    List<TaskDTOView> findTasksByPersonId(Long personId);
    List<TaskDTOView> findTasksBetweenStartAndEndDate(LocalDate startDate, LocalDate endDate);
    List<TaskDTOView> findAllUnassignedTasks();
    List<TaskDTOView> findAllUnfinishedTasksAndOverdue();
}