package org.github.aastrandemma.todoapi.converter;

import org.github.aastrandemma.todoapi.domain.dto.RoleDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Role;

public interface RoleConverter {
    RoleDTOView toRoleDTO(Role entity);
    Role toRoleEntity(RoleDTOView dtoView);
}