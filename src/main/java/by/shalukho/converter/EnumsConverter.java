package by.shalukho.converter;

public interface EnumsConverter<T, B> {
    public <EnymType extends Enum<EnymType>> B convertEnumsToEntity(T dto, B entity);

    public <EnymType extends Enum<EnymType>> T convertEnumsToDto(B entity, T dto);
}
