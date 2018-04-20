package com.sien.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhuangjt on 2018/4/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonColumn {

    private String field;

    private String type;

    private String key;

    private String extra;
}
