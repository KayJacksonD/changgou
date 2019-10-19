package com.changgou.goods.controller;

import com.changgou.goods.pojo.Good;
import com.changgou.goods.service.GoodService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/good")
@CrossOrigin
public class GoodController {
    @Autowired
    GoodService goodService;

    @PostMapping("/saveSpu")
    public Result saveSpu(@RequestBody Good good) {

        goodService.saveSpu(good);
        goodService.saveSku(good);
        return new Result(true, StatusCode.OK, "添加成功");
    }
}
