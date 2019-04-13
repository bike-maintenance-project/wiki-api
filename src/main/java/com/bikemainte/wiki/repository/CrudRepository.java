package com.bikemainte.wiki.repository;

import com.bikemainte.wiki.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hongyu
 * @date 11:27 PM 13/4/2019
 */
public interface CrudRepository<T extends AbstractEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
}
