package org.github.aastrandemma.todoapi.controller;

import org.github.aastrandemma.todoapi.domain.dto.UserDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.UserDTOView;
import org.github.aastrandemma.todoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<UserDTOView> doGetUserByEmail(@RequestParam String email) {
        System.out.println(">>>>>> getUserByEmail has been executed");
        System.out.println("Email: " + email);

        UserDTOView responseBody = userService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTOView> doRegisterUser(@RequestBody UserDTOForm dtoForm) {
        System.out.println("DTO Form: " + dtoForm);

        UserDTOView responseBody = userService.registerUser(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> doDisableUserByEmail(@RequestParam String email) {
        userService.inactiveUserByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> doEnableUserByEmail(@RequestParam String email) {
        userService.activeUserByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}