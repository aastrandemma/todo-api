package org.github.aastrandemma.todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class RoleDTOForm {
    private long id;
    private String name;
}