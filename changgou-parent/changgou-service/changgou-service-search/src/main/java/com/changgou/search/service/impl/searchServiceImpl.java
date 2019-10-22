package com.changgou.search.service.impl;

import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class searchServiceImpl implements SearchService {
    @Autowired(required = false)
    SkuFeign skuFeign;

    @Override
    public void test() {
        String skusByStatus = skuFeign.findSkusByStatus("1");
        System.out.println(skusByStatus);
        System.out.println("nimahai");
    }
}
