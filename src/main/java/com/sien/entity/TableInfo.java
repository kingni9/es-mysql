package com.sien.entity;

import lombok.*;

import java.util.List;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableInfo {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 导入数据执行sql
     */
    private String sql;

    /**
     * 主键key
     */
    private String primaryKey;

    /**
     * 表字段类型
     */
    private List<CommonColumn> commonColumns;
}
