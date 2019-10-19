package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:传智播客
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {
    @Select("SELECT tb.name FROM `tb_brand` tb,`tb_category_brand` tcd WHERE tb.`id`=tcd.`brand_id` AND tcd.`category_id` = #{id}")
    List<Brand> findBrandByCategoryId(Integer id);
}
