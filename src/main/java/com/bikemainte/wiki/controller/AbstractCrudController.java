package com.bikemainte.wiki.controller;

import com.bikemainte.wiki.entity.AbstractEntity;
import com.bikemainte.wiki.service.AbstractCrudService;
import indi.hongyu.web.commons.controller.AbstractBaseController;
import indi.hongyu.web.commons.view.json.BaseApiView;

/**
 * @author hongyu
 * @date 10:50 AM 14/4/2019
 */
public abstract class AbstractCrudController<T extends AbstractEntity, S extends AbstractCrudService<T>> extends AbstractBaseController {
    protected abstract S getService();

    protected abstract Class<? extends BaseApiView> getKeyInfoView();

    protected abstract Class<? extends BaseApiView> getBasicView();

    protected abstract Class<? extends BaseApiView> getDetailView();
}
