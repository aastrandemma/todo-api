package org.github.aastrandemma.todoapi.repository;

import org.github.aastrandemma.todoapi.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    //Find all persons with no task assigned
    @Query("SELECT p from Person p WHERE size(p.tasks) = 0")
    List<Person> findIdlePeople();
}