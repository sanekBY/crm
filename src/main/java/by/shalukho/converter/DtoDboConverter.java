package by.shalukho.converter;

public interface DtoDboConverter<T, B> {
    T convertToDto(final B dbo);
    B convertToDbo(final T dto);
}