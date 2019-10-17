package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import entity.Result;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand findById(Integer id);

    void add(Brand brand);

    void update(Brand brand);

    void delete(Integer id);

    List<Brand> findPageByCondition(Brand brand, Integer curPage, Integer size);
}
