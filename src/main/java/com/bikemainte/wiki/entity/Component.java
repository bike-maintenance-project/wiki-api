package com.bikemainte.wiki.entity;

import com.bikemainte.wiki.common.data.CustomProperty;
import com.bikemainte.wiki.common.data.enums.WeightUnitEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hongyu
 * @date 11:38 AM 13/4/2019
 */
@Entity
@Getter
@Setter
@ApiModel("零件")
public class Component extends AbstractEntity {

    @ApiModelProperty(value = "manufacturer", example = "Fox")
    private String manufacturer;
    @ApiModelProperty(value = "model", example = "F 100 RLT")
    private String model;
    @ApiModelProperty(value = "year of production", example = "2012")
    private Integer year;
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

    @ApiModelProperty("零件级别")
    private String grading;

    /**
     * 其他属性，用于保存动态配置的零件属性
     */
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<CustomProperty> properties = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_type_id")
    private ComponentType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(manufacturer, component.manufacturer) &&
                Objects.equals(model, component.model) &&
                Objects.equals(year, component.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, model, year);
    }
}
