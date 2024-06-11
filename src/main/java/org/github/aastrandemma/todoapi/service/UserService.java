package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.domain.dto.UserDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.UserDTOView;

public interface UserService {
    UserDTOView registerUser(UserDTOForm dtoForm);
    UserDTOView getUserByEmail(String email);
    void inactiveUserByEmail(String email);
    void activeUserByEmail(String email);
}