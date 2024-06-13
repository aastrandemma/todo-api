package org.github.aastrandemma.todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class UserDTOForm {
    private String email;
    private String password;
    private Set<RoleDTOForm> roles;
}