package org.github.aastrandemma.todoapi.converter;

import org.github.aastrandemma.todoapi.domain.dto.RoleDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverterImpl implements RoleConverter {
    @Override
    public RoleDTOView toRoleDTO(Role entity) {
        return new RoleDTOView(entity.getId(), entity.getName());
    }

    @Override
    public Role toRoleEntity(RoleDTOView dtoView) {
        return new Role(dtoView.getId(), dtoView.getName());
    }
}