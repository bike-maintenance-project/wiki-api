package com.bikemainte.wiki.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Entity to store category hierarchy for wiki category
 *
 * @author hongyu
 * @date 1:48 PM 13/4/2019
 */
@ApiModel("Category")
@Getter
@Setter
@ToString
@Entity
public class Category extends AbstractEntity {
    @ApiModelProperty(value = "目录名称", example = "Braking")
    @NaturalId
    private String name;

    /**
     * 父目录
     */
    @ApiModelProperty(value = "父级目录")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    /**
     * 子目录
     */
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "parent",
            cascade = CascadeType.REMOVE,
            // 移除目录即同时移除子目录
            orphanRemoval = true)
    @ApiModelProperty(value = "子目录")
    private List<Category> children = new ArrayList<>();

    /**
     * 目录下包含的零件类型
     */
    @ManyToMany(mappedBy = "categories")
    @ApiModelProperty("目录下包含的零件类型")
    private Set<ComponentType> components;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
