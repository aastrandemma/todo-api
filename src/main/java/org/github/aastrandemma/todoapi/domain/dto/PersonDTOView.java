package org.github.aastrandemma.todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class PersonDTOView {
    private Long id;
    private String name;
}