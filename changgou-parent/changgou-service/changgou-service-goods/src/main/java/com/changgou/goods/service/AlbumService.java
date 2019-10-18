package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AlbumService {
    //Album多条件分页查询
    PageInfo<Album> findPageByCondition(Album album,int curPage,int size);
}
