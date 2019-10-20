package com.changgou.listner;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.changgou.content.feign.ContentFeign;
import com.changgou.content.pojo.Content;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@CanalEventListener
public class ContentDataEventListener {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired(required = false)
    private ContentFeign contentFeign;

    @ListenPoint(destination = "example", schema = {"changgou_content"}, table = {"tb_content"},
            eventType = {CanalEntry.EventType.INSERT, CanalEntry.EventType.UPDATE})
    public void onEventContentData(CanalEntry.RowData rowData) {
        rowData.getAfterColumnsList().forEach((c) -> {
            if ("category_id".equals(c.getName())) {
                String category_id = c.getValue();//得到category_id
                Result<List<Content>> list = contentFeign.findContListByCategoryId(Long.parseLong(category_id));
                stringRedisTemplate.boundValueOps("category_" + category_id).set(JSON.toJSONString(list));
            }
        });
    }
}
