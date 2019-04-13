package com.bikemainte.wiki.repository;

import com.bikemainte.wiki.entity.Category;

import java.util.Optional;

/**
 * @author hongyu
 * @date 11:35 PM 13/4/2019
 */
public interface CategoryRepository extends CrudRepository<Category> {
    Optional<Category> findDistinctByName(String name);

    /**
     * 获取root目录
     *
     * @return root目录
     */
    default Category findRootCategory() {
        return findDistinctByName(Category.ROOT_CATEGORY_NAME)
                .orElseGet(() -> save(new Category(Category.ROOT_CATEGORY_NAME)));
    }
}
