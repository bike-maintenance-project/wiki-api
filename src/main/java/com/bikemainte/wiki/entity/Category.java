package com.bikemainte.wiki.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

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
@NoArgsConstructor
public class Category extends AbstractEntity {

    public static final String ROOT_CATEGORY_NAME = "ROOT";

    @ApiModelProperty(value = "目录名称", example = "Braking")
    @NaturalId
    private String name;

    /**
     * 父目录
     */
    @ApiModelProperty(value = "父级目录")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;

    /**
     * 子目录
     */
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "parent",
            cascade = CascadeType.ALL,
            // 移除目录即同时移除子目录
            orphanRemoval = true)
    @ApiModelProperty(value = "子目录")
    private List<Category> children = new ArrayList<>();

    /**
     * 目录下包含的零件类型
     */
    @ManyToMany(mappedBy = "categories")
    @ApiModelProperty("目录下包含的零件类型")
    private Set<ComponentType> components = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }

    public void addCategory(Category... categories) {
        children.addAll(Arrays.asList(categories));
        Arrays.stream(categories).forEach(c -> c.parent = this);
    }

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
