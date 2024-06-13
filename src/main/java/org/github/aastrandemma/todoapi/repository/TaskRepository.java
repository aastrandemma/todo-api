package org.github.aastrandemma.todoapi.repository;

import org.github.aastrandemma.todoapi.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContainsIgnoreCase(String titleContaining);
    List<Task> findByPerson_Id(Long personId);
    List<Task> findByDone(boolean status);
    List<Task> findByDeadlineBetween(LocalDate start, LocalDate end);
    List<Task> findByPersonIsNull();
    List<Task> findByDoneIsFalse();
    List<Task> findByDoneIsFalseAndDeadlineIsBefore(LocalDate currentDate);
}