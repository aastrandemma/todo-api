package org.github.aastrandemma.todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class RoleDTOView {
    private long id;
    private String name;
}