package com.bikemainte.wiki.common.data.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * rest查询语句请求
 *
 * @author hongyu
 * @date 1:21 PM 11/4/2019
 */
@Data
@ApiModel("rest查询语句请求")
public class RestQueryRequest {
    @ApiModelProperty("查询语句")
    private String query = "";
    @ApiModelProperty("视图 keyinfo: 关键信息 basic：基础信息 detail: 详细信息")
    private JsonViewEnum view = JsonViewEnum.keyinfo;

    public enum JsonViewEnum {
        /**
         * 关键信息
         */
        keyinfo,
        /**
         * 基础信息
         */
        basic,
        /**
         * 详细信息
         */
        detail
    }
}
