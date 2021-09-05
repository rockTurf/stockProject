package com.srj.web.stock.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Fund extends BaseRowModel {
    /**
     * 资金流向*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(index = 0)
    private String dataDate;//日期
    @ExcelProperty(index = 1)
    private String shClosePrice;//上证收盘价
    @ExcelProperty(index = 2)
    private String shScope;//上证涨跌幅
    @ExcelProperty(index = 3)
    private String szClosePrice;//深证收盘价
    @ExcelProperty(index = 4)
    private String szScope;//深证涨跌幅
    @ExcelProperty(index = 5)
    private String mainNum;//主力净流入额
    @ExcelProperty(index = 6)
    private String mainPer;//主力净占比
    @ExcelProperty(index = 7)
    private String hugeNum;//超大单净流入额
    @ExcelProperty(index = 8)
    private String hugePer;//超大单净占比
    @ExcelProperty(index = 9)
    private String bigNum;//大单净流入额
    @ExcelProperty(index = 10)
    private String bigPer;//大单净占比
    @ExcelProperty(index = 11)
    private String middleNum;//中单净流入额
    @ExcelProperty(index = 12)
    private String middlePer;//中单净占比
    @ExcelProperty(index = 13)
    private String smallNum;//小单净流入额
    @ExcelProperty(index = 14)
    private String smallPer;//小单净占比

}
