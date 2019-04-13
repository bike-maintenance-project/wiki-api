package com.bikemainte.wiki.repository;

import com.bikemainte.wiki.Tester;
import com.bikemainte.wiki.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author hongyu
 * @date 11:38 PM 13/4/2019
 */
@Slf4j
@Rollback(value = false)
public class CategoryRepositoryTest extends Tester {

    @Autowired
    private CategoryRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void t() throws JsonProcessingException {
        Category root = new Category();
        root.setName(Category.ROOT_CATEGORY_NAME);

        repository.save(root);

        Category c1 = new Category("c1");

        Category c2 = new Category("c2");

        Category c3 = new Category("c3");

        root.addCategory(c1, c2, c3);

        Category cc1 = new Category("cc1");
        c1.addCategory(cc1);

        repository.save(root);

        log.info(new ObjectMapper().writeValueAsString(root));
    }

    @After
    public void tearDown() throws Exception {

    }
}