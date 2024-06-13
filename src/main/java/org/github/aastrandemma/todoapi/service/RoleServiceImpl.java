package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.converter.RoleConverterImpl;
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
    private final RoleConverterImpl roleConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverterImpl roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    public List<RoleDTOView> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTOView> roleDTOViewList = new ArrayList<>();
        for (Role entity : roles) {
            roleDTOViewList.add(roleConverter.entityToDTO(entity));
        }
        return roleDTOViewList;
    }
}