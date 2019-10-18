package com.changgou.goods.controller;


import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @PostMapping("/search/{curPage}/{size}")
    public Result findPageByCondition(@RequestBody(required = false) Album album,
                                      @PathVariable("curPage") Integer curPage,
                                      @PathVariable("size") Integer size) {
        PageInfo<Album> pageByCondition = albumService.findPageByCondition(album, curPage, size);
        return new Result(true, StatusCode.OK, "条件查询相册成功！", pageByCondition);
    }
}
