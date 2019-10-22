package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name = "goods")
@RequestMapping("/sku")
public interface SkuFeign {
    @GetMapping(value = "/findSkusByStatus/{status}")
    String findSkusByStatus(@PathVariable(value = "status") String status);
}