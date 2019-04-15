package com.bikemainte.wiki.entity;

import com.bikemainte.wiki.common.data.Operator;
import com.bikemainte.wiki.common.data.view.EntityView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
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
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType.class),
        @TypeDef(name = "json-node", typeClass = JsonNodeStringType.class),
})
public class AbstractEntity {
    public static final String[] DEFAULT_SORT_FIELDS = new String[]{"lastUpdateTime"};
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
    @ApiModelProperty(hidden = true)
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    protected Operator creator;

    /**
     * 上次操作人
     */
    @JsonView(EntityView.Detail.class)
    @ApiModelProperty(hidden = true)
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    protected Operator lastUpdater;
}
