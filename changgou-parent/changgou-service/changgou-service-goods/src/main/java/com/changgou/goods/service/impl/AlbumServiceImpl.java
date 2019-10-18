package com.changgou.goods.service.impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired(required = false)
    private AlbumMapper albumMapper;

    @Override
    public PageInfo<Album> findPageByCondition(Album album, int curPage, int size) {
        PageHelper.startPage(curPage, size);
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null) {
            if (!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id", album.getId());
            }if (!StringUtils.isEmpty(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("title", "%"+album.getTitle()+"%");
            }if (!StringUtils.isEmpty(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        PageInfo<Album> albums = (PageInfo<Album>) albumMapper.selectByExample(criteria);
        return albums;
    }
}
