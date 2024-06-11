package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.domain.dto.RoleDTOView;

import java.util.List;

public interface RoleService {
    List<RoleDTOView> getAll();
}