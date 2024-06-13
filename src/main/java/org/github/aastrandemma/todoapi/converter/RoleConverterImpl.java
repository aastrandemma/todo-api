package org.github.aastrandemma.todoapi.converter;

import org.github.aastrandemma.todoapi.domain.dto.RoleDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverterImpl implements Converter<Role, RoleDTOView> {
    @Override
    public RoleDTOView entityToDTO(Role entity) {
        return RoleDTOView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Role DTOToEntity(RoleDTOView dtoView) {
        return Role.builder()
                .id(dtoView.getId())
                .name(dtoView.getName())
                .build();
    }
}