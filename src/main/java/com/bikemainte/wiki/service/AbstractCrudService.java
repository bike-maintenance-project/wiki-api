package com.bikemainte.wiki.service;

import com.bikemainte.wiki.common.data.request.RestQueryRequest;
import com.bikemainte.wiki.common.util.rsql.GenericRsqlSpecBuilder;
import com.bikemainte.wiki.entity.AbstractEntity;
import com.bikemainte.wiki.repository.CrudRepository;
import indi.hongyu.web.commons.data.request.PageConditionRequest;
import indi.hongyu.web.commons.data.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hongyu
 * @date 10:51 AM 14/4/2019
 */
public abstract class AbstractCrudService<T extends AbstractEntity> {
    protected abstract CrudRepository<T> getRepository();

    public PageResponse<T> findByRestQuery(PageConditionRequest<RestQueryRequest> query) {
        PageConditionRequest<String> pageConditionRequest = new PageConditionRequest<>();
        pageConditionRequest.setPage(query.getPage());
        pageConditionRequest.setSize(query.getSize());
        pageConditionRequest.setSortDirection(query.getSortDirection());
        pageConditionRequest.setSortFields(query.getSortFields());
        if (query.getCondition() != null) {
            pageConditionRequest.setCondition(query.getCondition().getQuery());
        }
        return findByRestQueryString(pageConditionRequest);
    }

    public PageResponse<T> findByRestQueryString(PageConditionRequest<String> query) {
        Specification<T> spec = GenericRsqlSpecBuilder.parse(query.getCondition());
        if (query.shouldPaging()) {
            Page<T> page = getRepository().findAll(spec, createPageable(query));
            return new PageResponse<>(page);
        }
        return new PageResponse<>(getRepository().findAll(spec), query);
    }

    public List<T> findByRestQuery(String query) {
        Specification<T> spec = GenericRsqlSpecBuilder.parse(query);
        return getRepository().findAll(spec);
    }

    protected Pageable createPageable(@NotNull PageConditionRequest pageConditionRequest) {
        int page = pageConditionRequest.getPage() - 1;
        int size = pageConditionRequest.getSize();
        return PageRequest.of(page, size, pageConditionRequest.createSort(AbstractEntity.DEFAULT_SORT_FIELDS));
    }
}
