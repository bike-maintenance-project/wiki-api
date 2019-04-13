package com.bikemainte.wiki.common.data;

import com.bikemainte.wiki.common.data.view.EntityView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 实体操作人
 *
 * @date 10:11 PM 14/9/2018
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Operator {
    @JsonView(EntityView.Detail.class)
    private String userId;
    @JsonView(EntityView.Detail.class)
    private String username;

    private Operator() {
    }

    public static Operator createNew(String userId, String username) {
        Operator operator = new Operator();
        operator.userId = userId;
        operator.username = username;
        return operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return Objects.equals(userId, operator.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
