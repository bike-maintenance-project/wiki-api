package com.bikemainte.wiki.common.util.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import indi.hongyu.web.commons.util.control.Must;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hongyu
 * @date 11:03 AM 11/4/2019
 */
public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;
    private Root<T> root;

    public GenericRsqlSpecification(String property, ComparisonOperator operator, List<String> arguments) {
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        this.root = root;
        List<Object> args = castArguments(root);
        Object argument = args.get(0);
        switch (RsqlSearchOperation.getSimpleOperator(operator)) {

            case EQUAL: {
                if (argument instanceof String) {
                    return builder.like(getKey(), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNull(getKey());
                } else {
                    return builder.equal(getKey(), argument);
                }
            }
            case NOT_EQUAL: {
                if (argument instanceof String) {
                    return builder.notLike(getKey(), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNotNull(getKey());
                } else {
                    return builder.notEqual(getKey(), argument);
                }
            }
            case GREATER_THAN: {
                return builder.greaterThan(getKey(), argument.toString());
            }
            case GREATER_THAN_OR_EQUAL: {
                return builder.greaterThanOrEqualTo(getKey(), argument.toString());
            }
            case LESS_THAN: {
                return builder.lessThan(getKey(), argument.toString());
            }
            case LESS_THAN_OR_EQUAL: {
                return builder.lessThanOrEqualTo(getKey(), argument.toString());
            }
            case IN:
                return getKey().in(args);
            case NOT_IN:
                return builder.not(getKey().in(args));
            default:
                // do nothing
        }

        return null;
    }

    private <K> Expression<K> getKey() {
        if (!property.contains(".")) {
            return root.get(property);
        }
        String[] keys = property.split("\\.");
        Must.beTrue(keys.length == 2).orElseThrow(new IllegalArgumentException("Too many cascade level, only support 2. e.g. users.name"));
        return root.join(keys[0]).get(keys[1]);
    }

    private List<Object> castArguments(final Root<T> root) {

        Class<?> type = getKey().getJavaType();

        return arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) {
                return Integer.parseInt(arg);
            } else if (type.equals(Long.class)) {
                return Long.parseLong(arg);
                // TODO: 14/4/2019 补全枚举类型
            } else {
                return arg;
            }
        }).collect(Collectors.toList());
    }
}
