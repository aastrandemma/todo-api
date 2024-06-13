package org.github.aastrandemma.todoapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"tasks", "user"})
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "email")
    private User user;
    @OneToMany(mappedBy = "person")
    private List<Task> tasks;

    public Person(String name) {
        this.name = name;
    }

    public void addTask(Task... tasks) {
        if(Objects.requireNonNull(tasks).length==0)
            throw new IllegalArgumentException("Tasks is empty");

        if(this.tasks == null) {
            this.tasks = new ArrayList<>();
        }

        for (Task task : tasks) {
            this.tasks.add(task);
            if(task.getPerson() != null) {
                task.setPerson(this);
            }
        }
    }

    public void removeTask(Task...  tasks) {
        if(Objects.requireNonNull(tasks).length==0)
            throw new IllegalArgumentException("Tasks is empty");

        for (Task task : tasks) {
            this.tasks.add(task);
            if(this.tasks.remove(task) && task.getPerson() == this) {
                task.setPerson(null);
            }
        }
    }
}