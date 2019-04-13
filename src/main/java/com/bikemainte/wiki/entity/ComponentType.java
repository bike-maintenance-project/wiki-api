package com.bikemainte.wiki.entity;

import com.bikemainte.wiki.common.data.CustomProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ApiModelProperty(value = "零件类型名称", example = "Cables")
    private String name;

    @Column(name = "properties", columnDefinition = "jsonb")
    @ApiModelProperty(value = "自定义字段列表")
    private List<CustomProperty> properties;

    /**
     * 零件类型所属的目录
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "componenttype_categories",
            joinColumns = @JoinColumn(name = "componenttype_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
