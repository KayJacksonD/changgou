package com.changgou.listner;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.*;

import java.util.List;

//@CanalEventListener
public class CanalDataListener {
    @InsertListenPoint
    public void onEnventInsert(CanalEntry.EntryType entryType, CanalEntry.RowData rowData) {
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();   // 列名称
            String value = column.getValue();       // 列对应的值
            System.out.println("列名：" + columnName + "列值：" + value);
        }
    }

    @UpdateListenPoint
    public void onEnventUpdate(CanalEntry.RowData rowData) {
        rowData.getBeforeColumnsList().forEach((c) -> System.out.println("列名" + c.getName() + "~~~值" + c.getValue()));
        System.out.println("=============更新前后的数据======================");
        rowData.getAfterColumnsList().forEach((c) -> System.out.println("列名" + c.getName() + "~~~值" + c.getValue()));
    }

    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println(eventType);
        rowData.getBeforeColumnsList().forEach((c) -> System.out.println(c.getName() + c.getValue()));
    }
}
