package org.github.aastrandemma.todoapi.converter;

public interface Converter<E, D> {
    D entityToDTO(E entity);
    E DTOToEntity(D dtoView);
}