package by.shalukho.converter;

import java.util.Collection;

public interface DtoDboConverter<T, B> {
    T convertToDto(final B dbo);

    B convertToEntity(final T dto);

    Collection<T> convertAllToDto(final Collection<B> entites);

    Collection<B> convertAllToEntity(final Collection<T> dbos);
}