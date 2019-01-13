package by.shalukho.specification;

import by.shalukho.criteria.SearchCriteria;
import by.shalukho.entity.AbstractEntity;
import by.shalukho.function.SerializableFunction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class SpecificationFilter {

    public static <T extends AbstractEntity> Specification<T> equal(@NonNull final SerializableFunction<T, ?> filedGetter,
                                                                    @NonNull final Object name) {
        return getSimpleSpecification(name, filedGetter, EntitySpecification.EQUAL_SIGN);
    }

    public static <T extends AbstractEntity> Specification<T> equalIgnoreCase(@NonNull final SerializableFunction<T, ?> filedGetter,
                                                                              @NonNull final String name) {
        return getSimpleSpecification(name, filedGetter, EntitySpecification.EQUAL_SIGN_IGNORE_CASE_SIGN);
    }

    public static <T extends AbstractEntity> Specification<T> like(@NonNull final SerializableFunction<T, ?> filedGetter,
                                                                   @NonNull final String name) {
        return getSimpleSpecification(name, filedGetter, EntitySpecification.LIKE_SIGN);
    }

    public static <T extends AbstractEntity> Specification<T> less(@NonNull final SerializableFunction<T, ?> filedGetter,
                                                                   @NonNull final String name) {
        return getSimpleSpecification(name, filedGetter, EntitySpecification.LESS_SIGN);
    }

    public static <T extends AbstractEntity> Specification<T> grater(@NonNull final SerializableFunction<T, ?> filedGetter,
                                                                     @NonNull final String name) {
        return getSimpleSpecification(name, filedGetter, EntitySpecification.GREATER_SIGN);
    }

    private static <T extends AbstractEntity> Specification<T> getSimpleSpecification(@NonNull final Object name,
                                                                                      @NonNull final SerializableFunction<T, ?> filedGetter,
                                                                                      @NonNull final String s) {
        EntitySpecification entitySpecification =
                new EntitySpecification(new SearchCriteria(getFieldName(filedGetter), s, name));
        return (Specification<T>) Specification.where(entitySpecification);
    }

    public static <T extends AbstractEntity> Specification<T> and(Specification<T>... specifications) {
        Specification<T> specification = Specification.where(specifications[0]);
        if (specifications.length > 1) {
            for (int i = 1; i < specifications.length; ++i) {
                specification = specification.and(specifications[i]);
            }
        }
        return specification;
    }


    private static <T extends AbstractEntity> String getFieldName(@NonNull final SerializableFunction<T, ?> lambda) {
        final Method findMethod = ReflectionUtils.findMethod(lambda.getClass(), "writeReplace");
        findMethod.setAccessible(true);

        final SerializedLambda invokeMethod = (SerializedLambda) ReflectionUtils.invokeMethod(findMethod, lambda);
        return invokeMethod.getImplMethodName().toLowerCase().replaceAll("get", "").replaceAll("is", "");
    }

}
