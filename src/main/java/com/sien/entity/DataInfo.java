package com.sien.entity;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataInfo {

    /**
     * 索引名称
     */
    private String index;

    /**
     * 类型
     */
    private String type;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 导入数据执行sql
     */
    private String importSql;

    /**
     * 主键key
     */
    private String primaryKey;

    /**
     * 表字段类型
     */
    private List<CommonColumn> commonColumns;

    /**
     * 导入数据记录
     */
    private List<Map<String, Object>> records;
}
