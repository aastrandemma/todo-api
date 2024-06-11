package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.domain.dto.UserDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.UserDTOView;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    //Todo: add required dependencies

    @Override
    public UserDTOView registerUser(UserDTOForm dtoForm) {
        //Todo: Implement method
        return null;
    }

    @Override
    public UserDTOView getUserByEmail(String email) {
        //Todo: Implement method
        return null;
    }

    @Override
    public void inactiveUserByEmail(String email) {
        //Todo: Implement method
    }

    @Override
    public void activeUserByEmail(String email) {
        //Todo: Implement method
    }
}