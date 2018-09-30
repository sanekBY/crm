package by.shalukho.converter;

import javax.persistence.Enumerated;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic converter for classes with enums. Can convert enum to string and string value to enum
 */
public class GenericConverterWithEnums<T, B> extends GenericConverter<T, B> implements EnumsConverter<T, B> {

    public GenericConverterWithEnums(final Class<B> entityClazz) {
        super(entityClazz);
    }

    @Override
    public T convertToDto(final B entity) {
        T dto = super.convertToDto(entity);
        dto = convertEnumsToDto(entity, dto);
        return dto;
    }

    @Override public B convertToEntity(final T dto) {
        B entity = super.convertToEntity(dto);
        entity = convertEnumsToEntity(dto, entity);
        return entity;
    }

    @Override
    public <EnumType extends Enum<EnumType>> B convertEnumsToEntity(final T dto, final B entity) {

        final List<Field> enumFields = getEnumFields();

        enumFields.forEach(f -> {
            try {
                final Field field = dto.getClass().getDeclaredField(f.getName());
                field.setAccessible(true);

                final EnumType[] enumConstants = (EnumType[]) f.getType().getEnumConstants();

                f.set(entity,
                      getEnumValue((String) field.get(dto),
                                   enumConstants));
            } catch (Exception e) {
                throwException(dto, entity);
            }
        });

        return entity;
    }

    @Override
    public <EnumType extends Enum<EnumType>> T convertEnumsToDto(final B entity, final T dto) {

        final List<Field> enumFields = getEnumFields();

        enumFields.forEach(f -> {
            try {
                final Field field = dto.getClass().getDeclaredField(f.getName());
                field.setAccessible(true);
                final EnumType value = (EnumType) f.get(entity);
                field.set(dto, value.toString());
            } catch (Exception e) {
                throwException(entity, dto);
            }
        });

        return dto;
    }

    protected static <EnumType extends Enum<EnumType>> EnumType getEnumValue(final String stringValue,
                                                                             final EnumType[] en) {
        final EnumType actualEnumValue =
                Arrays.stream(en).filter(e -> e.toString().equals(stringValue)).findFirst().orElse(en[0]);
        return actualEnumValue;
    }

    private List<Field> getEnumFields() {
        return Arrays.stream(getEntityClazz().getDeclaredFields()).filter(e -> e.isAnnotationPresent(
                Enumerated.class)).peek(field -> field.setAccessible(true)).collect(Collectors.toList());
    }

    private void throwException(final Object firstObj, final Object secondObj) {
        throw new RuntimeException("Impossible to convert enum from " + firstObj.getClass().getName() + " to " +
                                   secondObj.getClass().getName());
    }

}
