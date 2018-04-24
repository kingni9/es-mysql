package com.sien.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by zhuangjt on 2018/4/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName="main",type="pt_city",refreshInterval="-1")
public class City {

    @Id
    private Integer CityID;

    /**
     * pt_province 主键
     */
    private Integer ProvinceID;

    /**
     * 城市编码(区号)
     */
    private String CityCode;

    /**
     * 城市名称
     */
    private String CityName;

    /**
     * 城市编码(行政)
     */
    private String RegionCityCode;

    /**
     * 市场级别
     */
    private Integer MarketLevel;

    /**
     * 所属上级市场ID
     */
    private Integer SuperMarketID;

    /**
     * 阡陌区域ID
     */
    private Short QmskRegionID;

    private Short Hot;

    /**
     * 调价时划分的城市级别：1——战略城市；2——重点城市；3——中型城市；4——偏远小城市
     */
    private Byte PriceLevel;
}
