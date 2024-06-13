package org.github.aastrandemma.todoapi.service;

import org.github.aastrandemma.todoapi.domain.dto.RoleDTOView;
import org.github.aastrandemma.todoapi.domain.dto.UserDTOForm;
import org.github.aastrandemma.todoapi.domain.dto.UserDTOView;
import org.github.aastrandemma.todoapi.domain.entity.Role;
import org.github.aastrandemma.todoapi.domain.entity.User;
import org.github.aastrandemma.todoapi.exception.DataDuplicateException;
import org.github.aastrandemma.todoapi.exception.DataNotFoundException;
import org.github.aastrandemma.todoapi.repository.RoleRepository;
import org.github.aastrandemma.todoapi.repository.UserRepository;
import org.github.aastrandemma.todoapi.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTOView registerUser(UserDTOForm dtoForm) {
        //1. Check parameters
        if (dtoForm == null) throw new IllegalArgumentException("User form cannot be null");

        //2. Check if the email exists in the database
        boolean emailExists = userRepository.existsByEmail(dtoForm.getEmail());
        if (emailExists) throw new DataDuplicateException("Email already exists");

        //3. Validate roles in the repository
        Set<Role> roleList = dtoForm.getRoles()
                .stream()
                .map(
                        roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                                .orElseThrow(() -> new DataNotFoundException("Role is not valid")))
                                .collect(Collectors.toSet());

        //4. Convert UserDTOForm to Entity
        //5. Hash the password
        User user = User.builder().email(dtoForm.getEmail())
                .password(passwordEncoder.encode(dtoForm.getPassword()))
                .roles(roleList)
                .build();
        //6. Save the User to the database
        User savedUser = userRepository.save(user);

        //7. Convert the repository result to UserDTOView
        Set<RoleDTOView> roleDTOViews = savedUser.getRoles()
                .stream()
                .map(
                        role -> RoleDTOView.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                .collect(Collectors.toSet());

        return UserDTOView.builder()
                .email(savedUser.getEmail())
                .roles(roleDTOViews)
                .build();
    }

    @Override
    public UserDTOView getUserByEmail(String email) {
        User user = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exist"));
        Set<RoleDTOView> roleDTOViews = user.getRoles()
                .stream()
                .map(
                        role -> RoleDTOView.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                                .collect(Collectors.toSet());
        return UserDTOView.builder()
                .email(user.getEmail())
                .roles(roleDTOViews)
                .build();
    }

    @Override
    public void inactiveUserByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateActiveByEmail(email, false);
    }

    @Override
    public void activeUserByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateActiveByEmail(email, true);
    }

    private void isEmailTaken(String email) {
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist.");
    }
}