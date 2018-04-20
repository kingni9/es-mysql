package com.sien.db.dao;

import com.sien.entity.CommonColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Mapper
public interface CommonDAO {

    @Select({"${sql}"})
    List<Map<String, Object>> getForMap(@Param("sql") String sql);

    @Select({"SHOW COLUMNS FROM ${tableName}"})
    List<CommonColumn> getColumns(@Param("tableName") String tableName);
}
