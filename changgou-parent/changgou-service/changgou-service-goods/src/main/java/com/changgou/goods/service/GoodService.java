package com.changgou.goods.service;


import com.changgou.goods.pojo.Good;

/****
 * @Author:传智播客
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface GoodService {

    void saveSpu(Good good);

    void saveSku(Good good);
}
