package org.github.aastrandemma.todoapi.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class TaskDTOView {
    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
}