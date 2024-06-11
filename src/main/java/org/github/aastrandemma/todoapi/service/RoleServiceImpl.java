package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.converter.RoleConverter;
import org.github.aastrandemma.todoapi.domain.dto.RoleDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Role;
import org.github.aastrandemma.todoapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    public List<RoleDTOView> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTOView> roleDTOViewList = new ArrayList<>();
        for (Role entity : roles) {
            roleDTOViewList.add(roleConverter.toRoleDTO(entity));
        }
        return roleDTOViewList;
    }
}