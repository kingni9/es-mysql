package com.sien.business.service.dataImport;

import com.sien.entity.DataInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zhuangjt on 2018/4/20.
 */
@Component
@Slf4j
public class DataImportExecutor {
    @Autowired
    private ElasticsearchService elasticsearchService;
    @Autowired
    private DataInfoService dataInfoService;

    /**
     * 批量导入数据入口
     * @param fileName
     */
    public void dataImport(String fileName) {
        List<DataInfo> dataInfoList = dataInfoService.parseImportInfo(fileName);

        if(!CollectionUtils.isEmpty(dataInfoList)) {
            dataInfoList.stream().forEach(o -> elasticsearchService.save(o));
        }

    }
}
