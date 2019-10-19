package com.changgou.goods.service.impl;


import com.alibaba.fastjson.JSON;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.GoodMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.Good;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.GoodService;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired(required = false)
    GoodMapper goodMapper;

    @Autowired(required = false)
    IdWorker idWorker;

    @Autowired(required = false)
    SpuMapper spuMapper;
    @Autowired(required = false)
    CategoryMapper categoryMapper;

    @Autowired(required = false)
    BrandMapper brandMapper;

    @Override
    public void saveSpu(Good good) {
        long nextId = idWorker.nextId();
        Spu spu = good.getSpu();
        spu.setId(nextId);
        spu.setIsMarketable("0");
        spu.setIsDelete("0");
        spu.setStatus("0");
        spuMapper.insert(spu);
    }

    @Override
    public void saveSku(Good good) {
        List<Sku> list = good.getSkus();
        for (Sku sku : list) {
            sku.setId(idWorker.nextId());
            Spu spu = good.getSpu();
            sku.setSpuId(spu.getId());
            String name = spu.getName() + " " + spu.getCaption();
            String spec = sku.getSpec(); // json
            Map<String, String> specMap = JSON.parseObject(spec, Map.class);
            Set<Map.Entry<String, String>> entries = specMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String value = entry.getValue();
                name += " " + value;
            }
            sku.setName(name);
            sku.setCreateTime(new Date());  // 录入时间
            sku.setUpdateTime(new Date());  // 更新日期
            sku.setCategoryId(spu.getCategory3Id());    // 商品分类三级id
            sku.setCategoryName(categoryMapper.selectByPrimaryKey(spu.getCategory3Id()).getName());     // 分类名称
            sku.setBrandName(brandMapper.selectByPrimaryKey(spu.getBrandId()).getName());               // 品牌名称
            sku.setStatus("1");     // 库存状态
        }
    }
}
