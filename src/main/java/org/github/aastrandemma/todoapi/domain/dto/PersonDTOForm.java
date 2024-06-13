package org.github.aastrandemma.todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class PersonDTOForm {
    private Long id;
    private String name;
}