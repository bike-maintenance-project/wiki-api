package com.bikemainte.wiki.util;

import com.bikemainte.wiki.common.data.Operator;

/**
 * @author hongyu
 * @date 4:14 PM 13/4/2019
 */
public class OperatorConverter extends AbstractJpaGenericConverter<Operator> {
    @Override
    protected Class<Operator> getClazz() {
        return Operator.class;
    }
}
