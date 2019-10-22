package com.changgou.search.controller;

import com.changgou.goods.feign.SkuFeign;
import com.changgou.search.service.SearchService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class search {
    @Autowired(required = false)
    SearchService searchService;

    @GetMapping
    public Result search() {
        searchService.test();
        return new Result(true, StatusCode.OK, "test成功", 1111);
    }
}
