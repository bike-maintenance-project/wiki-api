package com.bikemainte.wiki;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试继承该类即可
 *
 * @date 11:49 PM 11/9/2018
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WikiApplication.class)
@Transactional
@Rollback
public class Tester {

    @Test
    public void test() {
    }
}
