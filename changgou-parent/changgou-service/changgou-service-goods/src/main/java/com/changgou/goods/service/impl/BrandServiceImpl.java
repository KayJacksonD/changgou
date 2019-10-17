package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandDao;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.spring.mapper.SpringBootBindUtil;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired(required = false)
    BrandDao brandDao;

    @Override
    public List<Brand> findAll() {
        return brandDao.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        Brand brand = brandDao.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findPageByCondition(Brand brand, Integer curPage, Integer size) {
        PageHelper.startPage(curPage, size);
        Example example = CreateExample(brand);
        List<Brand> brands = brandDao.selectByExample(example);
        return brands;
    }

    private Example CreateExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
            if (!StringUtils.isEmpty(brand.getSeq())) {
                criteria.andEqualTo("seq", brand.getSeq());
            }
        }
        return example;
    }


}
