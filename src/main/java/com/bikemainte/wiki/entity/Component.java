package com.bikemainte.wiki.entity;

import com.bikemainte.wiki.common.data.enums.WeightUnitEnum;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

/**
 * @author hongyu
 * @date 11:38 AM 13/4/2019
 */
@Entity
@Getter
@Setter
@ApiModel("零件")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Component extends AbstractEntity {

    @ApiModelProperty(value = "manufacturer", example = "Fox")
    private String manufacturer;
    @ApiModelProperty(value = "model", example = "F 100 RLT")
    private String model;
    @ApiModelProperty(value = "date of production", example = "2012-01-01")
    private LocalDate date;
    @ApiModelProperty(value = "size of component", example = "1.5")
    private String size;
    //    @ApiModelProperty(value = "unit of size",example = "cm")
//    private SizeUnitEnum sizeUnit;
    @ApiModelProperty(value = "weight that manufacturer claimed", example = "285")
    private BigDecimal weightClaimed;

    @ApiModelProperty(value = "unit of weight")
    private WeightUnitEnum weightUnit;

    @ApiModelProperty(value = "actual measured weight")
    private BigDecimal weightReal;

    private String comment;

    /**
     * 其他属性，用于保存动态配置的零件属性
     * todo: 应该还需要设计一个模板数据库存放属性模板数据
     */
    @Column(name = "properties", columnDefinition = "jsonb")
    private Map<String, String> properties;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(manufacturer, component.manufacturer) &&
                Objects.equals(model, component.model) &&
                Objects.equals(date, component.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, date);
    }
}
