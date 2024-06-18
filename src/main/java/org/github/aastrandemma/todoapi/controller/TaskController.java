package org.github.aastrandemma.todoapi.controller;

import org.github.aastrandemma.todoapi.domain.dto.TaskDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.TaskDTOView;
import org.github.aastrandemma.todoapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public ResponseEntity<TaskDTOView> doCreateTask(@RequestBody TaskDTOForm dtoForm) {
        TaskDTOView responseBody = taskService.createTask(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTOView> doGetTaskById(@PathVariable Long taskId) {
        TaskDTOView responseBody = taskService.findByTaskId(taskId);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> doUpdateTask(@PathVariable Long taskId, @RequestBody TaskDTOForm dtoForm) {
        dtoForm.setId(taskId);
        taskService.updateTask(dtoForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> doDeleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("people/{personId}/todo-items/")
    public ResponseEntity<List<TaskDTOView>> doFindTasksByPersonId(@PathVariable Long personId) {
        List<TaskDTOView> responseBody = taskService.findTasksByPersonId(personId);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<TaskDTOView>> doFindTasksBetweenStartAndEndDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<TaskDTOView> responseBody = taskService.findTasksBetweenStartAndEndDate(startDate, endDate);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<TaskDTOView>> doFindAllUnassignedTasks() {
        List<TaskDTOView> responseBody = taskService.findAllUnassignedTasks();
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/unfinished-overdue")
    public ResponseEntity<List<TaskDTOView>> doFindAllUnfinishedAndOverdue() {
        List<TaskDTOView> responseBody = taskService.findAllUnfinishedTasksAndOverdue();
        return ResponseEntity.ok(responseBody);
    }
}