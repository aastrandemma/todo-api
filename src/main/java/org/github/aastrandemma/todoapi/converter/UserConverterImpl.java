package org.github.aastrandemma.todoapi.converter;

import org.github.aastrandemma.todoapi.domain.dto.UserDTOView;
import org.github.aastrandemma.todoapi.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements Converter<User, UserDTOView> {
    @Override
    public UserDTOView entityToDTO(User entity) {
        return null;
    }

    @Override
    public User DTOToEntity(UserDTOView dtoView) {
        return null;
    }
}