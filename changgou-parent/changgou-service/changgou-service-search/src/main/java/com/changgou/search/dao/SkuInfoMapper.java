package com.changgou.search.dao;

import changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuInfoMapper extends ElasticsearchRepository<SkuInfo, Long> {
}

