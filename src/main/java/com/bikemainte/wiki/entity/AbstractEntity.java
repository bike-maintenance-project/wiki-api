package com.bikemainte.wiki.entity;

import com.bikemainte.wiki.common.data.Operator;
import com.bikemainte.wiki.common.data.view.EntityView;
import com.bikemainte.wiki.util.OperatorConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author hongyu
 * @date 4:01 PM 13/4/2019
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@ApiModel
public class AbstractEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @JsonView(EntityView.Basic.class)
    @ApiModelProperty(notes = "实体ID", example = "d5d06b3e-77c3-429a-af60-b4af070c480e")
    protected String id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(EntityView.Detail.class)
    @ApiModelProperty(hidden = true)
    protected LocalDateTime createTime;
    /**
     * 上次更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(EntityView.Detail.class)
    @ApiModelProperty(hidden = true)
    protected LocalDateTime lastUpdateTime;

    /**
     * 创建人
     */
    @JsonView(EntityView.Detail.class)
    @Convert(converter = OperatorConverter.class)
    @ApiModelProperty(hidden = true)
    protected Operator creator;

    /**
     * 上次操作人
     */
    @JsonView(EntityView.Detail.class)
    @Convert(converter = OperatorConverter.class)
    @ApiModelProperty(hidden = true)
    protected Operator lastUpdater;
}