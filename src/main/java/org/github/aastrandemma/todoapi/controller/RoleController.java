package org.github.aastrandemma.todoapi.controller;

import org.github.aastrandemma.todoapi.domain.dto.RoleDTOView;
import org.github.aastrandemma.todoapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/roles")
@RestController
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTOView>> doGetAllRoles() {
        List<RoleDTOView> responseBody = roleService.getAll();
        return ResponseEntity.ok(responseBody);
    }
}