package by.shalukho.specification;

import by.shalukho.criteria.SearchCriteria;
import by.shalukho.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class EntitySpecification implements Specification<AbstractEntity> {

    public static final String GREATER_SIGN = ">";
    public static final String LESS_SIGN = "<";
    public static final String LIKE_SIGN = "%";
    public static final String EQUAL_SIGN = "=";
    public static final String EQUAL_SIGN_IGNORE_CASE_SIGN = "==";
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(final Root<AbstractEntity> root,
                                 final CriteriaQuery<?> criteriaQuery,
                                 final CriteriaBuilder criteriaBuilder) {

        Predicate predicate = null;

        if (criteria.getOperation().equalsIgnoreCase(GREATER_SIGN)) {
            predicate =  criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(LESS_SIGN)) {
            predicate =  criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(LIKE_SIGN)) {
            predicate = criteriaBuilder.like(
                    root.get(criteria.getKey()), LIKE_SIGN + criteria.getValue() + LIKE_SIGN);
        } else if (criteria.getOperation().equalsIgnoreCase(EQUAL_SIGN)) {
            Class<?> javaType = root.get(criteria.getKey()).getJavaType();
            if (javaType == Boolean.class || javaType.toString().equals("boolean")) {
                predicate = getBooleanPredicate(root, criteriaBuilder, criteria.getValue());
            } else {
                predicate = criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase(EQUAL_SIGN_IGNORE_CASE_SIGN)) {
            throw new UnsupportedOperationException();
        }
        return predicate;
    }

    private Predicate getBooleanPredicate(final Root<AbstractEntity> root,
                                          final CriteriaBuilder criteriaBuilder,
                                          final Object value) {
        final Predicate predicate;
        if (Boolean.valueOf(value.toString()) == Boolean.TRUE) {
            predicate = criteriaBuilder.isTrue(root.get(criteria.getKey()));
        } else {
            predicate = criteriaBuilder.isFalse(root.get(criteria.getKey()));
        }
        return predicate;
    }

}
