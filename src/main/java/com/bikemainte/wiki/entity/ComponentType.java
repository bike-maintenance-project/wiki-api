package com.bikemainte.wiki.entity;

import com.bikemainte.wiki.common.data.CustomPropertyDefinition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.*;

/**
 * 零件类型，保存零件动态属性模板
 *
 * @author hongyu
 * @date 4:51 PM 13/4/2019
 */
@Entity
@Getter
@Setter
@ToString
@ApiModel("零件类型")
@JsonIgnoreProperties(ignoreUnknown = true)
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class ComponentType extends AbstractEntity {

    @NaturalId
    @ApiModelProperty(value = "零件类型名称", example = "Cables")
    private String name;

    private String comment;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @ApiModelProperty(value = "自定义字段列表")
    private List<CustomPropertyDefinition> propertyDefs = new ArrayList<>();

    /**
     * 零件类型所属的目录
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "component_type_categories",
            joinColumns = @JoinColumn(name = "component_type_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @OneToMany(
            mappedBy = "type",
            cascade = CascadeType.ALL,
            // 移除零件类型时，同时移除所有零件
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Component> components = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentType that = (ComponentType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
