package com.bikemainte.wiki.common.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义数据类型
 *
 * @author hongyu
 * @date 4:59 PM 13/4/2019
 */
@Data
@ApiModel("自定义属性")
public class CustomPropertyDefinition implements Serializable {
    @ApiModelProperty("属性名称")
    private String name;
    @ApiModelProperty("属性数据类型")
    private TypeEnum type;

    public enum TypeEnum {
        /**
         * 字符串类型
         */
        STRING,
        /**
         * 日期类型
         */
        DATE,
        /**
         * 数字类型
         */
        NUMBER
    }
}
