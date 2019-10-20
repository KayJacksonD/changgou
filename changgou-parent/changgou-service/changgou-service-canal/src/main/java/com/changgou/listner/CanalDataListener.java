package com.changgou.listner;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.InsertListenPoint;

import java.util.List;

@CanalEventListener
public class CanalDataListener {
    @InsertListenPoint
    public void onEnventInsert(CanalEntry.EntryType entryType, CanalEntry.RowData rowData){
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();   // 列名称
            String value = column.getValue();       // 列对应的值
            System.out.println("列名：" + columnName + "列值：" + value);
        }
    }
}
