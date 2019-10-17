package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return new Result(true, StatusCode.OK, "查询所有成功！", list);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询成功！", brand);
    }

    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功！");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable("id") Integer id) {
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "更新成功！");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功！");
    }

    @PostMapping("/searcher/{curPage}/{size}")
    public Result findPageByCondition(@PathVariable("curPage") Integer curPage,
                                      @PathVariable("size") Integer size,
                                      @RequestBody Brand brand) {
        List<Brand> list = brandService.findPageByCondition(brand, curPage, size);
        return new Result(true, StatusCode.OK, "条件分页查询成功！", list);
    }
    @PostMapping("/test")
    public Result test(@PathVariable("curPage") Integer curPage,
                                      @PathVariable("size") Integer size,
                                      @RequestBody Brand brand) {

        return new Result(true, StatusCode.OK, "条件分页查询成功！");
    }

}
